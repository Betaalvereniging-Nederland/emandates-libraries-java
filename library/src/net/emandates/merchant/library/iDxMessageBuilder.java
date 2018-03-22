package net.emandates.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.Duration;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import schemas.idx.AcquirerTrxReq;
import schemas.idx.DirectoryReq;
import schemas.idx.TransactionContainer;
import org.xml.sax.SAXException;
import schemas.idx.AcquirerStatusReq;

class iDxMessageBuilder {
    private final static String ProductID_CORE = "NL:BVN:eMandatesCore:1.0";
    private final static String ProductID_B2B = "NL:BVN:eMandatesB2B:1.0";
    private final static String Version = "1.0.0";
    
    private final Instrumentation localInstrumentCode;
    
    public iDxMessageBuilder(Instrumentation localInstrumentCode) {
        this.localInstrumentCode = localInstrumentCode;
    }
    
    private void verifyExpirationPeriod(Duration duration) throws DatatypeConfigurationException, CommunicatorException {
        if (duration != null && duration.isLongerThan(DatatypeFactory.newInstance().newDuration(true, 0, 0, 7, 0, 0, 0))) {
            throw new CommunicatorException("ExpirationPeriod should be less than 7 days");
        }
    }
    
    public String getDirectoryRequest(final Configuration config) throws DatatypeConfigurationException, JAXBException {
        DirectoryReq dirReq = new DirectoryReq();
        
        dirReq.setProductID((this.localInstrumentCode == Instrumentation.CORE)? ProductID_CORE : ProductID_B2B);
        dirReq.setVersion(Version);
        dirReq.setCreateDateTimestamp(Utils.UtcNow());

        dirReq.setMerchant(new DirectoryReq.Merchant() {{
            merchantID = config.geteMandateContractId();
            subID = config.geteMandateContractSubId();
        }});
                
        return Utils.serialize(dirReq, schemas.idx.DirectoryReq.class);
    }
    
    public String getTransactionRequest(final Configuration config, final NewMandateRequest newMandateRequest, final Element containedData)
            throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        verifyExpirationPeriod(newMandateRequest.getExpirationPeriod());
        
        AcquirerTrxReq trxReq = new AcquirerTrxReq();
        
        trxReq.setProductID((this.localInstrumentCode == Instrumentation.CORE)? ProductID_CORE : ProductID_B2B);
        trxReq.setVersion(Version);
        trxReq.setCreateDateTimestamp(Utils.UtcNow());

        trxReq.setMerchant(new AcquirerTrxReq.Merchant() {{
            setMerchantID(config.geteMandateContractId());
            setSubID(config.geteMandateContractSubId());
            setMerchantReturnURL(config.getMerchantReturnUrl());
        }});

        trxReq.setIssuer(new AcquirerTrxReq.Issuer() {{
            setIssuerID(newMandateRequest.getDebtorBankID());
        }});

        trxReq.setTransaction(new AcquirerTrxReq.Transaction() {{
            setEntranceCode(newMandateRequest.getEntranceCode());
            setExpirationPeriod(newMandateRequest.getExpirationPeriod());
            setLanguage(newMandateRequest.getLanguage());
            setContainer(new TransactionContainer());
        }});

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(
            Utils.serialize(trxReq, schemas.idx.AcquirerTrxReq.class).getBytes(StandardCharsets.UTF_8)), ""
        );
        Node node = doc.importNode(containedData, true);
        doc.getElementsByTagName("container").item(0).appendChild(node);

        return Utils.serialize(doc);
    }
    
    public String getStatusRequest(final Configuration config, final StatusRequest statusRequest) throws DatatypeConfigurationException, JAXBException {
        AcquirerStatusReq stsReq = new AcquirerStatusReq();
        
        stsReq.setProductID((this.localInstrumentCode == Instrumentation.CORE)? ProductID_CORE : ProductID_B2B);
        stsReq.setVersion(Version);
        stsReq.setCreateDateTimestamp(Utils.UtcNow());
        
        stsReq.setMerchant(new AcquirerStatusReq.Merchant() {{
            merchantID = config.geteMandateContractId();
            subID = config.geteMandateContractSubId();
        }});
        
        stsReq.setTransaction(new AcquirerStatusReq.Transaction() {{
            transactionID = statusRequest.getTransactionID();
        }});
        
        return Utils.serialize(stsReq, schemas.idx.AcquirerStatusReq.class);
    }
    
    public String getTransactionRequest(final Configuration config, final AmendmentRequest amendmentRequest, final Element containedData)
            throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        verifyExpirationPeriod(amendmentRequest.getExpirationPeriod());
        
        AcquirerTrxReq trxReq = new AcquirerTrxReq();
        
        trxReq.setProductID((this.localInstrumentCode == Instrumentation.CORE)? ProductID_CORE : ProductID_B2B);
        trxReq.setVersion(Version);
        trxReq.setCreateDateTimestamp(Utils.UtcNow());

        trxReq.setMerchant(new AcquirerTrxReq.Merchant() {{
            setMerchantID(config.geteMandateContractId());
            setSubID(config.geteMandateContractSubId());
            setMerchantReturnURL(config.getMerchantReturnUrl());
        }});

        trxReq.setIssuer(new AcquirerTrxReq.Issuer() {{
            setIssuerID(amendmentRequest.getDebtorBankID());
        }});

        trxReq.setTransaction(new AcquirerTrxReq.Transaction() {{
            setEntranceCode(amendmentRequest.getEntranceCode());
            setExpirationPeriod(amendmentRequest.getExpirationPeriod());
            setLanguage(amendmentRequest.getLanguage());
            setContainer(new TransactionContainer());
        }});

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(
            Utils.serialize(trxReq, schemas.idx.AcquirerTrxReq.class).getBytes(StandardCharsets.UTF_8)), ""
        );
        Node node = doc.importNode(containedData, true);
        doc.getElementsByTagName("container").item(0).appendChild(node);

        return Utils.serialize(doc);
    }
    
    public String getTransactionRequest(final Configuration config, final CancellationRequest cancellationRequest, final Element containedData)
            throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException, JAXBException, DatatypeConfigurationException, CommunicatorException {
        verifyExpirationPeriod(cancellationRequest.getExpirationPeriod());
        
        AcquirerTrxReq trxReq = new AcquirerTrxReq();
        
        trxReq.setProductID((this.localInstrumentCode == Instrumentation.CORE)? ProductID_CORE : ProductID_B2B);
        trxReq.setVersion(Version);
        trxReq.setCreateDateTimestamp(Utils.UtcNow());

        trxReq.setMerchant(new AcquirerTrxReq.Merchant() {{
            setMerchantID(config.geteMandateContractId());
            setSubID(config.geteMandateContractSubId());
            setMerchantReturnURL(config.getMerchantReturnUrl());
        }});

        trxReq.setIssuer(new AcquirerTrxReq.Issuer() {{
            setIssuerID(cancellationRequest.getDebtorBankID());
        }});

        trxReq.setTransaction(new AcquirerTrxReq.Transaction() {{
            setEntranceCode(cancellationRequest.getEntranceCode());
            setExpirationPeriod(cancellationRequest.getExpirationPeriod());
            setLanguage(cancellationRequest.getLanguage());
            setContainer(new TransactionContainer());
        }});

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(
            Utils.serialize(trxReq, schemas.idx.AcquirerTrxReq.class).getBytes(StandardCharsets.UTF_8)), ""
        );
        Node node = doc.importNode(containedData, true);
        doc.getElementsByTagName("container").item(0).appendChild(node);

        return Utils.serialize(doc);
    }
}
