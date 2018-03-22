
package schemas.pain010;

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
 *         &lt;element name="MndtAmdmntReq" type="{urn:iso:std:iso:20022:tech:xsd:pain.010.001.04}MandateAmendmentRequestV04"/>
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
    "mndtAmdmntReq"
})
@XmlRootElement(name = "Document", namespace = "urn:iso:std:iso:20022:tech:xsd:pain.010.001.04")
public class Document {

    @XmlElement(name = "MndtAmdmntReq", required = true)
    protected MandateAmendmentRequestV04 mndtAmdmntReq;

    /**
     * Gets the value of the mndtAmdmntReq property.
     * 
     * @return
     *     possible object is
     *     {@link MandateAmendmentRequestV04 }
     *     
     */
    public MandateAmendmentRequestV04 getMndtAmdmntReq() {
        return mndtAmdmntReq;
    }

    /**
     * Sets the value of the mndtAmdmntReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandateAmendmentRequestV04 }
     *     
     */
    public void setMndtAmdmntReq(MandateAmendmentRequestV04 value) {
        this.mndtAmdmntReq = value;
    }

}
