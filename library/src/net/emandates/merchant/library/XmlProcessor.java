package net.emandates.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class XmlProcessor {

    protected ILogger logger;

    public XmlProcessor(Configuration config) {
        this.logger = config.getLoggerFactory().Create();
    }

    public String AddSignature(Configuration config, String xml)
            throws CommunicatorException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableEntryException, InvalidAlgorithmParameterException, ParserConfigurationException, MarshalException,
            SAXException, XMLSignatureException, TransformerException {
        logger.Log(config, "adding signature...");
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = config.getKeyStore();
        is.reset();
        if(is == null){
            logger.Log(config, "key store can not be found/loaded");
            throw new CommunicatorException("KeyStore was not found/loaded");
        }
        ks.load(is, config.getKeyStorePassword().toCharArray());
        logger.Log(config, "loaded key store");
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(config.getSigningCertificateAlias(),
                new KeyStore.PasswordProtection(config.getSigningCertificatePassword().toCharArray()));
        if(keyEntry == null){
            logger.Log(config, "key entry '" + config.getSigningCertificateAlias() + "' can not be found");
            throw new CommunicatorException("KeyEntry '" + config.getSigningCertificateAlias() + "' was not found in the KeyStore");
        }
        logger.Log(config, "found key entry");
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        List<Transform> transforms = new ArrayList<>();
        transforms.add(fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec) null));
        transforms.add(fac.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (TransformParameterSpec) null));

        Reference ref = fac.newReference(
                "",
                fac.newDigestMethod(DigestMethod.SHA256, null),
                transforms,
                null,
                null
        );

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null),
                Collections.singletonList(ref)
        );

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyName kn = kif.newKeyName(Utils.sha1Hex(cert.getEncoded()));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kn));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), "");

        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

        logger.Log(config, "signing xml");
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty("encoding", "utf-8");
        StringWriter writer = new StringWriter();
        trans.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.toString();
    }

    public boolean VerifySchema(final Configuration config, String xml) throws SAXException, ParserConfigurationException, IOException {
        logger.Log(config, "verifying schema...");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(true);

        logger.Log(config, "building schema set");
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        dbf.setSchema(sf.newSchema(new Source[]{
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/idx/xmldsig-core-schema.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/idx/idx.merchant-acquirer.1.0.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/pain009/pain.009.001.04.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/pain010/pain.010.001.04.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/pain011/pain.011.001.04.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/pain012/pain.012.001.04.xsd"))
        }));

        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                logger.Log(config, "schema error: " + exception.getMessage());
                throw new SAXException(exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                logger.Log(config, "schema error: " + exception.getMessage());
                throw new SAXException(exception);
            }
        });

        db.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

        logger.Log(config, "schema is valid");
        return true;
    }

    public boolean VerifySignature(Configuration config, String xml) throws ParserConfigurationException, SAXException, IOException, MarshalException, XMLSignatureException, ClassNotFoundException, InstantiationException, IllegalAccessException, TransformerException {
        logger.Log(config, "verifying signature...");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), "");

        NodeList signatures = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (signatures.getLength() == 2) {
            Element mandate = (Element) (doc.getElementsByTagNameNS("urn:iso:std:iso:20022:tech:xsd:pain.012.001.04", "Document").item(0));
            if (CheckMandateSignature(config, mandate)) {
                logger.Log(config, "eMandate signature is valid");
                Element signature = (Element) (signatures.item(signatures.getLength() - 1));
                boolean b = CheckIdxSignature(config, doc, signature);
                logger.Log(config, "idx signature is valid: " + b);
                return b;
            } else {
                logger.Log(config, "eMandate signature is not valid");
                return false;
            }
        } else if (signatures.getLength() == 1) {
            Element signature = (Element) (signatures.item(signatures.getLength() - 1));
            boolean b = CheckIdxSignature(config, doc, signature);
            logger.Log(config, "idx signature is valid: " + b);
            return b;
        } else {
            logger.Log(config, "signatures in document: " + signatures.getLength());
            return false;
        }
    }

    private X509Certificate ExtractCertificate(Configuration config, X509Data struct) throws ParserConfigurationException, MarshalException, XMLSignatureException, SAXException, IOException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, TransformerException {

        Iterator iterator = struct.getContent().iterator();

        while (iterator.hasNext()) {
            Object element = iterator.next();

            if (element instanceof X509Certificate) {
                return (X509Certificate) element;
            }
        }

        throw new XMLSignatureException();
    }

    private boolean CheckMandateSignature(Configuration config, Element element) throws ParserConfigurationException, MarshalException, XMLSignatureException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        Document temp = dbf.newDocumentBuilder().newDocument();
        temp.appendChild(temp.importNode(element, true));

        String xml = Utils.serializeWithoutDeclaration(temp);

        Document eMandate = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        Element signature = (Element) eMandate.getElementsByTagNameNS("*", "Signature").item(0);

        String providerName = System.getProperty(
                "jsr105Provider",
                "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());
        XMLSignature sig = fac.unmarshalXMLSignature(new DOMStructure(signature));

        X509Data struct = (X509Data) sig.getKeyInfo().getContent().iterator().next();
        X509Certificate cert = ExtractCertificate(config, struct);

        DOMValidateContext valContext = new DOMValidateContext(KeySelector.singletonKeySelector(cert.getPublicKey()), eMandate.getDocumentElement());

        boolean b = sig.validate(valContext);

        if (b == false) {
            logger.Log(config, "Signature failed core validation");
            boolean sv = sig.getSignatureValue().validate(valContext);
            logger.Log(config, "signature validation status: " + sv);
            // check the validation status of each Reference
            Iterator i = sig.getSignedInfo().getReferences().iterator();
            for (int j = 0; i.hasNext(); j++) {
                boolean refValid
                        = ((Reference) i.next()).validate(valContext);
                logger.Log(config, "ref[" + j + "] validity status: " + refValid);
            }
        } else {
            logger.Log(config, "Signature passed core validation");
        }

        return b;
    }

    private boolean CheckIdxSignature(Configuration config, Document doc, Element signature) throws MarshalException, XMLSignatureException, IOException {
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        DOMValidateContext valContext = new DOMValidateContext(new idxKeySelector(config), doc);
        XMLSignature sig = fac.unmarshalXMLSignature(new DOMStructure(signature));

        valContext.setProperty("javax.xml.crypto.dsig.cacheReference", Boolean.TRUE);

        boolean b = sig.validate(valContext);
        return b;
    }

    private class eMandateKeySelector extends KeySelector {

        private Configuration config;

        public eMandateKeySelector(Configuration config) {
            this.config = config;
        }

        @Override
        public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
            XMLStructure ki = (XMLStructure) keyInfo.getContent().iterator().next();
            if (!(ki instanceof X509Data)) {
                throw new KeySelectorException("KeyName not found!");
            }
            X509Data data = (X509Data) ki;
            final X509Certificate cert = (X509Certificate) data.getContent().get(0);
            final PublicKey pk = cert.getPublicKey();

            logger.Log(config, "checking eMandate signature with certificate:");
            try {
                logger.Log(config, "  fingerprint: " + Utils.sha1Hex(cert.getEncoded()));
            } catch (CertificateEncodingException | NoSuchAlgorithmException ex) {
                logger.Log(config, "  (cannot get fingerprint): " + ex.getMessage());
            }
            logger.Log(config, "  subject    : " + cert.getSubjectDN().getName());
            logger.Log(config, "  issuer     : " + cert.getIssuerDN().getName());

            return new KeySelectorResult() {
                @Override
                public Key getKey() {
                    return pk;
                }
            };
        }
    }

    private class idxKeySelector extends KeySelector {

        private Configuration config;

        public idxKeySelector(Configuration config) {
            this.config = config;
        }

        @Override
        public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
            try {
                XMLStructure ki = (XMLStructure) keyInfo.getContent().iterator().next();
                if (!(ki instanceof KeyName)) {
                    throw new KeySelectorException("KeyName not found!");
                }
                KeyName kn = (KeyName) ki;
                String thumbprint = kn.getName();

                KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                InputStream is = config.getKeyStore();
                is.reset();
                ks.load(is, config.getKeyStorePassword().toCharArray());
                X509Certificate cert = (X509Certificate) ks.getCertificate(config.getAcquirerCertificateAlias());
                final PublicKey pk = cert.getPublicKey();

                logger.Log(config, "checking iDx signature with certificate:");
                logger.Log(config, "  fingerprint: " + thumbprint);
                logger.Log(config, "  subject    : " + cert.getSubjectDN().getName());
                logger.Log(config, "  issuer     : " + cert.getIssuerDN().getName());

                return new KeySelectorResult() {
                    @Override
                    public Key getKey() {
                        return pk;
                    }
                };
            } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException ex) {
                throw new KeySelectorException(ex);
            }
        }
    }
}
