
package schemas.pain012;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcceptanceResult6 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcceptanceResult6">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Accptd" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}YesNoIndicator"/>
 *         &lt;element name="RjctRsn" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}MandateReason1Choice" minOccurs="0"/>
 *         &lt;element name="AddtlRjctRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}Max105Text" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcceptanceResult6", propOrder = {
    "accptd",
    "rjctRsn",
    "addtlRjctRsnInf"
})
public class AcceptanceResult6 {

    @XmlElement(name = "Accptd")
    protected boolean accptd;
    @XmlElement(name = "RjctRsn")
    protected MandateReason1Choice rjctRsn;
    @XmlElement(name = "AddtlRjctRsnInf")
    protected List<String> addtlRjctRsnInf;

    /**
     * Gets the value of the accptd property.
     * 
     */
    public boolean isAccptd() {
        return accptd;
    }

    /**
     * Sets the value of the accptd property.
     * 
     */
    public void setAccptd(boolean value) {
        this.accptd = value;
    }

    /**
     * Gets the value of the rjctRsn property.
     * 
     * @return
     *     possible object is
     *     {@link MandateReason1Choice }
     *     
     */
    public MandateReason1Choice getRjctRsn() {
        return rjctRsn;
    }

    /**
     * Sets the value of the rjctRsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandateReason1Choice }
     *     
     */
    public void setRjctRsn(MandateReason1Choice value) {
        this.rjctRsn = value;
    }

    /**
     * Gets the value of the addtlRjctRsnInf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addtlRjctRsnInf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddtlRjctRsnInf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAddtlRjctRsnInf() {
        if (addtlRjctRsnInf == null) {
            addtlRjctRsnInf = new ArrayList<String>();
        }
        return this.addtlRjctRsnInf;
    }

}
