package net.emandates.merchant.library;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KeystoreSigningKeyProvider implements ISigningKeyProvider {
    private final Configuration configuration;
    private final ILogger logger;

    public KeystoreSigningKeyProvider(Configuration configuration, ILogger logger) {
        this.configuration = configuration;
        this.logger = logger;
    }

    @Override
    public SigningKeyPair getSigningKeyPair() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException, CommunicatorException, IOException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = configuration.getKeyStore();
        if (is == null) {
            logger.Log(configuration, "key store can not be found/loaded");
            throw new CommunicatorException("KeyStore was not found/loaded");
        }
        is.reset();
        ks.load(is, configuration.getKeyStorePassword().toCharArray());
        logger.Log(configuration, "loaded key store");
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(configuration.getSigningCertificateAlias(),
                new KeyStore.PasswordProtection(configuration.getSigningCertificatePassword().toCharArray()));
        if (keyEntry == null) {
            logger.Log(configuration, "key entry '" + configuration.getSigningCertificateAlias() + "' can not be found");
            throw new CommunicatorException("KeyEntry '" + configuration.getSigningCertificateAlias() + "' was not found in the KeyStore");
        }
        logger.Log(configuration, "found key entry");
        KeyStore.PrivateKeyEntry privateKeyEntry = keyEntry;
        return new SigningKeyPair(privateKeyEntry.getPrivateKey(), (X509Certificate) privateKeyEntry.getCertificate());
    }

    @Override
    public KeySelector getAcquirerKeySelector() {
        try {
            return new idxKeySelector(configuration, configuration.getAcquirerCertificateAlias());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public KeySelector getAlternativeAcquirerKeySelector() {
        try {
            return new idxKeySelector(configuration, configuration.getAcquirerAlternateCertificateAlias());
        } catch (IllegalArgumentException e) {
            return null;
        }
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
                InputStream is = configuration.getKeyStore();
                is.reset();
                ks.load(is, configuration.getKeyStorePassword().toCharArray());
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
