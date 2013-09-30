//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.06 at 10:00:50 AM EST 
//


package org.slc.sli.test.edfi.entities;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This financial entity represents the sum of the financial transactions to date for employee compensation.  An "employee" who performs services under the direction of the employing institution or agency, is compensated for such services by the employer, and is eligible for employee benefits and wage or salary tax withholdings.
 * 
 * <p>Java class for Payroll complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Payroll">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ed-fi.org/0100}ComplexObjectType">
 *       &lt;sequence>
 *         &lt;element name="AmountToDate" type="{http://ed-fi.org/0100}Currency"/>
 *         &lt;element name="AsOfDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="AccountReference" type="{http://ed-fi.org/0100}AccountReferenceType"/>
 *         &lt;element name="StaffReference" type="{http://ed-fi.org/0100}StaffReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payroll", propOrder = {
    "amountToDate",
    "asOfDate",
    "accountReference",
    "staffReference"
})
public class Payroll
    extends ComplexObjectType
{

    @XmlElement(name = "AmountToDate", required = true)
    protected BigDecimal amountToDate;
    @XmlElement(name = "AsOfDate", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String asOfDate;
    @XmlElement(name = "AccountReference", required = true)
    protected AccountReferenceType accountReference;
    @XmlElement(name = "StaffReference", required = true)
    protected StaffReferenceType staffReference;

    /**
     * Gets the value of the amountToDate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountToDate() {
        return amountToDate;
    }

    /**
     * Sets the value of the amountToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountToDate(BigDecimal value) {
        this.amountToDate = value;
    }

    /**
     * Gets the value of the asOfDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsOfDate() {
        return asOfDate;
    }

    /**
     * Sets the value of the asOfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsOfDate(String value) {
        this.asOfDate = value;
    }

    /**
     * Gets the value of the accountReference property.
     * 
     * @return
     *     possible object is
     *     {@link AccountReferenceType }
     *     
     */
    public AccountReferenceType getAccountReference() {
        return accountReference;
    }

    /**
     * Sets the value of the accountReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReferenceType }
     *     
     */
    public void setAccountReference(AccountReferenceType value) {
        this.accountReference = value;
    }

    /**
     * Gets the value of the staffReference property.
     * 
     * @return
     *     possible object is
     *     {@link StaffReferenceType }
     *     
     */
    public StaffReferenceType getStaffReference() {
        return staffReference;
    }

    /**
     * Sets the value of the staffReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffReferenceType }
     *     
     */
    public void setStaffReference(StaffReferenceType value) {
        this.staffReference = value;
    }

}