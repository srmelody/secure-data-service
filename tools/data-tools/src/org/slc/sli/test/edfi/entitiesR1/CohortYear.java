//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.31 at 10:43:34 AM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The type and year of a cohort (e.g., 9th grade) the
 *                 student belongs
 *                 to.
 *             
 * 
 * <p>Java class for cohortYear complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cohortYear">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schoolYear" type="{http://slc-sli/ed-org/0.1}schoolYearType"/>
 *         &lt;element name="cohortYearType" type="{http://slc-sli/ed-org/0.1}cohortYearType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cohortYear", propOrder = {
    "schoolYear",
    "cohortYearType"
})
public class CohortYear {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolYear;
    @XmlElement(required = true)
    protected CohortYearType cohortYearType;

    /**
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolYear(String value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the cohortYearType property.
     * 
     * @return
     *     possible object is
     *     {@link CohortYearType }
     *     
     */
    public CohortYearType getCohortYearType() {
        return cohortYearType;
    }

    /**
     * Sets the value of the cohortYearType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CohortYearType }
     *     
     */
    public void setCohortYearType(CohortYearType value) {
        this.cohortYearType = value;
    }

}