//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.31 at 10:43:34 AM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for behaviorMapType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="behaviorMapType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="State Law Crime"/>
 *     &lt;enumeration value="State Offense"/>
 *     &lt;enumeration value="School Violation"/>
 *     &lt;enumeration value="School Code of Conduct"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "behaviorMapType")
@XmlEnum
public enum BehaviorMapType {

    @XmlEnumValue("State Law Crime")
    STATE_LAW_CRIME("State Law Crime"),
    @XmlEnumValue("State Offense")
    STATE_OFFENSE("State Offense"),
    @XmlEnumValue("School Violation")
    SCHOOL_VIOLATION("School Violation"),
    @XmlEnumValue("School Code of Conduct")
    SCHOOL_CODE_OF_CONDUCT("School Code of Conduct");
    private final String value;

    BehaviorMapType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BehaviorMapType fromValue(String v) {
        for (BehaviorMapType c: BehaviorMapType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}