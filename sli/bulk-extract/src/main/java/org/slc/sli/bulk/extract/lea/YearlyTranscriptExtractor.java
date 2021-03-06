/*
 * Copyright 2012 Shared Learning Collaborative, LLC
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

package org.slc.sli.bulk.extract.lea;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.slc.sli.bulk.extract.extractor.EntityExtractor;
import org.slc.sli.bulk.extract.util.LocalEdOrgExtractHelper;
import org.slc.sli.common.constants.EntityNames;
import org.slc.sli.common.constants.ParameterConstants;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralQuery;
import org.slc.sli.domain.Repository;

public class YearlyTranscriptExtractor implements EntityExtract {
    private EntityExtractor extractor;
    private LEAExtractFileMap map;
    private Repository<Entity> repo;
    private LocalEdOrgExtractHelper localEdOrgExtractHelper;
    private EntityToLeaCache studentAcademicRecordCache;
    
    public YearlyTranscriptExtractor(EntityExtractor extractor, LEAExtractFileMap map, Repository<Entity> repo, 
            LocalEdOrgExtractHelper localEdOrgExtractHelper, EntityToLeaCache studentAcademicRecordCache) {
        this.extractor = extractor;
        this.map = map;
        this.repo = repo;
        this.localEdOrgExtractHelper = localEdOrgExtractHelper;
        this.studentAcademicRecordCache = studentAcademicRecordCache;    
    }
    
    @Override
    public void extractEntities(EntityToLeaCache entityToEdorgCache) {
        localEdOrgExtractHelper.logSecurityEvent(map.getLeas(), "yearlyTranscript", this.getClass().getName());
        Iterator<Entity> yearlyTranscripts = repo.findEach("yearlyTranscript", new NeutralQuery());
        
        while (yearlyTranscripts.hasNext()) {
            Entity yearlyTranscript = yearlyTranscripts.next();
            String studentId = (String) yearlyTranscript.getBody().get(ParameterConstants.STUDENT_ID);
            Set<String> studentLeas = entityToEdorgCache.getEntriesById(studentId);
            Set<String> studentAcademicRecords = fetchStudentAcademicRecordsFromYearlyTranscript(yearlyTranscript);
            for (String lea : studentLeas) {
                extractor.extractEntity(yearlyTranscript, map.getExtractFileForLea(lea), "yearlyTranscript");
                
                for (String studentAcademicRecord : studentAcademicRecords) {
                    studentAcademicRecordCache.addEntry(studentAcademicRecord, lea);
                }
            }
        }
        
    }
    
    /**
     * returns all parents of the student
     * @param student
     * @return
     */
    private Set<String> fetchStudentAcademicRecordsFromYearlyTranscript(Entity yearlyTranscript) {
        Set<String> records = new TreeSet<String>();
        if (yearlyTranscript.getEmbeddedData().containsKey(EntityNames.STUDENT_ACADEMIC_RECORD)) {
            for (Entity sar : yearlyTranscript.getEmbeddedData().get(EntityNames.STUDENT_ACADEMIC_RECORD)) {
                records.add(sar.getEntityId());
            }
        }
        return records;
    }
    
    /**
     * Get the cache of studentAcademicRecordIds to a list of LEA IDs that these records were extracted to
     * @return
     */
    public EntityToLeaCache getStudentAcademicRecordCache(){
        return studentAcademicRecordCache;
    }
}
