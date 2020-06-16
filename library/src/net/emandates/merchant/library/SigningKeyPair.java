package net.emandates.merchant.library;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Objects;

public final class SigningKeyPair {
    private final PrivateKey privateKey;
    private final X509Certificate certificate;

    public SigningKeyPair(PrivateKey privateKey, X509Certificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SigningKeyPair that = (SigningKeyPair) o;
        return Objects.equals(privateKey, that.privateKey) &&
                Objects.equals(certificate, that.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateKey, certificate);
    }
}
