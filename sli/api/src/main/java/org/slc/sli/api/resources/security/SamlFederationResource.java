/*
 * Copyright 2012-2013 inBloom, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.slc.sli.api.resources.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.search.query.FromParseElement;
import org.jdom.Document;
import org.jdom.Element;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slc.sli.api.security.context.APIAccessDeniedException;
import org.slc.sli.api.security.roles.EdOrgContextualRoleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import org.slc.sli.api.representation.CustomStatus;
import org.slc.sli.api.security.OauthSessionManager;
import org.slc.sli.api.security.SLIPrincipal;
import org.slc.sli.api.security.SecurityEventBuilder;
import org.slc.sli.api.security.context.resolver.EdOrgHelper;
import org.slc.sli.api.security.context.resolver.RealmHelper;
import org.slc.sli.api.security.resolve.RolesToRightsResolver;
import org.slc.sli.api.security.resolve.UserLocator;
import org.slc.sli.api.security.roles.Role;
import org.slc.sli.api.security.saml.SamlAttributeTransformer;
import org.slc.sli.api.security.saml.SamlHelper;
import org.slc.sli.common.constants.EntityNames;
import org.slc.sli.common.util.logging.LogLevelType;
import org.slc.sli.common.util.logging.SecurityEvent;
import org.slc.sli.common.util.tenantdb.TenantContext;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralCriteria;
import org.slc.sli.domain.NeutralQuery;
import org.slc.sli.domain.Repository;

/**
 * Process SAML assertions
 *
 * @author dkornishev
 */
@Component
@Path("saml")
public class SamlFederationResource {

    @Autowired
    private SamlHelper saml;

    @Autowired
    @Qualifier("validationRepo")
    private Repository<Entity> repo;

    @Autowired
    private UserLocator users;

    @Autowired
    private SamlAttributeTransformer transformer;

    @Autowired
    private RolesToRightsResolver resolver;

    @Autowired
    private OauthSessionManager sessionManager;

    @Value("${sli.security.sp.issuerName}")
    private String metadataSpIssuerName;

    @Value("classpath:saml/samlMetadata.xml.template")
    private Resource metadataTemplateResource;

    @Value("${sli.api.cookieDomain}")
    private String apiCookieDomain;

    @Autowired
    @Value("${sli.sandbox.enabled}")
    private boolean sandboxEnabled;

    @Autowired
    private RealmHelper realmHelper;

    @Context
    private HttpServletRequest httpServletRequest;

    private String metadata;

    @Autowired
    private SecurityEventBuilder securityEventBuilder;

    @Autowired
    private EdOrgHelper edorgHelper;

    @Autowired
    private EdOrgContextualRoleBuilder edOrgRoleBuilder;

    public static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings("unused")
    @PostConstruct
    private void processMetadata() throws IOException {
        InputStream is = metadataTemplateResource.getInputStream();
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);
        is.close();

