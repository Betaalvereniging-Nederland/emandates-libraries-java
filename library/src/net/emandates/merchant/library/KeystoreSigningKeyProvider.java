package net.emandates.merchant.library;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KeystoreSigningKeyProvider implements ISigningKeyProvider {

    private String keyStoreLocation;
    private InputStream keyStore;
    private String keyStorePassword;
    private String signingCertificateAlias;
    private String signingCertificatePassword;
    private String acquirerCertificateAlias;
    private String acquirerAlternateCertificateAlias;

    private final Configuration configuration;
    private final ILogger logger;

    public KeystoreSigningKeyProvider(
            String keyStoreLocation,
            InputStream keyStore,
            String keyStorePassword,
            String signingCertificateAlias,
            String signingCertificatePassword,
            String acquirerCertificateAlias,
            String acquirerAlternateCertificateAlias,
            Configuration configuration) throws IOException {
        this.keyStoreLocation = keyStoreLocation;
        setKeyStoreAndPass(keyStore, keyStorePassword);
        this.signingCertificateAlias = signingCertificateAlias;
        this.signingCertificatePassword = signingCertificatePassword;
        this.acquirerCertificateAlias = acquirerCertificateAlias;
        this.acquirerAlternateCertificateAlias = acquirerAlternateCertificateAlias;
        this.configuration = configuration;
        this.logger = configuration.getLoggerFactory().Create();
    }

    public KeystoreSigningKeyProvider(
            String keyStoreLocation,
            String keyStorePassword,
            String signingCertificateAlias,
            String signingCertificatePassword,
            String acquirerCertificateAlias,
            String acquirerAlternateCertificateAlias,
            Configuration configuration) throws IOException {
        this(
                keyStoreLocation,
                loadKeyStore(keyStoreLocation),
                keyStorePassword,
                signingCertificateAlias,
                signingCertificatePassword,
                acquirerCertificateAlias,
                acquirerAlternateCertificateAlias,
                configuration);
    }

    private static InputStream loadKeyStore(String storeLocation) throws IOException {
        URL url = ClassLoader.getSystemClassLoader().getResource(storeLocation);
        if (url == null) {
            url = Configuration.class.getClassLoader().getResource(storeLocation);
        }
        InputStream keyStore = markSupported(url.openStream());
        keyStore.mark(Integer.MAX_VALUE);
        return keyStore;
    }

    private static InputStream markSupported(InputStream in) {
        if (in.markSupported()) {
            return in;
        }
        return new BufferedInputStream(in);
    }

    @Override
    public SigningKeyPair getSigningKeyPair() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException, CommunicatorException, IOException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = getKeyStore();
        if (is == null) {
            logger.Log(configuration, "key store can not be found/loaded");
            throw new CommunicatorException("KeyStore was not found/loaded");
        }
        is.reset();
        ks.load(is, getKeyStorePassword().toCharArray());
        logger.Log(configuration, "loaded key store");
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(getSigningCertificateAlias(),
                new KeyStore.PasswordProtection(getSigningCertificatePassword().toCharArray()));
        if (keyEntry == null) {
            logger.Log(configuration, "key entry '" + getSigningCertificateAlias() + "' can not be found");
            throw new CommunicatorException("KeyEntry '" + getSigningCertificateAlias() + "' was not found in the KeyStore");
        }
        logger.Log(configuration, "found key entry");
        KeyStore.PrivateKeyEntry privateKeyEntry = keyEntry;
        return new SigningKeyPair(privateKeyEntry.getPrivateKey(), (X509Certificate) privateKeyEntry.getCertificate());
    }

    @Override
    public KeySelector getAcquirerKeySelector() {
        try {
            return new idxKeySelector(configuration, getAcquirerCertificateAlias());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public KeySelector getAlternativeAcquirerKeySelector() {
        try {
            return new idxKeySelector(configuration, getAcquirerAlternateCertificateAlias());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public ISigningKeyProvider Clone() throws CloneNotSupportedException {
        try {
            return new KeystoreSigningKeyProvider(
                    this.keyStoreLocation,
                    this.keyStorePassword,
                    this.signingCertificateAlias,
                    this.signingCertificatePassword,
                    this.acquirerCertificateAlias,
                    this.acquirerAlternateCertificateAlias,
                    configuration);
        } catch (IOException e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    /**
     * @return The password of the private key of the signing certificate
     */
    public String getSigningCertificatePassword() {
        return signingCertificatePassword;
    }

    /**
     * @param signingCertificatePassword The password of the private key of the signing certificate
     */
    public void setSigningCertificatePassword(String signingCertificatePassword) {
        this.signingCertificatePassword = signingCertificatePassword;
    }

    /**
     * @return The password used to access the keystore
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * @return A Java keystore (file on the disk) that stores the certificates
     */
    public String getKeyStoreLocation() {
        return keyStoreLocation;
    }

    /**
     * @param keyStoreLocation A Java keystore (file on the disk) that stores the certificates
     * @param keyStorePassword The password used to access the keystore
     */
    public void setKeyStoreLocationAndPass(String keyStoreLocation, String keyStorePassword) throws IOException {
        this.keyStoreLocation = keyStoreLocation;
        this.keyStorePassword = keyStorePassword;

        this.keyStore = loadKeyStore(getKeyStoreLocation());
    }

    /**
     * @param keyStore A Java InputStream keystore that stores the certificates
     * @param keyStorePassword The password used to access the keystore
     */
    public void setKeyStoreAndPass(InputStream keyStore, String keyStorePassword) throws IOException {
        this.keyStorePassword = keyStorePassword;

        this.keyStore = markSupported(keyStore);
        this.keyStore.mark(Integer.MAX_VALUE);
    }

    /**
     * @return the keyStore
     */
    InputStream getKeyStore() {
        return keyStore;
    }

    /**
     * @return A string which specifies the alias of the certificate to use to sign messages to the creditor bank.
     */
    public String getSigningCertificateAlias() {
        return signingCertificateAlias;
    }

    /**
     * @param signingCertificateAlias A string which specifies the alias of the certificate to use to sign messages to the creditor bank.
     */
    public void setSigningCertificateAlias(String signingCertificateAlias) {
        this.signingCertificateAlias = signingCertificateAlias;
    }

    /**
     * @return A string which specifies the alias of the certificate to use to validate messages from the creditor bank
     */
    public String getAcquirerCertificateAlias() {
        return acquirerCertificateAlias;
    }

    /**
     * @param acquirerCertificateAlias A string which specifies the alias of the certificate to use to validate messages from the creditor bank
     */
    public void setAcquirerCertificateAlias(String acquirerCertificateAlias) {
        this.acquirerCertificateAlias = acquirerCertificateAlias;
    }

    /**
     * @return A string which specifies the alias of the alternate certificate to validate  received messages from the creditor bank
     */
    public String getAcquirerAlternateCertificateAlias() {
        return acquirerAlternateCertificateAlias;
    }

    /**
     * @param acquirerAlternateCertificateAlias A string which specifies the alias of the alternate certificate to validate received messages from the creditor bank
     */
    public void setAcquirerAlternateCertificateAlias(String acquirerAlternateCertificateAlias) {
        this.acquirerAlternateCertificateAlias = acquirerAlternateCertificateAlias;
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
        private String acquirerCertificateAlias;

        public idxKeySelector(Configuration configuration, String acquirerCertificateAlias) {
            this.acquirerCertificateAlias = acquirerCertificateAlias;

            if(acquirerCertificateAlias == null || acquirerCertificateAlias.isEmpty())
            {
                logger.Log(configuration,"When checking idx signature, acquirer certificate was null or empty!");
                throw new IllegalArgumentException();
            }
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
                InputStream is = getKeyStore();
                is.reset();
                ks.load(is, getKeyStorePassword().toCharArray());
                X509Certificate cert = (X509Certificate) ks.getCertificate(acquirerCertificateAlias);
                final PublicKey pk = cert.getPublicKey();

                logger.Log(configuration, "checking iDx signature with certificate:");
                logger.Log(configuration, "  fingerprint: " + thumbprint);
                logger.Log(configuration, "  subject    : " + cert.getSubjectDN().getName());
                logger.Log(configuration, "  issuer     : " + cert.getIssuerDN().getName());

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
