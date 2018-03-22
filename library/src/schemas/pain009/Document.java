
package schemas.pain009;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MndtInitnReq" type="{urn:iso:std:iso:20022:tech:xsd:pain.009.001.04}MandateInitiationRequestV04"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "mndtInitnReq"
})
@XmlRootElement(name = "Document")
public class Document {

    @XmlElement(name = "MndtInitnReq", required = true)
    protected MandateInitiationRequestV04 mndtInitnReq;

    /**
     * Gets the value of the mndtInitnReq property.
     * 
     * @return
     *     possible object is
     *     {@link MandateInitiationRequestV04 }
     *     
     */
    public MandateInitiationRequestV04 getMndtInitnReq() {
        return mndtInitnReq;
    }

    /**
     * Sets the value of the mndtInitnReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandateInitiationRequestV04 }
     *     
     */
    public void setMndtInitnReq(MandateInitiationRequestV04 value) {
        this.mndtInitnReq = value;
    }

}
