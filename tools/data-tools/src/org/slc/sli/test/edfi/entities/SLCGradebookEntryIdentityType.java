//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.06 at 10:00:50 AM EST 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * New SLC identity type for GradebookEntry. Contains the keyfields: GradebookEntryType, DateAssigned and an embedded SectionReference.
 * 
 * <p>Java class for SLC-GradebookEntryIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SLC-GradebookEntryIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GradebookEntryType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateAssigned" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SectionReference" type="{http://ed-fi.org/0100}SLC-SectionReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-GradebookEntryIdentityType", propOrder = {
    "gradebookEntryType",
    "dateAssigned",
    "sectionReference"
})
public class SLCGradebookEntryIdentityType {

    @XmlElement(name = "GradebookEntryType", required = true)
    protected String gradebookEntryType;
    @XmlElement(name = "DateAssigned", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String dateAssigned;
    @XmlElement(name = "SectionReference", required = true)
    protected SLCSectionReferenceType sectionReference;

    /**
     * Gets the value of the gradebookEntryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradebookEntryType() {
        return gradebookEntryType;
    }

    /**
     * Sets the value of the gradebookEntryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradebookEntryType(String value) {
        this.gradebookEntryType = value;
    }

    /**
     * Gets the value of the dateAssigned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateAssigned() {
        return dateAssigned;
    }

    /**
     * Sets the value of the dateAssigned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateAssigned(String value) {
        this.dateAssigned = value;
    }

    /**
     * Gets the value of the sectionReference property.
     * 
     * @return
     *     possible object is
     *     {@link SLCSectionReferenceType }
     *     
     */
    public SLCSectionReferenceType getSectionReference() {
        return sectionReference;
    }

    /**
     * Sets the value of the sectionReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLCSectionReferenceType }
     *     
     */
    public void setSectionReference(SLCSectionReferenceType value) {
        this.sectionReference = value;
    }

}
