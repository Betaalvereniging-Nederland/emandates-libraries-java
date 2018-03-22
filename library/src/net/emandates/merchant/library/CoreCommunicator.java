package net.emandates.merchant.library;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Communicator class, to be used for sending messages where LocalInstrumentationCode = CORE
 */
public class CoreCommunicator {
    /**
     * Logger instance, to be used for logging iso pain raw messages and library messages.
     */
    protected ILogger logger;

    /**
     * XmlProcessor instance, used to process XMLs (signing, verifying, validating signature).
     */
    protected XmlProcessor xmlProcessor;

    /**
     * LocalInstrumentCode used by the current instance (can be CORE or B2B).
     */
    protected Instrumentation localInstrumentCode;
    
    /**
     * Configuration used by the current instance (can be CORE or B2B).
     */
    protected Configuration config;

    /**
     * Constructs a new Communicator, initializes the Configuration and sets LocalInstrumentCode = CORE
     */
    public CoreCommunicator() {
       this(Configuration.defaultInstance());
    }
    
    public CoreCommunicator(Configuration config) {
        this.config = config;
        logger = (this.config.getLoggerFactory() != null)
                ? this.config.getLoggerFactory().Create() : new LoggerFactory().Create();
        xmlProcessor = new XmlProcessor(this.config);
        localInstrumentCode = Instrumentation.CORE;
    }

    /**
     * Sends a directory request to the URL specified in Configuration.AcquirerUrl_DirectoryReq.
     * @return A DirectoryResponse object which contains the response from the server (a list of debtor banks), or error
     * information when an error occurs
     */
    public DirectoryResponse directory() {
        try {
            logger.Log(config, "sending new directory request");
            String xml = new iDxMessageBuilder(localInstrumentCode).getDirectoryRequest(config);
            xml = xmlProcessor.AddSignature(config, xml);

            DirectoryResponse dr = DirectoryResponse.Parse(
                performRequest(xml, config.getAcquirerUrl_DirectoryReq(), config.isTls12Enabled())
            );

            return dr;
        } catch (DatatypeConfigurationException | JAXBException | CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return DirectoryResponse.Get(ex);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | ParserConfigurationException | MarshalException | SAXException | XMLSignatureException | TransformerException ex) {
            logger.Log(config, ex.getMessage());
            return DirectoryResponse.Get(ex);
        }
    }
    
    private HttpURLConnection getConnection(String url, boolean isTls12Enabled) throws IOException, KeyManagementException, NoSuchAlgorithmException
    {
        if(url.startsWith("https://"))
        {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
            if(isTls12Enabled)
            {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null,null,new java.security.SecureRandom());
                con.setSSLSocketFactory(sc.getSocketFactory());
            }
            return con;
        }
        else
        {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            return con;
        }
    }
    
    String performRequest(String xml, String url, boolean isTls12Enabled) throws CommunicatorException {
        try {
            logger.Log(config, "sending request to " + url);

            if (!xmlProcessor.VerifySchema(config, xml)) {
                logger.Log(config, "request xml schema is not valid");
                throw new CommunicatorException("request xml schema not valid");
            }

            logger.LogXmlMessage(config, xml);

            logger.Log(config, "creating http(s) client");

            HttpURLConnection con = getConnection(url, isTls12Enabled );//HttpsURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            
            con.getOutputStream().write(xml.getBytes(StandardCharsets.UTF_8));
            
            String response = Utils.copy(con.getInputStream());

            logger.LogXmlMessage(config, response);

            if (!xmlProcessor.VerifySchema(config,response)) {
                logger.Log(config, "response xml schema is not valid");
                throw new CommunicatorException("response xml schema not valid");
            }

            if (!xmlProcessor.VerifySignature(config, response)) {
                logger.Log(config, "response xml signature not valid");
                throw new CommunicatorException("response xml signature not valid");
            }
            
            return response;
        } catch (IOException | IllegalStateException | ParserConfigurationException | SAXException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (TransformerException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (KeyManagementException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (NoSuchAlgorithmException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        }
    }
    
    /**
     * Sends a new mandate request to the URL specified in Configuration.AcquirerUrl_TransactionReq.
     * @param newMandateRequest A NewMandateRequest object.
     * @return A NewMandateResponse object which contains the response from the server (transaction id, issuer authentication URL), or error information
     * when an error occurs
     */
    public NewMandateResponse newMandate(NewMandateRequest newMandateRequest) {
        try {
            logger.Log(config, "sending new mandate request");
            Element eMandate = new eMandateMessageBuilder(localInstrumentCode).getNewMandate(newMandateRequest);
            String xml = new iDxMessageBuilder(localInstrumentCode).getTransactionRequest(config,newMandateRequest, eMandate);
            xml = xmlProcessor.AddSignature(config, xml);

            NewMandateResponse nmr = NewMandateResponse.Parse(
                performRequest(xml, config.getAcquirerUrl_TransactionReq(), config.isTls12Enabled())
            );

            return nmr;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return NewMandateResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return NewMandateResponse.Get(ex);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            return NewMandateResponse.Get(ex);
        }
    }
    
    /**
     * Sends an amendment request to the URL specified in Configuration.AcquirerUrl_TransactionReq.
     * @param amendmentRequest An AmendmentRequest object.
     * @return An AmendmentResponse object which contains the response from the server (transaction id, issuer authentication URL),
     * or error information when an error occurs.
     */
    public AmendmentResponse amend(AmendmentRequest amendmentRequest) {
        try {
            logger.Log(config, "sending amend mandate request");
            Element eMandate = new eMandateMessageBuilder(localInstrumentCode).getAmend(amendmentRequest);
            String xml = new iDxMessageBuilder(localInstrumentCode).getTransactionRequest(config, amendmentRequest, eMandate);
            xml = xmlProcessor.AddSignature(config, xml);

            AmendmentResponse ar = AmendmentResponse.Parse(
                    performRequest( xml, config.getAcquirerUrl_TransactionReq(), config.isTls12Enabled())
            );

            return ar;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return AmendmentResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return AmendmentResponse.Get(ex);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            return AmendmentResponse.Get(ex);
        }
    }
    
    /**
     * Sends a transaction status request to the URL specified in Configuration.AcquirerUrl_TransactionReq.
     * @param statusRequest A StatusRequest object
     * @return A StatusResponse object which contains the response from the server (transaction id, status message), or
     * error information when an error occurs.
     */
    public StatusResponse getStatus(StatusRequest statusRequest) {
        try {
            logger.Log(config, "sending status request");
            String xml = new iDxMessageBuilder(localInstrumentCode).getStatusRequest(config, statusRequest);
            xml = xmlProcessor.AddSignature(config, xml);

            StatusResponse sr = StatusResponse.Parse(
                    performRequest( xml, config.getAcquirerUrl_StatusReq(), config.isTls12Enabled())
            );

            return sr;
        } catch (CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | ParserConfigurationException | MarshalException | SAXException | XMLSignatureException | TransformerException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        }
    }
    
    public static String getVersion() {
        return "1.2.4";
    }
}
