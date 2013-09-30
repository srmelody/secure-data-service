//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.05 at 01:12:38 PM EST 
//


package org.slc.sli.sample.entitiesR1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ElectronicMailAddressType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ElectronicMailAddressType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Home/Personal"/>
 *     &lt;enumeration value="Organization"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Work"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ElectronicMailAddressType")
@XmlEnum
public enum ElectronicMailAddressType {

    @XmlEnumValue("Home/Personal")
    HOME_PERSONAL("Home/Personal"),
    @XmlEnumValue("Organization")
    ORGANIZATION("Organization"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Work")
    WORK("Work");
    private final String value;

    ElectronicMailAddressType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ElectronicMailAddressType fromValue(String v) {
        for (ElectronicMailAddressType c: ElectronicMailAddressType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
