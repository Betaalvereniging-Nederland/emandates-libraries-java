
package schemas.idx;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.Duration;
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
 *         &lt;element name="Issuer">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="issuerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerID"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Merchant">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="merchantID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.merchantID"/>
 *                   &lt;element name="subID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Merchant.subID"/>
 *                   &lt;element name="merchantReturnURL" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}url"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Transaction">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="expirationPeriod" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.expirationPeriod" minOccurs="0"/>
 *                   &lt;element name="language" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.language"/>
 *                   &lt;element name="entranceCode" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.entranceCode"/>
 *                   &lt;element name="container" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.container"/>
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
    "issuer",
    "merchant",
    "transaction",
    "signature"
})
@XmlRootElement(name = "AcquirerTrxReq", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
public class AcquirerTrxReq {

    @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected XMLGregorianCalendar createDateTimestamp;
    @XmlElement(name = "Issuer", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxReq.Issuer issuer;
    @XmlElement(name = "Merchant", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxReq.Merchant merchant;
    @XmlElement(name = "Transaction", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxReq.Transaction transaction;
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
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxReq.Issuer }
     *     
     */
    public AcquirerTrxReq.Issuer getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxReq.Issuer }
     *     
     */
    public void setIssuer(AcquirerTrxReq.Issuer value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the merchant property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxReq.Merchant }
     *     
     */
    public AcquirerTrxReq.Merchant getMerchant() {
        return merchant;
    }

    /**
     * Sets the value of the merchant property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxReq.Merchant }
     *     
     */
    public void setMerchant(AcquirerTrxReq.Merchant value) {
        this.merchant = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxReq.Transaction }
     *     
     */
    public AcquirerTrxReq.Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxReq.Transaction }
     *     
     */
    public void setTransaction(AcquirerTrxReq.Transaction value) {
        this.transaction = value;
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
     *         &lt;element name="issuerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerID"/>
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
        "issuerID"
    })
    public static class Issuer {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String issuerID;

        /**
         * Gets the value of the issuerID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIssuerID() {
            return issuerID;
        }

        /**
         * Sets the value of the issuerID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIssuerID(String value) {
            this.issuerID = value;
        }

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
     *         &lt;element name="merchantReturnURL" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}url"/>
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
        "subID",
        "merchantReturnURL"
    })
    public static class Merchant {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String merchantID;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected int subID;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected String merchantReturnURL;

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

        /**
         * Gets the value of the merchantReturnURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMerchantReturnURL() {
            return merchantReturnURL;
        }

        /**
         * Sets the value of the merchantReturnURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMerchantReturnURL(String value) {
            this.merchantReturnURL = value;
        }

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
     *         &lt;element name="expirationPeriod" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.expirationPeriod" minOccurs="0"/>
     *         &lt;element name="language" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.language"/>
     *         &lt;element name="entranceCode" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.entranceCode"/>
     *         &lt;element name="container" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.container"/>
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
        "expirationPeriod",
        "language",
        "entranceCode",
        "container"
    })
    public static class Transaction {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
        protected Duration expirationPeriod;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String language;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String entranceCode;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected TransactionContainer container;

        /**
         * Gets the value of the expirationPeriod property.
         * 
         * @return
         *     possible object is
         *     {@link Duration }
         *     
         */
        public Duration getExpirationPeriod() {
            return expirationPeriod;
        }

        /**
         * Sets the value of the expirationPeriod property.
         * 
         * @param value
         *     allowed object is
         *     {@link Duration }
         *     
         */
        public void setExpirationPeriod(Duration value) {
            this.expirationPeriod = value;
        }

        /**
         * Gets the value of the language property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Sets the value of the language property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguage(String value) {
            this.language = value;
        }

        /**
         * Gets the value of the entranceCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEntranceCode() {
            return entranceCode;
        }

        /**
         * Sets the value of the entranceCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEntranceCode(String value) {
            this.entranceCode = value;
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
