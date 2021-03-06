/****** Script for SelectTopNRows command from SSMS  ******/
SELECT cls.EducationOrganizationId
       ,cls.IdentityCourseCode
       ,cls.LearningStandardId as LearningStandardIdIdentificationCode
       ,cst.CodeValue as LearningStandardIdContentStandardName
  FROM EdFi.edfi.CourseLearningStandard cls
  LEFT JOIN EdFi.edfi.LearningStandard ls ON cls.LearningStandardId = ls.LearningStandardId
  LEFT JOIN EdFi.edfi.ContentStandardType cst ON ls.ContentStandardTypeId = cst.ContentStandardTypeId
  ORDER BY cls.EducationOrganizationId, cls.IdentityCourseCode