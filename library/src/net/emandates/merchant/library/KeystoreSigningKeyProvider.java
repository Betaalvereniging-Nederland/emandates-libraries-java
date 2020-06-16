package net.emandates.merchant.library;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
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
}
