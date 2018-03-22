package net.emandates.merchant.library;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Communicator class, to be used for sending messages where
 * LocalInstrumentationCode = B2B.
 */
public class B2BCommunicator extends CoreCommunicator {

    /**
     * Default constructor, does initialization and sets LocalInstrumentCode to
     * B2B.
     */
    public B2BCommunicator() {
        super();
        localInstrumentCode = Instrumentation.B2B;
    }

    public B2BCommunicator(Configuration config) {
        super(config);
        localInstrumentCode = Instrumentation.B2B;
    }

    /**
     * Sends a cancellation request to the URL specified in
     * Configuration.AcquirerUrl_TransactionReq.
     * @param cancellationRequest A CancellationRequest object
     * @return A CancellationResponse object which contains the response from
     * the server (transaction id, issuer authentication URL), or error
     * information when an error occurs
     */
    public CancellationResponse cancel(CancellationRequest cancellationRequest) {
        try {
            logger.Log(config, "sending cancel mandate request");
            Element eMandate = new eMandateMessageBuilder(localInstrumentCode).getCancel(cancellationRequest);
            String xml = new iDxMessageBuilder(localInstrumentCode).getTransactionRequest(config, cancellationRequest, eMandate);
            xml = xmlProcessor.AddSignature(config, xml);

            CancellationResponse cr = CancellationResponse.Parse(
                    performRequest(xml, config.getAcquirerUrl_TransactionReq(), config.isTls12Enabled())
            );

            return cr;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return CancellationResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return CancellationResponse.Get(ex);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            return CancellationResponse.Get(ex);
        }
    }
}
