//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.22 at 01:42:02 PM EST 
//


package org.ed_fi._0100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="StudentAssessment" type="{http://ed-fi.org/0100}SLC-StudentAssessment"/>
 *         &lt;element name="StudentObjectiveAssessment" type="{http://ed-fi.org/0100}SLC-StudentObjectiveAssessment"/>
 *         &lt;element name="StudentAssessmentItem" type="{http://ed-fi.org/0100}SLC-StudentAssessmentItem"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem"
})
@XmlRootElement(name = "InterchangeStudentAssessment")
public class InterchangeStudentAssessment {

    @XmlElements({
        @XmlElement(name = "StudentAssessmentItem", type = SLCStudentAssessmentItem.class),
        @XmlElement(name = "StudentAssessment", type = SLCStudentAssessment.class),
        @XmlElement(name = "StudentObjectiveAssessment", type = SLCStudentObjectiveAssessment.class)
    })
    protected List<ComplexObjectType> studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem;

    /**
     * Gets the value of the studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SLCStudentAssessmentItem }
     * {@link SLCStudentAssessment }
     * {@link SLCStudentObjectiveAssessment }
     * 
     * 
     */
    public List<ComplexObjectType> getStudentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem() {
        if (studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem == null) {
            studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem = new ArrayList<ComplexObjectType>();
        }
        return this.studentAssessmentOrStudentObjectiveAssessmentOrStudentAssessmentItem;
    }

}
