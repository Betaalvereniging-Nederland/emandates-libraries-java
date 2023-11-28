
package schemas.pain010;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FrequencyPeriod1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FrequencyPeriod1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tp" type="{urn:iso:std:iso:20022:tech:xsd:pain.010.001.04}Frequency6Code"/>
 *         &lt;element name="CntPerPrd" type="{urn:iso:std:iso:20022:tech:xsd:pain.010.001.04}DecimalNumber"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FrequencyPeriod1", propOrder = {
    "tp",
    "cntPerPrd"
})
public class FrequencyPeriod1 {

    @XmlElement(name = "Tp", required = true)
    protected Frequency6Code tp;
    @XmlElement(name = "CntPerPrd", required = true)
    protected BigDecimal cntPerPrd;

    /**
     * Gets the value of the tp property.
     * 
     * @return
     *     possible object is
     *     {@link Frequency6Code }
     *     
     */
    public Frequency6Code getTp() {
        return tp;
    }

    /**
     * Sets the value of the tp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Frequency6Code }
     *     
     */
    public void setTp(Frequency6Code value) {
        this.tp = value;
    }

    /**
     * Gets the value of the cntPerPrd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCntPerPrd() {
        return cntPerPrd;
    }

    /**
     * Sets the value of the cntPerPrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCntPerPrd(BigDecimal value) {
        this.cntPerPrd = value;
    }

}
