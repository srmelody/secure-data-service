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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * An indicator or metric computed for the student
 *                 (e.g., at risk) to
 *                 influence more effective education or direct
 *                 specific interventions.
 *             
 * 
 * <p>Java class for studentIndicator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="studentIndicator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="indicatorGroup" type="{http://slc-sli/ed-org/0.1}indicatorName" minOccurs="0"/>
 *         &lt;element name="indicatorName" type="{http://slc-sli/ed-org/0.1}indicatorName"/>
 *         &lt;element name="indicator" type="{http://slc-sli/ed-org/0.1}indicator"/>
 *         &lt;element name="beginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="designatedBy" type="{http://slc-sli/ed-org/0.1}designatedBy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studentIndicator", propOrder = {
    "indicatorGroup",
    "indicatorName",
    "indicator",
    "beginDate",
    "endDate",
    "designatedBy"
})
public class StudentIndicator {

    protected String indicatorGroup;
    @XmlElement(required = true)
    protected String indicatorName;
    @XmlElement(required = true)
    protected String indicator;
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String beginDate;
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String endDate;
    protected String designatedBy;

    /**
     * Gets the value of the indicatorGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicatorGroup() {
        return indicatorGroup;
    }

    /**
     * Sets the value of the indicatorGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicatorGroup(String value) {
        this.indicatorGroup = value;
    }

    /**
     * Gets the value of the indicatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicatorName() {
        return indicatorName;
    }

    /**
     * Sets the value of the indicatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicatorName(String value) {
        this.indicatorName = value;
    }

    /**
     * Gets the value of the indicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * Sets the value of the indicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicator(String value) {
        this.indicator = value;
    }

    /**
     * Gets the value of the beginDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the beginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the designatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignatedBy() {
        return designatedBy;
    }

    /**
     * Sets the value of the designatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignatedBy(String value) {
        this.designatedBy = value;
    }

}