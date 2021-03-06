@RALLY_US4835
@rc
Feature:  RC Integration Tests

Background:
Given I have an open web browser

Scenario: Realm Admin Logins to create realm
When I navigate to the Portal home page
When I see the realm selector I authenticate to "inBloom"
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "<SECONDARY_EMAIL>" "<SECONDARY_EMAIL_PASS>" for the "Simple" login page
Then I should be on Portal home page
Then I should see Admin link
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Manage Realm"
And I switch to the iframe
And I should see that I am on the new realm page
And all of the input fields should be blank
And I should enter "Daybreak Test Realm" into the Display Name field
And I enter "<CI_IDP_Redirect_URL>" in the IDP URL field
And I enter "<CI_IDP_Redirect_URL>" in the Redirect Endpoint field
And I should enter "RC-IL-Daybreak" into Realm Identifier
And I should click the "Save" button
And I switch to the iframe
And I should receive a notice that the realm was successfully "created"
Then I see the realms for "Daybreak School District 4529 (IL-DAYBREAK)"
And the realm "Daybreak Test Realm" will exist
And I exit out of the iframe
And I click on log out

Scenario: User cannot access Bootstrapped Apps before approval
When I navigate to the Portal home page
When I selected the realm "Daybreak Test Realm"
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page    
Then I should be on Portal home page
Then I should not see "inBloom Dashboards"
And I click on Admin
And I should be on the admin page
And I should not see "inBloom Data Browser"
And I click on log out

Scenario:  LEA approves Dashboard, Databrowser and Bulk Extract 2 End Applications
When I navigate to the Portal home page
When I see the realm selector I authenticate to "inBloom"
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "<SECONDARY_EMAIL>" "<SECONDARY_EMAIL_PASS>" for the "Simple" login page
Then I should be on Portal home page
Then I should see Admin link
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Authorize Applications"
And I switch to the iframe
Then I am redirected to the Admin Application Authorization Tool
#Authorize the Dashboard
And I see an application "inBloom Dashboards" in the table
And in Status it says "Not Approved"
And I click on the "Approve" button next to it
And I am asked 'Do you really want this application to access the district's data'
When I click on Ok
Then the application is authorized to use data of "Daybreak School District 4529"
And the app "inBloom Dashboards" Status becomes "Approved"
And it is colored "green"
And the Approve button next to it is disabled
And the Deny button next to it is enabled
#Authorize the Databrowser
And I see an application "inBloom Data Browser" in the table
And in Status it says "Not Approved"
And I click on the "Approve" button next to it
And I am asked 'Do you really want this application to access the district's data'
When I click on Ok
# switch back to iframe because of the page reload
And I switch to the iframe
Then the application is authorized to use data of "Daybreak School District 4529"
And the app "inBloom Data Browser" Status becomes "Approved"
And it is colored "green"
And the Approve button next to it is disabled
And the Deny button next to it is enabled
#Authorize the New Installed App
And I see an application "Bulk Extract 2 End" in the table
And in Status it says "Not Approved"
And I click on the "Approve" button next to it
And I am asked 'Do you really want this application to access the district's data'
When I click on Ok
# switch back to iframe because of the page reload
And I switch to the iframe
Then the application is authorized to use data of "Daybreak School District 4529"
And the app "Bulk Extract 2 End" Status becomes "Approved"
And it is colored "green"
And the Approve button next to it is disabled
And the Deny button next to it is enabled

#Add Bulk Extract role to IT Admin
And I exit out of the iframe
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Create Custom Roles"
And I switch to the iframe
And I edit the group "IT Administrator"
When I add the right "BULK_EXTRACT" to the group "IT Administrator"
And I hit the save button
Then I am no longer in edit mode
And I switch to the iframe
And the group "IT Administrator" contains the "right" rights "Bulk IT Administrator"
And I exit out of the iframe
And I click on log out

Scenario: SEA admin makes an api call to PATCH the SEA
  Given the pre-existing bulk extrac testing app key has been created
  When I navigate to the API authorization endpoint with my client ID
  And I select "Daybreak Test Realm" and click go
  And I was redirected to the "Simple" IDP Login page
  When I submit the credentials "rrogers" "rrogers1234" for the "Simple" login page
  Then I should receive a json response containing my authorization code
  When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
  Then I should receive a json response containing my authorization token
  And I get the id for the edorg "STANDARD-SEA"
  When I PATCH the name for the current edorg entity to Education Agency for RC Tests
  Then I should receive a return code of 204