        metadata = writer.toString();
        metadata = metadata.replaceAll("\\$\\{sli\\.security\\.sp.issuerName\\}", metadataSpIssuerName);
    }

    @POST
    @Path("sso/post")
    @SuppressWarnings("unchecked")
    public Response consume(@FormParam("SAMLResponse") String postData, @Context UriInfo uriInfo) {

        info("Received a SAML post for SSO...");
        TenantContext.setTenantId(null);
        Document doc = null;
        String targetEdOrg = null;

        try {
            doc = saml.decodeSamlPost(postData);
        } catch (Exception e) {
            SecurityEvent event = securityEventBuilder.createSecurityEvent(this.getClass().getName(), uriInfo.getRequestUri(), "", false);


            try {
                event.setExecutedOn(InetAddress.getLocalHost().getHostName());
            } catch (UnknownHostException ue) {
                info("Could not find hostname for security event logging!");
            }

            if (httpServletRequest != null) {
                event.setUserOrigin(httpServletRequest.getRemoteHost());
                event.setAppId(httpServletRequest.getHeader("User-Agent"));
                event.setUser(httpServletRequest.getRemoteUser());

                // the origin header contains the uri info of the idp server that sends the SAML
                // data
                event.setLogMessage("SAML message received from " + httpServletRequest.getHeader("Origin")
                        + " is invalid!");
                event.setLogLevel(LogLevelType.TYPE_WARN);
            } else {
                event.setLogMessage("HttpServletRequest is missing, and this should never happen!!");
                event.setLogLevel(LogLevelType.TYPE_ERROR);
            }

            audit(event);

            generateSamlValidationError(e.getMessage(), targetEdOrg);
        }

        String inResponseTo = doc.getRootElement().getAttributeValue("InResponseTo");
        String issuer = doc.getRootElement().getChildText("Issuer", SamlHelper.SAML_NS);

        NeutralQuery neutralQuery = new NeutralQuery();
        neutralQuery.setOffset(0);
        neutralQuery.setLimit(1);
        neutralQuery.addCriteria(new NeutralCriteria("idp.id", "=", issuer));
        Entity realm = repo.findOne("realm", neutralQuery);

        if (realm == null) {
            generateSamlValidationError("Invalid realm: " + issuer, targetEdOrg);
        }

        if (realm.getBody() != null) {
            targetEdOrg = (String) realm.getBody().get("edOrg");
        }
        Element assertion = doc.getRootElement().getChild("Assertion", SamlHelper.SAML_NS);
        Element stmt = assertion.getChild("AttributeStatement", SamlHelper.SAML_NS);

        Element conditions = assertion.getChild("Conditions", SamlHelper.SAML_NS);

        if (conditions != null) {

            // One or both of these can be null
            String notBefore = conditions.getAttributeValue("NotBefore");
            String notOnOrAfter = conditions.getAttributeValue("NotOnOrAfter");

            if (!isTimeInRange(notBefore, notOnOrAfter)) {
                generateSamlValidationError("SAML Conditions failed.  Current time not in range " + notBefore + " to "
                        + notOnOrAfter + ".", targetEdOrg);
            }
        }

        if (assertion.getChild("Subject", SamlHelper.SAML_NS) != null) {
            Element subjConfirmationData = assertion.getChild("Subject", SamlHelper.SAML_NS)
                    .getChild("SubjectConfirmation", SamlHelper.SAML_NS)
                    .getChild("SubjectConfirmationData", SamlHelper.SAML_NS);
            String recipient = subjConfirmationData.getAttributeValue("Recipient");

            if (!uriInfo.getRequestUri().toString().equals(recipient)) {
                generateSamlValidationError("SAML Recipient was invalid, was " + recipient, targetEdOrg);
            }

            // One or both of these can be null
            String notBefore = subjConfirmationData.getAttributeValue("NotBefore");
            String notOnOrAfter = subjConfirmationData.getAttributeValue("NotOnOrAfter");

            if (!isTimeInRange(notBefore, notOnOrAfter)) {
                generateSamlValidationError("SAML Subject failed.  Current time not in range " + notBefore + " to "
                        + notOnOrAfter + ".", targetEdOrg);
            }
        } else {
            generateSamlValidationError("SAML response is missing Subject.", targetEdOrg);
        }

        List<org.jdom.Element> attributeNodes = stmt.getChildren("Attribute", SamlHelper.SAML_NS);

        LinkedMultiValueMap<String, String> attributes = new LinkedMultiValueMap<String, String>();
        for (org.jdom.Element attributeNode : attributeNodes) {
            String samlAttributeName = attributeNode.getAttributeValue("Name");
            List<org.jdom.Element> valueNodes = attributeNode.getChildren("AttributeValue", SamlHelper.SAML_NS);
            for (org.jdom.Element valueNode : valueNodes) {
                attributes.add(samlAttributeName, valueNode.getText());
            }
        }

        // Apply transforms
        attributes = transformer.apply(realm, attributes);

        SLIPrincipal principal;
        String tenant;
        String sandboxTenant = null; //for developers coming from developer realm
        String realmTenant = (String) realm.getBody().get("tenantId");
        String samlTenant = attributes.getFirst("tenant");

        Boolean isAdminRealm = (Boolean) realm.getBody().get("admin");
        isAdminRealm = (isAdminRealm != null) ? isAdminRealm : Boolean.FALSE;

        Boolean isDevRealm = (Boolean) realm.getBody().get("developer");
        isDevRealm = (isDevRealm != null) ? isDevRealm : Boolean.FALSE;
        if (isAdminRealm && sandboxEnabled) {
            // Sandbox mode using the SimpleIDP
            //reset isAdminRealm based on the value of the saml isAdmin attribute
            //since this same realm is used for impersonation and admin logins
            isAdminRealm = Boolean.valueOf(attributes.getFirst("isAdmin"));
            // accept the tenantId from the Sandbox-IDP or if none then it's an admin user
            if (isAdminRealm){
                tenant = null; //operator admin
            }else{
                //impersonation login, require tenant in the saml
                if(samlTenant != null) {
                    tenant = samlTenant;
                }else{
                    error("Attempted login by a user in sandbox mode but no tenant was specified in the saml message.");
                    throw new APIAccessDeniedException("Invalid user configuration.", (String) realm.getBody().get("edOrg"));
                }
            }
        } else if(isAdminRealm){
            //Prod mode, admin login
            tenant = null;
        } else if (isDevRealm) {
            //Prod mode, developer login
            tenant = null;
            sandboxTenant = samlTenant;
            samlTenant = null;
        } else {
            //regular IDP login
            tenant = realmTenant;
        }

        debug("Authenticating user is an admin: " + isAdminRealm);
        principal = users.locate(tenant, attributes.getFirst("userId"), attributes.getFirst("userType"));
        String userName = getUserNameFromEntity(principal.getEntity());
        if (userName != null) {
            principal.setName(userName);
        } else {
            if (isAdminRealm || isDevRealm) {
                principal.setFirstName(attributes.getFirst("givenName"));
                principal.setLastName(attributes.getFirst("sn"));
                principal.setVendor(attributes.getFirst("vendor"));
                principal.setEmail(attributes.getFirst("mail"));
            }
            principal.setName(attributes.getFirst("userName"));
        }

        // cache realm edOrg for security events
        principal.setRealmEdOrg(targetEdOrg);

        List<String> roles = attributes.get("roles");
        if (roles == null || roles.isEmpty()) {
            error("Attempted login by a user that did not include any roles in the SAML Assertion.");
            throw new APIAccessDeniedException("Invalid user. No roles specified for user.", realm);
        }

        if(!(isAdminRealm || isDevRealm) &&
                (principal.getUserType() == null || principal.getUserType().equals("") || principal.getUserType().equals(EntityNames.STAFF))) {
            Map<String, List<String>> sliEdOrgRoleMap = edOrgRoleBuilder.buildValidStaffRoles(realm.getEntityId(), principal.getEntity().getEntityId(), tenant, roles);
            principal.setEdOrgRoles(sliEdOrgRoleMap);
        }

        principal.setRealm(realm.getEntityId());
        principal.setEdOrg(attributes.getFirst("edOrg"));
        principal.setAdminRealm(attributes.getFirst("edOrg"));

        if ("-133".equals(principal.getEntity().getEntityId()) && !(isAdminRealm || isDevRealm)) {
            // if we couldn't find an Entity for the user and this isn't an admin realm, then we
            // have no valid user
            throw new APIAccessDeniedException("Invalid user.", realm);
        }

        if (!(isAdminRealm || isDevRealm) && !realmHelper.isUserAllowedLoginToRealm(principal.getEntity(), realm)) {
            throw new APIAccessDeniedException("User is not associated with realm.", realm);
        }

        //F262: Update this to only store roles for non staff users
        Set<Role> sliRoleSet = resolver.mapRoles(tenant, realm.getEntityId(), roles, isAdminRealm);
        List<String> sliRoleList = new ArrayList<String>();
        boolean isAdminUser = true;
        for (Role role : sliRoleSet) {
            sliRoleList.addAll(role.getName());
            if (!role.isAdmin()) {
                isAdminUser = false;
                break;
            }
        }

        principal.setRoles(sliRoleList);
        principal.setAdminUser(isAdminUser);
        principal.setAdminRealmAuthenticated(isAdminRealm || isDevRealm);

        if (principal.getRoles().isEmpty()) {
            debug("Attempted login by a user that included no roles in the SAML Assertion that mapped to any of the SLI roles.");
            throw new APIAccessDeniedException(
                    "Invalid user.  No valid role mappings exist for the roles specified in the SAML Assertion.", realm);
        }

        if (samlTenant != null) {
            principal.setTenantId(samlTenant);
            TenantContext.setTenantId(samlTenant);
            TenantContext.setIsSystemCall(false);
            NeutralQuery idQuery = new NeutralQuery();
            idQuery.addCriteria(new NeutralCriteria("stateOrganizationId", NeutralCriteria.OPERATOR_EQUAL, principal
                    .getEdOrg()));
            Entity edOrg = repo.findOne(EntityNames.EDUCATION_ORGANIZATION, idQuery);
            if (edOrg != null) {
                principal.setEdOrgId(edOrg.getEntityId());
            } else {
                debug("Failed to find edOrg with stateOrganizationID {} and tenantId {}", principal.getEdOrg(),
                        principal.getTenantId());
            }
        }

        if (sandboxTenant != null && isDevRealm) {
            principal.setSandboxTenant(sandboxTenant);
        }

        TenantContext.setIsSystemCall(true);

        Entity session = sessionManager.getSessionForSamlId(inResponseTo);

        String requestedRealmId = (String) session.getBody().get("requestedRealmId");
        if (requestedRealmId == null || !requestedRealmId.equals(realm.getEntityId())) {
            generateSamlValidationError("Requested Realm (id=" + requestedRealmId +") does not match the realm the user authenticated against (id="+realm.getEntityId()+")", targetEdOrg);
        }

        Map<String, Object> appSession = sessionManager.getAppSession(inResponseTo, session);
        Boolean isInstalled = (Boolean) appSession.get("installed");
        Map<String, Object> code = (Map<String, Object>) appSession.get("code");

        ObjectMapper jsoner = new ObjectMapper();
        Map<String, Object> mapForm = jsoner.convertValue(principal, Map.class);
        mapForm.remove("entity");
        if (!mapForm.containsKey("userType")) {
            mapForm.put("userType", EntityNames.STAFF);
        }
        session.getBody().put("principal", mapForm);
        sessionManager.updateSession(session);

        String authorizationCode = (String) code.get("value");
        Object state = appSession.get("state");

        SecurityEvent successfulLogin = securityEventBuilder.createSecurityEvent(this.getClass().getName(), uriInfo.getRequestUri(), "", principal, null, realm, null, true);
        successfulLogin.setOrigin(httpServletRequest.getRemoteHost()+ ":" + httpServletRequest.getRemotePort());
        successfulLogin.setCredential(authorizationCode);
        successfulLogin.setUserOrigin(httpServletRequest.getRemoteHost()+ ":" + httpServletRequest.getRemotePort());
        successfulLogin.setLogLevel(LogLevelType.TYPE_INFO);
        successfulLogin.setRoles(principal.getRoles());

        String applicationDetails = null;
        if(appSession != null){
            String clientId = (String)appSession.get("clientId");
            if(clientId != null) {
                NeutralQuery appQuery = new NeutralQuery();
                appQuery.setOffset(0);
                appQuery.setLimit(1);
                appQuery.addCriteria(new NeutralCriteria("client_id", "=", clientId));
                Entity application = repo.findOne("application", appQuery);
                if(application != null) {
                    Map<String, Object> body = application.getBody();
                    if (body != null) {
                        String name                = (String) body.get("name");
                        String createdBy           = (String) body.get("created_by");
                        successfulLogin.setAppId(name+"," + clientId);
                        applicationDetails         = String.format("%s by %s", name, createdBy);
                    }
                }
            }
        }
        successfulLogin.setUser(principal.getExternalId());
        successfulLogin.setLogMessage(principal.getExternalId() + " from tenant " + tenant + " logged successfully into " + applicationDetails + ".");

        audit(successfulLogin);

        if (isInstalled) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("authorization_code", authorizationCode);
            if (state != null) {
                resultMap.put("state", state);
            }
            info("Sending back authorization token for installed app: {}", authorizationCode);
            return Response.ok(resultMap).build();

        } else {
            String redirectUri = (String) appSession.get("redirectUri");
            UriBuilder builder = UriBuilder.fromUri(redirectUri);
            builder.queryParam("code", authorizationCode);
            if (state != null) {
                builder.queryParam("state", state);
            }

            boolean runningSsl = uriInfo.getRequestUri().getScheme().equals("https");
            URI redirect = builder.build();
            return Response.status(CustomStatus.FOUND)
                    .cookie(new NewCookie("_tla", session.getEntityId(), "/", apiCookieDomain, "", -1, runningSsl))
                    .location(redirect).build();
        }
    }


    private void generateSamlValidationError(String message, String targetEdOrg) {
        error(message);
        throw new APIAccessDeniedException("Authorization could not be verified.", targetEdOrg);
    }

    private String getUserNameFromEntity(Entity entity) {
        if (entity != null) {
            @SuppressWarnings("rawtypes")
            Map nameMap = (Map) entity.getBody().get("name");
            if (nameMap != null) {
                StringBuffer name = new StringBuffer();
                if (nameMap.containsKey("personalTitlePrefix")) {
                    name.append((String) nameMap.get("personalTitlePrefix"));
                    name.append(" ");
                }
                name.append((String) nameMap.get("firstName"));
                name.append(" ");
                name.append((String) nameMap.get("lastSurname"));
                if (nameMap.containsKey("generationCodeSuffix")) {
                    name.append(" ");
                    name.append((String) nameMap.get("generationCodeSuffix"));
                }
                return name.toString();
            }
        }
        return null;
    }

    /**
     * Get metadata describing saml federation.
     * This is an unsecured (public) resource.
     *
     * @return Response containing saml metadata
     */
    @GET
    @Path("metadata")
    @Produces({ "text/xml" })
    public Response getMetadata() {

        if (!metadata.isEmpty()) {
            return Response.ok(metadata).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    /**
     * Check that the current time is within the specified range.
     *
     * @param notBefore
     *            - can be null to skip before check
     * @param notOnOrAfter
     *            - can be null to skip after check
     *
     * @return true if in range, false otherwise
     */
    private boolean isTimeInRange(String notBefore, String notOnOrAfter) {
        DateTime currentTime = new DateTime(DateTimeZone.UTC);

        if (notBefore != null) {
            DateTime calNotBefore = DateTime.parse(notBefore);
            if (currentTime.isBefore(calNotBefore)) {
                debug("{} is before {}.", currentTime, calNotBefore);
                return false;
            }
        }

        if (notOnOrAfter != null) {
            DateTime calNotOnOrAfter = DateTime.parse(notOnOrAfter);
            if (currentTime.isAfter(calNotOnOrAfter) || currentTime.isEqual(calNotOnOrAfter)) {
                debug("{} is on or after {}.", currentTime, calNotOnOrAfter);
                return false;
            }
        }
        return true;
    }

    Repository getRepository() {
        return repo;
    }

    public void setEdorgHelper(EdOrgHelper edorgHelper) {
        this.edorgHelper = edorgHelper;
    }

    public void setSecurityEventBuilder(SecurityEventBuilder securityEventBuilder) {
    	this.securityEventBuilder = securityEventBuilder;
    }

}
