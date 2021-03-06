@RALLY_US3369
Feature: SIF EmploymentRecord Test

Background: Set my data store
Given the data store is "data_EmploymentRecord"

Scenario: Add an EmploymentRecord for a staff and educationOrganization
Given I wait for "10" seconds
And the following collections are clean and bootstrapped in datastore:
     | collectionName                        |
     | educationOrganization                 |
     | staff                                 |
     | staffEducationOrganizationAssociation |
     | teacherSchoolAssociation              |
     | custom_entities                       |
And the fixture data "sif_lea_fixture" has been imported into collection "educationOrganization"
And the fixture data "sif_staff_fixture" has been imported into collection "staff"
And the fixture data "sif_bootstrap_custom_entity_fixture" has been imported into collection "custom_entities"
And I want to POST a(n) "sifEvent_EmploymentRecord_add_staff_educationOrganization" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
   And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012vv-cd444c5b-dcd7-11e1-992f-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                                        |
     | staffEducationOrganizationAssociation | body.staffReference | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_add_staff_educationOrganization |

Scenario: Update an EmploymentRecord for a staff and educationOrganization
Given I want to POST a(n) "sifEvent_EmploymentRecord_change_staff_educationOrganization" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
  And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012vv-cd444c5b-dcd7-11e1-992f-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                                           |
     | staffEducationOrganizationAssociation | body.staffReference | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_change_staff_educationOrganization |

Scenario: Add an EmploymentRecord for a staff and school
Given the following collections are clean and bootstrapped in datastore:
     | collectionName                        |
     | educationOrganization                 |
     | staff                                 |
     | staffEducationOrganizationAssociation |
     | teacherSchoolAssociation              |
     | custom_entities                       |
And the fixture data "sif_educationOrganization_fixture" has been imported into collection "educationOrganization"
And the fixture data "sif_staff_fixture" has been imported into collection "staff"
And the fixture data "sif_bootstrap_custom_entity_fixture" has been imported into collection "custom_entities"
And I want to POST a(n) "sifEvent_EmploymentRecord_add_staff_school" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
   And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012av-6dcc2939-dcc5-11e1-95f6-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                         |
     | staffEducationOrganizationAssociation | body.staffReference | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_add_staff_school |

Scenario: Update an EmploymentRecord for a staff and school
Given I want to POST a(n) "sifEvent_EmploymentRecord_change_staff_school" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
  And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012av-6dcc2939-dcc5-11e1-95f6-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                            |
     | staffEducationOrganizationAssociation | body.staffReference | 2012fw-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_change_staff_school |

Scenario: Add an EmploymentRecord for a teacher and educationOrganization
Given the following collections are clean and bootstrapped in datastore:
     | collectionName                        |
     | educationOrganization                 |
     | staff                                 |
     | staffEducationOrganizationAssociation |
     | teacherSchoolAssociation              |
     | custom_entities                       |
And the fixture data "sif_educationOrganization_fixture" has been imported into collection "educationOrganization"
And the fixture data "sif_teacher_fixture" has been imported into collection "staff"
And the fixture data "sif_bootstrap_custom_entity_fixture" has been imported into collection "custom_entities"
And I want to POST a(n) "sifEvent_EmploymentRecord_add_teacher_educationOrganization" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
   And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012vm-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012fx-6dc8a6c8-dcc5-11e1-95f6-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                                          |
     | staffEducationOrganizationAssociation | body.staffReference | 2012vm-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_add_teacher_educationOrganization |

Scenario: Update an EmploymentRecord for a teacher and educationOrganization
Given I want to POST a(n) "sifEvent_EmploymentRecord_change_teacher_educationOrganization" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 2     |
     | teacherSchoolAssociation              | 0     |
   And I check to find if record is in collection:
     | collectionName                        | expectedRecordCount | searchParameter                     | searchValue                                 | searchType |
     | staffEducationOrganizationAssociation | 1                   | body.staffReference                 | 2012vm-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     |
     | staffEducationOrganizationAssociation | 1                   | body.educationOrganizationReference | 2012fx-6dc8a6c8-dcc5-11e1-95f6-0021701f543f | string     |
   And I check that the record contains all of the expected values:
     | collectionName                        | searchParameter     | searchValue                                 | searchType | expectedValuesFile                                             |
     | staffEducationOrganizationAssociation | body.staffReference | 2012vm-e2efce80-eb85-11e1-b59f-406c8f06bd30 | string     | expected_EmploymentRecord_change_teacher_educationOrganization |



Scenario: Negative Testing - Add an EmploymentRecord which is missing SLI required fields 2: missing reference
Given the following collections are clean and bootstrapped in datastore:
     | collectionName                        |
     | educationOrganization                 |
     | staff                                 |
     | staffEducationOrganizationAssociation |
     | teacherSchoolAssociation              |
     | custom_entities                       |
And the fixture data "sif_educationOrganization_fixture" has been imported into collection "educationOrganization"
And the fixture data "sif_staff_fixture" has been imported into collection "staff"
And the fixture data "sif_teacher_fixture" has been imported into collection "staff"
And the fixture data "sif_bootstrap_custom_entity_fixture" has been imported into collection "custom_entities"
And I want to POST a(n) "sifEvent_EmploymentRecord_add_staff_educationOrganization_missing_reference" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 1     |
     | teacherSchoolAssociation              | 0     |

Scenario: Negative Testing - Update an EmploymentRecord which doesn't exist - staff and educationOrganization
Given the following collections are clean and bootstrapped in datastore:
     | collectionName                        |
     | educationOrganization                 |
     | staff                                 |
     | staffEducationOrganizationAssociation |
     | teacherSchoolAssociation              |
     | custom_entities                       |
And the fixture data "sif_educationOrganization_fixture" has been imported into collection "educationOrganization"
And the fixture data "sif_staff_fixture" has been imported into collection "staff"
And the fixture data "sif_teacher_fixture" has been imported into collection "staff"
And the fixture data "sif_bootstrap_custom_entity_fixture" has been imported into collection "custom_entities"
And I want to POST a(n) "sifEvent_EmploymentRecord_change_staff_educationOrganization" SIF message
When I POST the message to the ZIS
And I wait for "3" seconds
Then I should see following map of entry counts in the corresponding collections:
     | collectionName                        | count |
     | staffEducationOrganizationAssociation | 1     |
     | teacherSchoolAssociation              | 0     |


