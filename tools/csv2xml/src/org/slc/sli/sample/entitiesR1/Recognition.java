//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.05 at 01:12:38 PM EST 
//


package org.slc.sli.sample.entitiesR1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Recognition given to the student for accomplishments in a co-curricular, or extra-curricular activity.
 * 
 * <p>Java class for Recognition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Recognition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecognitionType" type="{http://ed-fi.org/0100}RecognitionType"/>
 *         &lt;element name="RecognitionDescription" type="{http://ed-fi.org/0100}RecognitionDescription" minOccurs="0"/>
 *         &lt;element name="RecognitionAwardDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recognition", propOrder = {
    "recognitionType",
    "recognitionDescription",
    "recognitionAwardDate"
})
public class Recognition {

    @XmlElement(name = "RecognitionType", required = true)
    protected RecognitionType recognitionType;
    @XmlElement(name = "RecognitionDescription")
    protected String recognitionDescription;
    @XmlElement(name = "RecognitionAwardDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar recognitionAwardDate;

    /**
     * Gets the value of the recognitionType property.
     * 
     * @return
     *     possible object is
     *     {@link RecognitionType }
     *     
     */
    public RecognitionType getRecognitionType() {
        return recognitionType;
    }

    /**
     * Sets the value of the recognitionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecognitionType }
     *     
     */
    public void setRecognitionType(RecognitionType value) {
        this.recognitionType = value;
    }

    /**
     * Gets the value of the recognitionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognitionDescription() {
        return recognitionDescription;
    }

    /**
     * Sets the value of the recognitionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognitionDescription(String value) {
        this.recognitionDescription = value;
    }

    /**
     * Gets the value of the recognitionAwardDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecognitionAwardDate() {
        return recognitionAwardDate;
    }

    /**
     * Sets the value of the recognitionAwardDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecognitionAwardDate(XMLGregorianCalendar value) {
        this.recognitionAwardDate = value;
    }

}