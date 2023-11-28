
package schemas.idx;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createDateTimestamp" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}dateTime"/>
 *         &lt;element name="Merchant">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="merchantID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.merchantID"/>
 *                   &lt;element name="subID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.subID"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}MessageAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createDateTimestamp",
    "merchant",
    "signature"
})
@XmlRootElement(name = "DirectoryReq", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
public class DirectoryReq {

    @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected XMLGregorianCalendar createDateTimestamp;
    @XmlElement(name = "Merchant", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected DirectoryReq.Merchant merchant;
    @XmlElement(name = "Signature", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "productID", required = true)
    protected String productID;

    /**
     * Gets the value of the createDateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDateTimestamp() {
        return createDateTimestamp;
    }

    /**
     * Sets the value of the createDateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDateTimestamp(XMLGregorianCalendar value) {
        this.createDateTimestamp = value;
    }

    /**
     * Gets the value of the merchant property.
     * 
     * @return
     *     possible object is
     *     {@link DirectoryReq.Merchant }
     *     
     */
    public DirectoryReq.Merchant getMerchant() {
        return merchant;
    }

    /**
     * Sets the value of the merchant property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectoryReq.Merchant }
     *     
     */
    public void setMerchant(DirectoryReq.Merchant value) {
        this.merchant = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="merchantID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.merchantID"/>
     *         &lt;element name="subID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.subID"/>
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
        "merchantID",
        "subID"
    })
    public static class Merchant {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String merchantID;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected int subID;

        /**
         * Gets the value of the merchantID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMerchantID() {
            return merchantID;
        }

        /**
         * Sets the value of the merchantID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMerchantID(String value) {
            this.merchantID = value;
        }

        /**
         * Gets the value of the subID property.
         * 
         */
        public int getSubID() {
            return subID;
        }

        /**
         * Sets the value of the subID property.
         * 
         */
        public void setSubID(int value) {
            this.subID = value;
        }

    }

}
