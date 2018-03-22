
package schemas.pain012;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MandateAcceptance4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MandateAcceptance4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrgnlMsgInf" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}OriginalMessageInformation1" minOccurs="0"/>
 *         &lt;element name="AccptncRslt" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}AcceptanceResult6"/>
 *         &lt;element name="OrgnlMndt" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}OriginalMandate3Choice"/>
 *         &lt;element name="SplmtryData" type="{urn:iso:std:iso:20022:tech:xsd:pain.012.001.04}SupplementaryData1" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandateAcceptance4", propOrder = {
    "orgnlMsgInf",
    "accptncRslt",
    "orgnlMndt",
    "splmtryData"
})
public class MandateAcceptance4 {

    @XmlElement(name = "OrgnlMsgInf")
    protected OriginalMessageInformation1 orgnlMsgInf;
    @XmlElement(name = "AccptncRslt", required = true)
    protected AcceptanceResult6 accptncRslt;
    @XmlElement(name = "OrgnlMndt", required = true)
    protected OriginalMandate3Choice orgnlMndt;
    @XmlElement(name = "SplmtryData")
    protected List<SupplementaryData1> splmtryData;

    /**
     * Gets the value of the orgnlMsgInf property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalMessageInformation1 }
     *     
     */
    public OriginalMessageInformation1 getOrgnlMsgInf() {
        return orgnlMsgInf;
    }

    /**
     * Sets the value of the orgnlMsgInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalMessageInformation1 }
     *     
     */
    public void setOrgnlMsgInf(OriginalMessageInformation1 value) {
        this.orgnlMsgInf = value;
    }

    /**
     * Gets the value of the accptncRslt property.
     * 
     * @return
     *     possible object is
     *     {@link AcceptanceResult6 }
     *     
     */
    public AcceptanceResult6 getAccptncRslt() {
        return accptncRslt;
    }

    /**
     * Sets the value of the accptncRslt property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptanceResult6 }
     *     
     */
    public void setAccptncRslt(AcceptanceResult6 value) {
        this.accptncRslt = value;
    }

    /**
     * Gets the value of the orgnlMndt property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalMandate3Choice }
     *     
     */
    public OriginalMandate3Choice getOrgnlMndt() {
        return orgnlMndt;
    }

    /**
     * Sets the value of the orgnlMndt property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalMandate3Choice }
     *     
     */
    public void setOrgnlMndt(OriginalMandate3Choice value) {
        this.orgnlMndt = value;
    }

    /**
     * Gets the value of the splmtryData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the splmtryData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSplmtryData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplementaryData1 }
     * 
     * 
     */
    public List<SupplementaryData1> getSplmtryData() {
        if (splmtryData == null) {
            splmtryData = new ArrayList<SupplementaryData1>();
        }
        return this.splmtryData;
    }

}
