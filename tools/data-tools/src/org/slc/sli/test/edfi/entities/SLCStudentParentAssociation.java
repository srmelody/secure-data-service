//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.06 at 10:00:50 AM EST 
//


package org.slc.sli.test.edfi.entities;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * StudentParentAssociation record with key fields: StudentReference and ParentReference. Changed types of StudentReference and ParentReference to SLC reference types. 
 * 
 * <p>Java class for SLC-StudentParentAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SLC-StudentParentAssociation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentReference" type="{http://ed-fi.org/0100}SLC-StudentReferenceType"/>
 *         &lt;element name="ParentReference" type="{http://ed-fi.org/0100}SLC-ParentReferenceType"/>
 *         &lt;element name="Relation" type="{http://ed-fi.org/0100}RelationType" minOccurs="0"/>
 *         &lt;element name="PrimaryContactStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LivesWith" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EmergencyContactStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ContactPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ContactRestrictions" type="{http://ed-fi.org/0100}ContactRestrictions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-StudentParentAssociation", propOrder = {
    "studentReference",
    "parentReference",
    "relation",
    "primaryContactStatus",
    "livesWith",
    "emergencyContactStatus",
    "contactPriority",
    "contactRestrictions"
})
@XmlRootElement(name = "StudentParentAssociation") 
public class SLCStudentParentAssociation {

    @XmlElement(name = "StudentReference", required = true)
    protected SLCStudentReferenceType studentReference;
    @XmlElement(name = "ParentReference", required = true)
    protected SLCParentReferenceType parentReference;
    @XmlElement(name = "Relation")
    protected RelationType relation;
    @XmlElement(name = "PrimaryContactStatus")
    protected Boolean primaryContactStatus;
    @XmlElement(name = "LivesWith")
    protected Boolean livesWith;
    @XmlElement(name = "EmergencyContactStatus")
    protected Boolean emergencyContactStatus;
    @XmlElement(name = "ContactPriority")
    protected Integer contactPriority;
    @XmlElement(name = "ContactRestrictions")
    protected String contactRestrictions;

    /**
     * Gets the value of the studentReference property.
     * 
     * @return
     *     possible object is
     *     {@link SLCStudentReferenceType }
     *     
     */
    public SLCStudentReferenceType getStudentReference() {
        return studentReference;
    }

    /**
     * Sets the value of the studentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLCStudentReferenceType }
     *     
     */
    public void setStudentReference(SLCStudentReferenceType value) {
        this.studentReference = value;
    }

    /**
     * Gets the value of the parentReference property.
     * 
     * @return
     *     possible object is
     *     {@link SLCParentReferenceType }
     *     
     */
    public SLCParentReferenceType getParentReference() {
        return parentReference;
    }

    /**
     * Sets the value of the parentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLCParentReferenceType }
     *     
     */
    public void setParentReference(SLCParentReferenceType value) {
        this.parentReference = value;
    }

    /**
     * Gets the value of the relation property.
     * 
     * @return
     *     possible object is
     *     {@link RelationType }
     *     
     */
    public RelationType getRelation() {
        return relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationType }
     *     
     */
    public void setRelation(RelationType value) {
        this.relation = value;
    }

    /**
     * Gets the value of the primaryContactStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrimaryContactStatus() {
        return primaryContactStatus;
    }

    /**
     * Sets the value of the primaryContactStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrimaryContactStatus(Boolean value) {
        this.primaryContactStatus = value;
    }

    /**
     * Gets the value of the livesWith property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLivesWith() {
        return livesWith;
    }

    /**
     * Sets the value of the livesWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLivesWith(Boolean value) {
        this.livesWith = value;
    }

    /**
     * Gets the value of the emergencyContactStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmergencyContactStatus() {
        return emergencyContactStatus;
    }

    /**
     * Sets the value of the emergencyContactStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmergencyContactStatus(Boolean value) {
        this.emergencyContactStatus = value;
    }

    /**
     * Gets the value of the contactPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContactPriority() {
        return contactPriority;
    }

    /**
     * Sets the value of the contactPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContactPriority(Integer value) {
        this.contactPriority = value;
    }

    /**
     * Gets the value of the contactRestrictions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactRestrictions() {
        return contactRestrictions;
    }

    /**
     * Sets the value of the contactRestrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactRestrictions(String value) {
        this.contactRestrictions = value;
    }

}