Scenario: App developer creates new Bulk Extract App
When I navigate to the Portal home page
When I see the realm selector I authenticate to the developer realm
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "<DEVELOPER_EMAIL>" "<DEVELOPER_EMAIL_PASS>" for the "Simple" login page    
Then I should be on Portal home page
Then I should see Admin link
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Register Application"
And I switch to the iframe
Then I am redirected to the Application Registration Tool page
And I have clicked to the button New
And I am redirected to a new application page
When I entered the name "BulkExtractApp" into the field titled "Name"
And I entered the name "Best.  Description.  Ever." into the field titled "Description"
And I entered the name "0.0" into the field titled "Version"
And I make my app an installed app
And I check Bulk Extract
And I click on the button Submit
And I switch to the iframe
Then I am redirected to the Application Registration Tool page
And the application "BulkExtractApp" is listed in the table on the top
And the client ID and shared secret fields are present
And I exit out of the iframe
And I click on log out

Scenario: App developer enables Bulk Extract App
When I navigate to the Portal home page
When I see the realm selector I authenticate to the developer realm
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "<DEVELOPER_EMAIL>" "<DEVELOPER_EMAIL_PASS>" for the "Simple" login page    
Then I should be on Portal home page
Then I should see Admin link
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Register Application"
And I switch to the iframe
Then I am redirected to the Application Registration Tool page
And I see an application "BulkExtractApp" in the table
And the client ID and shared secret fields are present
And I clicked on the button Edit for the application "BulkExtractApp"
Then I can see the on-boarded states
When I select the state "Education Agency for RC Tests"
Then I see all of the Districts
Then I check the Districts
When I click on Save
Then the "BulkExtractApp" is enabled for Districts
And I exit out of the iframe
And I click on log out

@wip @ThisStepIsNotYetNeededSinceAutoApproveAppsIsStillTrueInRC
Scenario: SLC Operator Approves Application Registration
When I navigate to the Portal home page
When I see the realm selector I authenticate to "inBloom"
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "slcoperator" "slcoperator1234" for the "Simple" login page
Then I should be on Portal home page
Then I should see Admin link
And I click on Admin
Then I should be on the admin page
And under System Tools, I click on "Approve Application Registration"
And I switch to the iframe
Then I am redirected to the Application Approval Tool page
#And I see all the applications registered on SLI
And I see all the applications pending registration
And the pending apps are on top
When I click on 'Approve' next to application "BulkExtractApp"
Then application "BulkExtractApp" is registered
And the 'Approve' button is disabled for application "BulkExtractApp"
And I exit out of the iframe
And I click on log out

Scenario: Sessions are shared between Dashboard and Databrowser apps
When I navigate to the Portal home page
When I select "Daybreak Test Realm" and click go
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page    
Then I should be on Portal home page
When I navigate to the dashboard page
And I am redirected to the dashboard home page
When I navigate to the databrowser page
Then I do not see any login pages
Then I am redirected to the databrowser home page
And I click on log out
And I should forced to reauthenticate to gain access
When I navigate to the dashboard home page
Then I should forced to reauthenticate to gain access

Scenario: User sees non-installed Developer App 
When I navigate to the Portal home page
When I selected the realm "Daybreak Test Realm"
And I was redirected to the "Simple" IDP Login page
When I submit the credentials "linda.kim" "linda.kim1234" for the "Simple" login page    
Then I should be on Portal home page
And under My Applications, I see the following apps: "inBloom Dashboards"

Scenario: Operator triggers a bulk extract
Given the production extraction zone is empty
And the operator triggers a bulk extract for the production tenant
And the operator triggers a delta for the production tenant

   Scenario: App makes an api call to retrieve an lea level bulk extract
   #Get a session to trigger a bulk extract
   Given the pre-existing bulk extrac testing app key has been created
   When I navigate to the API authorization endpoint with my client ID
   When I select "Daybreak Test Realm" and click go
   And I was redirected to the "Simple" IDP Login page
   When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page
   Then I should receive a json response containing my authorization code
   When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
   Then I should receive a json response containing my authorization token
   Then I get the id for the edorg "IL-DAYBREAK"
   #Get bulk extract tar file
   Then there is no bulk extract files in the local directory
   And I request and download a "bulk" extract file for the edorg
   And there is a metadata file in the extract
   And the extract contains a file for each of the following entities:
      |  entityType                            |
      # |  assessment                            |
      |  attendance                            |
      |  calendarDate                          |
      |  cohort                                |
      |  course                                |
      |  courseTranscript                      |
      |  courseOffering                        |
      |  disciplineIncident                    |
      |  disciplineAction                      |
      |  educationOrganization                 |
      |  grade                                 |
      |  gradebookEntry                        |
      |  gradingPeriod                         |
      # |  learningObjective                     |
      # |  learningStandard                      |
      |  parent                                |
      # |  program                               |
      |  reportCard                            |
      |  school                                |
      |  section                               |
      |  session                               |
      |  staff                                 |
      |  staffCohortAssociation                |
      |  staffEducationOrganizationAssociation |
      |  staffProgramAssociation               |
      |  student                               |
      |  studentAcademicRecord                 |
      |  studentAssessment                     |
      |  studentCohortAssociation              |
      |  studentCompetency                     |
      # |  studentCompetencyObjective            |
      |  studentDisciplineIncidentAssociation  |
      |  studentProgramAssociation             |
      |  studentGradebookEntry                 |
      |  studentSchoolAssociation              |
      |  studentSectionAssociation             |
      |  studentParentAssociation              |
      |  teacher                               |
      |  teacherSchoolAssociation              |
      |  teacherSectionAssociation             |

