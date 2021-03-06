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
package org.slc.sli.bulk.extract.context.resolver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slc.sli.common.constants.EntityNames;

/**
 * Staff / Teacher related entities resolver
 * 
 * @author ycao
 * 
 */
@Component
public class StaffTeacherDirectRelatedContextResolver extends RelatedContextResolver {
    public static final String STAFF_ID = "staffId";
    public static final String TEACHER_ID = "teacherId";
    
    @Autowired
    private StaffTeacherContextResolver staffTeacherResolver;
    
    @Override
    protected String getReferenceProperty(String entityType) {
        if (EntityNames.TEACHER_SCHOOL_ASSOCIATION.equals(entityType)
                || EntityNames.TEACHER_SECTION_ASSOCIATION.equals(entityType)) {
            return TEACHER_ID;
        }
        
        if (EntityNames.STAFF_ED_ORG_ASSOCIATION.equals(entityType)) {
            return StaffTeacherContextResolver.STAFF_REFERENCE;
        }
        
        if (EntityNames.STAFF_COHORT_ASSOCIATION.equals(entityType)
                || EntityNames.STAFF_PROGRAM_ASSOCIATION.equals(entityType)) {
            // curse you edfi!!!!!
            return STAFF_ID;
        }
        
        return null;
    }
    
    @Override
    protected ReferrableResolver getReferredResolver() {
        return staffTeacherResolver;
    }
    
}
