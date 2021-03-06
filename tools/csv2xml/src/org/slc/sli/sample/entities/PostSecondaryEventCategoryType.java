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


//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.20 at 03:09:04 PM EDT 
//


package org.slc.sli.sample.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostSecondaryEventCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PostSecondaryEventCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="College Application"/>
 *     &lt;enumeration value="College Acceptance"/>
 *     &lt;enumeration value="College Enrollment"/>
 *     &lt;enumeration value="FAFSA Application"/>
 *     &lt;enumeration value="Vocation Certification"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PostSecondaryEventCategoryType")
@XmlEnum
public enum PostSecondaryEventCategoryType {

    @XmlEnumValue("College Application")
    COLLEGE_APPLICATION("College Application"),
    @XmlEnumValue("College Acceptance")
    COLLEGE_ACCEPTANCE("College Acceptance"),
    @XmlEnumValue("College Enrollment")
    COLLEGE_ENROLLMENT("College Enrollment"),
    @XmlEnumValue("FAFSA Application")
    FAFSA_APPLICATION("FAFSA Application"),
    @XmlEnumValue("Vocation Certification")
    VOCATION_CERTIFICATION("Vocation Certification");
    private final String value;

    PostSecondaryEventCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PostSecondaryEventCategoryType fromValue(String v) {
        for (PostSecondaryEventCategoryType c: PostSecondaryEventCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
