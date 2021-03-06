/****** Script for SelectTopNRows command from SSMS  ******/
SELECT sa.StudentUSI
	  ,aic.AssessmentTitle
      ,aic.AcademicSubjectTypeId
      ,aic.AssessedGradeLevelTypeId
      ,aic.Version
      ,sa.AdministrationDate
      ,aist.CodeValue as AssessmentIdentificationSystem
      ,aic.AssigningOrganizationCode
      ,aic.IdentificationCode
  FROM EdFi.edfi.AssessmentIdentificationCode aic
  RIGHT JOIN EdFi.edfi.StudentAssessment sa 
	ON sa.AssessmentTitle = aic.AssessmentTitle
		AND sa.AcademicSubjectTypeId = aic.AcademicSubjectTypeId
		AND sa.AssessedGradeLevelTypeId = aic.AssessedGradeLevelTypeId
		AND sa.Version = aic.Version
  LEFT JOIN EdFi.edfi.AssessmentIdentificationSystemType aist ON aic.AssessmentIdentificationSystemTypeId = aist.AssessmentIdentificationSystemTypeId
  ORDER BY sa.StudentUSI, aic.AssessmentTitle, aic.AcademicSubjectTypeId, aic.AssessedGradeLevelTypeId, aic.Version, sa.AdministrationDate
