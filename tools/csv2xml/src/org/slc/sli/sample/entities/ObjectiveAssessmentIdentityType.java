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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Encapsulates the possible attributes that can be used to lookup the identity of assessment objectives.
 * 
 * <p>Java class for ObjectiveAssessmentIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectiveAssessmentIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectiveAssessmentIdentificationCode" type="{http://ed-fi.org/0100}IdentificationCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectiveAssessmentIdentityType", propOrder = {
    "objectiveAssessmentIdentificationCode"
})
public class ObjectiveAssessmentIdentityType {

    @XmlElement(name = "ObjectiveAssessmentIdentificationCode", required = true)
    protected String objectiveAssessmentIdentificationCode;

    /**
     * Gets the value of the objectiveAssessmentIdentificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectiveAssessmentIdentificationCode() {
        return objectiveAssessmentIdentificationCode;
    }

    /**
     * Sets the value of the objectiveAssessmentIdentificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectiveAssessmentIdentificationCode(String value) {
        this.objectiveAssessmentIdentificationCode = value;
    }

}