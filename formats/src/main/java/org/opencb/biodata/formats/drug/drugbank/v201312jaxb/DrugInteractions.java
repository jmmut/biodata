//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 04:12:32 PM CET 
//


package org.opencb.biodata.formats.drug.drugbank.v201312jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://drugbank.ca}drug-interaction"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "drugInteraction"
})
@XmlRootElement(name = "drug-interactions")
public class DrugInteractions {

    @XmlElement(name = "drug-interaction")
    protected List<DrugInteraction> drugInteraction;

    /**
     * Gets the value of the drugInteraction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugInteraction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugInteraction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugInteraction }
     * 
     * 
     */
    public List<DrugInteraction> getDrugInteraction() {
        if (drugInteraction == null) {
            drugInteraction = new ArrayList<DrugInteraction>();
        }
        return this.drugInteraction;
    }

}