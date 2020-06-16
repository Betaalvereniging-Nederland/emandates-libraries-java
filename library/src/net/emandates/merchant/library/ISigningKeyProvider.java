package net.emandates.merchant.library;

import javax.xml.crypto.KeySelector;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface ISigningKeyProvider {
    public SigningKeyPair getSigningKeyPair() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException, CommunicatorException, IOException;
    public KeySelector getAcquirerKeySelector();
    public KeySelector getAlternativeAcquirerKeySelector();
}
