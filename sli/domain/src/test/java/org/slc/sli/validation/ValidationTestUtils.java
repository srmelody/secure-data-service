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


package org.slc.sli.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slc.sli.domain.CalculatedData;
import org.slc.sli.domain.Entity;


/**
 * Various utility functions for test
 *
 * @author nbrown
 *
 */
public class ValidationTestUtils {

    public static Entity makeDummyEntity(final String type, final String id) {
        return new Entity() {
            Map<String, Object> body = new HashMap<String, Object>();
            @Override
            public String getType() {
                return type;
            }

            @Override
            public Map<String, Object> getMetaData() {
                return new HashMap<String, Object>();
            }

            @Override
            public String getEntityId() {
                return id;
            }

            @Override
            public Map<String, Object> getBody() {
                return body;
            }

            @Override
            public CalculatedData<String> getCalculatedValues() {
                return null;
            }

            @Override
            public CalculatedData<Map<String, Integer>> getAggregates() {
                return null;
            }

            @Override
            public Map<String, List<Entity>> getEmbeddedData() {
                return null;
            }

            @Override
            public Map<String, List<Map<String, Object>>> getDenormalizedData() {
                return null;
            }

            @Override
            public String getStagedEntityId() {
                return null;
            }

            @Override
            public Map<String, List<Entity>> getContainerData() {
                return null;
            }

            @Override
            public void hollowOut() {
                // override super implementation with empty implementation
            }
       };
    }

}
