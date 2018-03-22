
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
 *         &lt;element name="Acquirer">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="acquirerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Acquirer.acquirerID"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Issuer">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="issuerAuthenticationURL" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerAuthenticationURL"/>
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
 *                   &lt;element name="transactionID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.transactionID"/>
 *                   &lt;element name="transactionCreateDateTimestamp" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}dateTime"/>
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
    "acquirer",
    "issuer",
    "transaction",
    "signature"
})
@XmlRootElement(name = "AcquirerTrxRes", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
public class AcquirerTrxRes {

    @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected XMLGregorianCalendar createDateTimestamp;
    @XmlElement(name = "Acquirer", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxRes.Acquirer acquirer;
    @XmlElement(name = "Issuer", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxRes.Issuer issuer;
    @XmlElement(name = "Transaction", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected AcquirerTrxRes.Transaction transaction;
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
     * Gets the value of the acquirer property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxRes.Acquirer }
     *     
     */
    public AcquirerTrxRes.Acquirer getAcquirer() {
        return acquirer;
    }

    /**
     * Sets the value of the acquirer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxRes.Acquirer }
     *     
     */
    public void setAcquirer(AcquirerTrxRes.Acquirer value) {
        this.acquirer = value;
    }

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxRes.Issuer }
     *     
     */
    public AcquirerTrxRes.Issuer getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxRes.Issuer }
     *     
     */
    public void setIssuer(AcquirerTrxRes.Issuer value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTrxRes.Transaction }
     *     
     */
    public AcquirerTrxRes.Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTrxRes.Transaction }
     *     
     */
    public void setTransaction(AcquirerTrxRes.Transaction value) {
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
     *         &lt;element name="acquirerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Acquirer.acquirerID"/>
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
        "acquirerID"
    })
    public static class Acquirer {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String acquirerID;

        /**
         * Gets the value of the acquirerID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcquirerID() {
            return acquirerID;
        }

        /**
         * Sets the value of the acquirerID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcquirerID(String value) {
            this.acquirerID = value;
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
     *         &lt;element name="issuerAuthenticationURL" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerAuthenticationURL"/>
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
        "issuerAuthenticationURL"
    })
    public static class Issuer {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected String issuerAuthenticationURL;

        /**
         * Gets the value of the issuerAuthenticationURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIssuerAuthenticationURL() {
            return issuerAuthenticationURL;
        }

        /**
         * Sets the value of the issuerAuthenticationURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIssuerAuthenticationURL(String value) {
            this.issuerAuthenticationURL = value;
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
     *         &lt;element name="transactionID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Transaction.transactionID"/>
     *         &lt;element name="transactionCreateDateTimestamp" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}dateTime"/>
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
        "transactionID",
        "transactionCreateDateTimestamp"
    })
    public static class Transaction {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String transactionID;
        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected XMLGregorianCalendar transactionCreateDateTimestamp;

        /**
         * Gets the value of the transactionID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionID() {
            return transactionID;
        }

        /**
         * Sets the value of the transactionID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionID(String value) {
            this.transactionID = value;
        }

        /**
         * Gets the value of the transactionCreateDateTimestamp property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTransactionCreateDateTimestamp() {
            return transactionCreateDateTimestamp;
        }

        /**
         * Sets the value of the transactionCreateDateTimestamp property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTransactionCreateDateTimestamp(XMLGregorianCalendar value) {
            this.transactionCreateDateTimestamp = value;
        }

    }

}