Scenario: App makes an api call to retrieve a SEA public data bulk extract
   #Get a session to trigger a bulk extract
   Given the pre-existing bulk extrac testing app key has been created
   When I navigate to the API authorization endpoint with my client ID
   When I select "Daybreak Test Realm" and click go
   And I was redirected to the "Simple" IDP Login page
   When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page
   Then I should receive a json response containing my authorization code
   When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
   Then I should receive a json response containing my authorization token
   Then I get the id for the edorg "STANDARD-SEA"
   #Get bulk extract tar file
   Then there is no bulk extract files in the local directory
   And I request and download a "bulk" extract file for the edorg
   And there is a metadata file in the extract
   And the extract contains a file for each of the following entities:
      |  entityType                            |
      |  assessment                            |
      |  educationOrganization                 |
      |  learningObjective                     |
      |  learningStandard                      |
      |  program                               |
      |  studentCompetencyObjective            |
      |  calendarDate                          |

Scenario: App makes an api call to retrieve a bulk extract delta
#Get a session to trigger a bulk extract
Given the pre-existing bulk extrac testing app key has been created
  When I navigate to the API authorization endpoint with my client ID
   And I select "Daybreak Test Realm" and click go
   And I was redirected to the "Simple" IDP Login page
  When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page
  Then I should receive a json response containing my authorization code
  When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
  Then I should receive a json response containing my authorization token
   And there is no bulk extract files in the local directory 
 
  Then I get the id for the edorg "IL-DAYBREAK"
  When I PATCH the postalCode for the lea entity to 11999
  Then I should receive a return code of 204
  When the operator triggers a delta for the production tenant
   #And I make a call to the bulk extract end point "/v1.1/bulk/extract/list"
   And I make a call to the bulk extract end point "/v1.1/bulk/extract/list" using the certificate for app "pike"
   And I get back a response code of "200"
   And I store the URL for the latest delta for the LEA
   And the number of returned URLs is correct:
   |   fieldName  | count |
   |   fullLeas   |  1    |
   |   deltaLeas  |  1    |
   And I request and download a "delta" extract file for the edorg
   And there is a metadata file in the extract
   And the extract contains a file for each of the following entities:
   |  entityType                            |
   |  educationOrganization                 |
   |  school                                |

Scenario: Ingestion user ingests additional public entities
  Given a landing zone
  And I drop the file "NewSimplePublicEntities.zip" into the landingzone
  And I check for the file "job-NewSimplePublicEntities*.log" every "6" seconds for "120" seconds
  Then I should not see an error log file created
  And I should not see a warning log file created

  Scenario: SEA admin makes an api call to PATCH the SEA
    Given the pre-existing bulk extrac testing app key has been created
    When I navigate to the API authorization endpoint with my client ID
    And I select "Daybreak Test Realm" and click go
    And I was redirected to the "Simple" IDP Login page
    When I submit the credentials "rrogers" "rrogers1234" for the "Simple" login page
    Then I should receive a json response containing my authorization code
    When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
    Then I should receive a json response containing my authorization token
    And I get the id for the edorg "STANDARD-SEA"
    When I PATCH the postalCode for the current edorg entity to 99999
    Then I should receive a return code of 204

Scenario: App makes an api call to retrieve a bulk extract delta for the SEA
  #Get a session to trigger a bulk extract
  Given the pre-existing bulk extrac testing app key has been created
  When I navigate to the API authorization endpoint with my client ID
  And I select "Daybreak Test Realm" and click go
  And I was redirected to the "Simple" IDP Login page
  When I submit the credentials "jstevenson" "jstevenson1234" for the "Simple" login page
  Then I should receive a json response containing my authorization code
  When I navigate to the API token endpoint with my client ID, secret, authorization code, and redirect URI
  Then I should receive a json response containing my authorization token
  And there is no bulk extract files in the local directory
  And I get the id for the edorg "STANDARD-SEA"

  When the operator triggers a delta for the production tenant
  And I make a call to the bulk extract end point "/v1.1/bulk/extract/list" using the certificate for app "pike"
  And I get back a response code of "200"
  And I store the URL for the latest delta for the SEA
  And the number of returned URLs is correct:
    |   fieldName  | count |
    |   deltaSea   |  1    |
  And I request and download a "delta" extract file for the edorg
  And there is a metadata file in the extract
  And the extract contains a file for each of the following entities:
    |  entityType                            |
    |  educationOrganization                 |
    |  competencyLevelDescriptor             |
    |  studentCompetencyObjective            |
    |  learningObjective                     |
    |  learningStandard                      |
    |  program                               |
    |  calendarDate                          |
