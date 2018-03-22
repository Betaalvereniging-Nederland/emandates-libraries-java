
package schemas.idx;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Directory">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="directoryDateTimestamp" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}dateTime"/>
 *                   &lt;element name="Country" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="countryNames" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Country.countryNames"/>
 *                             &lt;element name="Issuer" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="issuerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerID"/>
 *                                       &lt;element name="issuerName" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerName"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "directory",
    "signature"
})
@XmlRootElement(name = "DirectoryRes", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0")
public class DirectoryRes {

    @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected XMLGregorianCalendar createDateTimestamp;
    @XmlElement(name = "Acquirer", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected DirectoryRes.Acquirer acquirer;
    @XmlElement(name = "Directory", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
    protected DirectoryRes.Directory directory;
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
     *     {@link DirectoryRes.Acquirer }
     *     
     */
    public DirectoryRes.Acquirer getAcquirer() {
        return acquirer;
    }

    /**
     * Sets the value of the acquirer property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectoryRes.Acquirer }
     *     
     */
    public void setAcquirer(DirectoryRes.Acquirer value) {
        this.acquirer = value;
    }

    /**
     * Gets the value of the directory property.
     * 
     * @return
     *     possible object is
     *     {@link DirectoryRes.Directory }
     *     
     */
    public DirectoryRes.Directory getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectoryRes.Directory }
     *     
     */
    public void setDirectory(DirectoryRes.Directory value) {
        this.directory = value;
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
     *         &lt;element name="directoryDateTimestamp" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}dateTime"/>
     *         &lt;element name="Country" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="countryNames" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Country.countryNames"/>
     *                   &lt;element name="Issuer" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="issuerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerID"/>
     *                             &lt;element name="issuerName" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerName"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "directoryDateTimestamp",
        "country"
    })
    public static class Directory {

        @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected XMLGregorianCalendar directoryDateTimestamp;
        @XmlElement(name = "Country", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
        protected List<DirectoryRes.Directory.Country> country;

        /**
         * Gets the value of the directoryDateTimestamp property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDirectoryDateTimestamp() {
            return directoryDateTimestamp;
        }

        /**
         * Sets the value of the directoryDateTimestamp property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDirectoryDateTimestamp(XMLGregorianCalendar value) {
            this.directoryDateTimestamp = value;
        }

        /**
         * Gets the value of the country property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the country property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCountry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DirectoryRes.Directory.Country }
         * 
         * 
         */
        public List<DirectoryRes.Directory.Country> getCountry() {
            if (country == null) {
                country = new ArrayList<DirectoryRes.Directory.Country>();
            }
            return this.country;
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
         *         &lt;element name="countryNames" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Country.countryNames"/>
         *         &lt;element name="Issuer" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="issuerID" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerID"/>
         *                   &lt;element name="issuerName" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerName"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "countryNames",
            "issuer"
        })
        public static class Country {

            @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String countryNames;
            @XmlElement(name = "Issuer", namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
            protected List<DirectoryRes.Directory.Country.Issuer> issuer;

            /**
             * Gets the value of the countryNames property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCountryNames() {
                return countryNames;
            }

            /**
             * Sets the value of the countryNames property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCountryNames(String value) {
                this.countryNames = value;
            }

            /**
             * Gets the value of the issuer property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the issuer property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getIssuer().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link DirectoryRes.Directory.Country.Issuer }
             * 
             * 
             */
            public List<DirectoryRes.Directory.Country.Issuer> getIssuer() {
                if (issuer == null) {
                    issuer = new ArrayList<DirectoryRes.Directory.Country.Issuer>();
                }
                return this.issuer;
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
             *         &lt;element name="issuerName" type="{http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0}Issuer.issuerName"/>
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
                "issuerID",
                "issuerName"
            })
            public static class Issuer {

                @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String issuerID;
                @XmlElement(namespace = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0", required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String issuerName;

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

                /**
                 * Gets the value of the issuerName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getIssuerName() {
                    return issuerName;
                }

                /**
                 * Sets the value of the issuerName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setIssuerName(String value) {
                    this.issuerName = value;
                }

            }

        }

    }

}
