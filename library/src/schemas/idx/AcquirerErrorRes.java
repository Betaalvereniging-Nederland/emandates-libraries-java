
package schemas.idx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *         &lt;element name="Error">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="errorCode" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorCode"/>
 *                   &lt;element name="errorMessage" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorMessage"/>
 *                   &lt;element name="errorDetail" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorDetail" minOccurs="0"/>
 *                   &lt;element name="suggestedAction" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.suggestedAction" minOccurs="0"/>
 *                   &lt;element name="consumerMessage" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.consumerMessage" minOccurs="0"/>
 *                   &lt;element name="container" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.container" minOccurs="0"/>
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
    "error",
    "signature"
})
@XmlRootElement(name = "AcquirerErrorRes", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
public class AcquirerErrorRes {

    @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected XMLGregorianCalendar createDateTimestamp;
    @XmlElement(name = "Error", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerErrorRes.Error error;
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
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerErrorRes.Error }
     *     
     */
    public AcquirerErrorRes.Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerErrorRes.Error }
     *     
     */
    public void setError(AcquirerErrorRes.Error value) {
        this.error = value;
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
     *         &lt;element name="errorCode" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorCode"/>
     *         &lt;element name="errorMessage" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorMessage"/>
     *         &lt;element name="errorDetail" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.errorDetail" minOccurs="0"/>
     *         &lt;element name="suggestedAction" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.suggestedAction" minOccurs="0"/>
     *         &lt;element name="consumerMessage" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Error.consumerMessage" minOccurs="0"/>
     *         &lt;element name="container" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.container" minOccurs="0"/>
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
        "errorCode",
        "errorMessage",
        "errorDetail",
        "suggestedAction",
        "consumerMessage",
        "container"
    })
    public static class Error {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String errorCode;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected String errorMessage;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected String errorDetail;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected String suggestedAction;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected String consumerMessage;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected TransactionContainer container;

        /**
         * Gets the value of the errorCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrorCode() {
            return errorCode;
        }

        /**
         * Sets the value of the errorCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrorCode(String value) {
            this.errorCode = value;
        }

        /**
         * Gets the value of the errorMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrorMessage() {
            return errorMessage;
        }

        /**
         * Sets the value of the errorMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrorMessage(String value) {
            this.errorMessage = value;
        }

        /**
         * Gets the value of the errorDetail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrorDetail() {
            return errorDetail;
        }

        /**
         * Sets the value of the errorDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrorDetail(String value) {
            this.errorDetail = value;
        }

        /**
         * Gets the value of the suggestedAction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSuggestedAction() {
            return suggestedAction;
        }

        /**
         * Sets the value of the suggestedAction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSuggestedAction(String value) {
            this.suggestedAction = value;
        }

        /**
         * Gets the value of the consumerMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConsumerMessage() {
            return consumerMessage;
        }

        /**
         * Sets the value of the consumerMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConsumerMessage(String value) {
            this.consumerMessage = value;
        }

        /**
         * Gets the value of the container property.
         * 
         * @return
         *     possible object is
         *     {@link TransactionContainer }
         *     
         */
        public TransactionContainer getContainer() {
            return container;
        }

        /**
         * Sets the value of the container property.
         * 
         * @param value
         *     allowed object is
         *     {@link TransactionContainer }
         *     
         */
        public void setContainer(TransactionContainer value) {
            this.container = value;
        }

    }

}
