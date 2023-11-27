package net.emandates.merchant.library;

import java.util.Date;
import jakarta.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.AcquirerStatusRes;

/**
 * Represents a status response
 */
public class StatusResponse {
    /**
     * Received as part of a status response, corresponding to the pain.012 message
     */
    public static class AcceptanceReport {
        private String messageID;
        private Date dateTime;
        private String validationReference;
        private String originalMessageID;
        private String messageNameID;
        private boolean acceptedResult;
        private String originalMandateID;
        private String mandateRequestID;
        private String serviceLevelCode;
        private Instrumentation localInstrumentCode;
        private SequenceType sequenceType;
        private Double maxAmount;
        private String eMandateReason;
        private String creditorID;
        private String schemeName;
        private String creditorName;
        private String creditorCountry;
        private String[] creditorAddressLine;
        private String creditorTradeName;
        private String debtorAccountName;
        private String debtorReference;
        private String debtorIBAN;
        private String debtorBankID;
        private String debtorSignerName;
        private String rawMessage;
        
        private AcceptanceReport(schemas.pain012.Document doc) {
            schemas.pain012.GroupHeader47 grpHdr = doc.getMndtAccptncRpt().getGrpHdr();
            schemas.pain012.MandateAcceptance4 accDtls = doc.getMndtAccptncRpt().getUndrlygAccptncDtls().get(0);
            
            messageID = grpHdr.getMsgId();
            dateTime = grpHdr.getCreDtTm().toGregorianCalendar().getTime();
            validationReference = grpHdr.getAuthstn().get(0).getPrtry();
            originalMessageID = accDtls.getOrgnlMsgInf().getMsgId();
            messageNameID = accDtls.getOrgnlMsgInf().getMsgNmId();
            acceptedResult = accDtls.getAccptncRslt().isAccptd();
            
            schemas.pain012.Mandate5 origMndt = accDtls.getOrgnlMndt().getOrgnlMndt();
            
            originalMandateID = origMndt.getMndtId();
            mandateRequestID = origMndt.getMndtReqId();
            serviceLevelCode = origMndt.getTp().getSvcLvl().getCd();
            localInstrumentCode = Instrumentation.valueOf(origMndt.getTp().getLclInstrm().getCd());
            sequenceType = SequenceType.valueOf(origMndt.getOcrncs().getSeqTp().value());
            maxAmount = (origMndt.getMaxAmt() != null)? origMndt.getMaxAmt().getValue().doubleValue() : null;
            eMandateReason = (origMndt.getRsn() != null)? origMndt.getRsn().getCd() : "";
            creditorID = origMndt.getCdtrSchmeId().getId().getPrvtId().getOthr().get(0).getId();
            schemeName = origMndt.getCdtrSchmeId().getId().getPrvtId().getOthr().get(0).getSchmeNm().getCd();
            creditorName = origMndt.getCdtr().getNm();
            creditorCountry = origMndt.getCdtr().getPstlAdr().getCtry();
            creditorAddressLine = origMndt.getCdtr().getPstlAdr().getAdrLine().toArray(new String[0]);
            creditorTradeName = (origMndt.getUltmtCdtr() != null)? origMndt.getUltmtCdtr().getNm() : "";
            debtorAccountName = origMndt.getDbtr().getNm();
            debtorReference = (origMndt.getDbtr().getId() != null)? origMndt.getDbtr().getId().getPrvtId().getOthr().get(0).getId() : "";
            debtorIBAN = origMndt.getDbtrAcct().getId().getIBAN();
            debtorBankID = origMndt.getDbtrAgt().getFinInstnId().getBICFI();
            debtorSignerName = origMndt.getUltmtDbtr().getNm();
        }
        
        static AcceptanceReport Parse(Object obj) throws JAXBException, TransformerException {
            schemas.pain012.Document doc = Utils.deserialize((Node) obj, schemas.pain012.Document.class);
            AcceptanceReport acc = new AcceptanceReport(doc);
            acc.rawMessage = Utils.serializeWithoutDeclaration((Node) obj);
            return acc;
        }

        /**
         * @return Message Identification
         */
        public String getMessageID() {
            return messageID;
        }

        /**
         * @return Message timestamp
         */
        public Date getDateTime() {
            return dateTime;
        }

        /**
         * @return Validation reference
         */
        public String getValidationReference() {
            return validationReference;
        }

        /**
         * @return Original Message ID
         */
        public String getOriginalMessageID() {
            return originalMessageID;
        }

        /**
         * @return Refers to the type of validation request that preceded the acceptance report
         */
        public String getMessageNameID() {
            return messageNameID;
        }

        /**
         * @return Whether or not the mandate is accepted by the debtor
         */
        public boolean isAcceptedResult() {
            return acceptedResult;
        }

        /**
         * @return Original mandate ID
         */
        public String getOriginalMandateID() {
            return originalMandateID;
        }

        /**
         * @return Mandate request ID
         */
        public String getMandateRequestID() {
            return mandateRequestID;
        }

        /**
         * @return "SEPA"
         */
        public String getServiceLevelCode() {
            return serviceLevelCode;
        }

        /**
         * @return Core or B2B
         */
        public Instrumentation getLocalInstrumentCode() {
            return localInstrumentCode;
        }

        /**
         * @return Sequence Type: recurring or one-off
         */
        public SequenceType getSequenceType() {
            return sequenceType;
        }

        /**
         * @return Maximum amount
         */
        public Double getMaxAmount() {
            return maxAmount;
        }

        /**
         * @return Reason for eMandate
         */
        public String geteMandateReason() {
            return eMandateReason;
        }

        /**
         * @return Direct Debit ID of the Creditor
         */
        public String getCreditorID() {
            return creditorID;
        }

        /**
         * @return "SEPA"
         */
        public String getSchemeName() {
            return schemeName;
        }

        /**
         * @return Name of the Creditor
         */
        public String getCreditorName() {
            return creditorName;
        }

        /**
         * @return Country of the postal address of the Creditor
         */
        public String getCreditorCountry() {
            return creditorCountry;
        }

        /**
         * @return The Creditor’s address: P.O. Box or street name + building + add-on + Postcode + City. Second Address line only to
         * be used if 70 chars are exceeded in the first line
         */
        public String[] getCreditorAddressLine() {
            return creditorAddressLine;
        }

        /**
         * @return Name of the company (or daughter-company, or label etc.) for which the Creditor is processing eMandates. May only be
         * used when meaningfully different from CreditorName
         */
        public String getCreditorTradeName() {
            return creditorTradeName;
        }

        /**
         * @return Account holder name of the account that is used for the eMandate
         */
        public String getDebtorAccountName() {
            return debtorAccountName;
        }

        /**
         * @return Reference ID that identifies the Debtor to the Creditor. Issued by the Creditor
         */
        public String getDebtorReference() {
            return debtorReference;
        }

        /**
         * @return Debtor’s bank account number
         */
        public String getDebtorIBAN() {
            return debtorIBAN;
        }

        /**
         * @return BIC of the Debtor bank
         */
        public String getDebtorBankID() {
            return debtorBankID;
        }

        /**
         * @return Name of the person signing the eMandate. In case of multiple signing, all signer names must be included in this field,
         * separated by commas. If the total would exceed the maximum of 70 characters, the names are cut off at 65 characters and
         * "e.a." is added after the last name.
         */
        public String getDebtorSignerName() {
            return debtorSignerName;
        }
        
        /**
        * @return The response XML
        */
        public String getRawMessage() {
            return rawMessage;
        }
    };
    
    /**
     * Open
     */
    public static final String Open      = "Open";

    /**
     * Pending
     */
    public static final String Pending   = "Pending";

    /**
     * Success
     */
    public static final String Success   = "Success";

    /**
     * Failure
     */
    public static final String Failure   = "Failure";

    /**
     * Expired
     */
    public static final String Expired   = "Expired";

    /**
     * Cancelled
     */
    public static final String Cancelled = "Cancelled";
    
    private boolean isError;
    private ErrorResponse errorResponse;
    private String transactionID;
    private String status;
    private XMLGregorianCalendar statusDateTimestamp;
    private String rawMessage;
    private AcceptanceReport acceptanceReport;
    
    private StatusResponse(AcquirerStatusRes stsRes, String xml) throws JAXBException, TransformerException {
        isError = false; 
        errorResponse = null;
        transactionID = stsRes.getTransaction().getTransactionID();
        statusDateTimestamp = stsRes.getTransaction().getStatusDateTimestamp();
        status = stsRes.getTransaction().getStatus();
        rawMessage = xml;
        
        if (status.equalsIgnoreCase(Success)) {
            acceptanceReport = AcceptanceReport.Parse(stsRes.getTransaction().getContainer().getAny().get(0));
        }
    }
    
    private StatusResponse(AcquirerErrorRes errRes, String xml) {
        isError = true; 
        errorResponse = ErrorResponse.Get(errRes);
        transactionID = null;
        statusDateTimestamp = null;
        status = null;
        rawMessage = xml;
    }
    
    private StatusResponse(Throwable e) {
        isError = true; 
        errorResponse = ErrorResponse.Get(e);
        transactionID = null;
        statusDateTimestamp = null;
        status = null;
        rawMessage = null;
    }
    
    static StatusResponse Parse(String xml) {
        try {
            AcquirerStatusRes stsRes = (AcquirerStatusRes) Utils.deserialize(xml, AcquirerStatusRes.class);
            return new StatusResponse(stsRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new StatusResponse(errRes, xml);
            }
            catch (Exception e2) {
                StatusResponse sr = new StatusResponse(e2);
                sr.rawMessage = xml;
                return sr;
            }
        }
    }
    
    static StatusResponse Get(Throwable e) {
        return new StatusResponse(e);
    }

    /**
     * @return true if an error occured, or false when no errors were encountered
     */
    public boolean getIsError() {
        return isError;
    }

    /**
     * @return Object that holds the error if one occurs; when there are no errors, this is set to null
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    /**
     * @return The transaction ID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return Possible values: Open, Pending, Success, Failure, Expired, Cancelled
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return Date when the status was created, or null if no such date available (for example, when mandate has expired)
     */
    public XMLGregorianCalendar getStatusDateTimestamp() {
        return statusDateTimestamp;
    }

    /**
     * @return The response XML
     */
    public String getRawMessage() {
        return rawMessage;
    }

    /**
     * @return The acceptance report returned in the status response
     */
    public AcceptanceReport getAcceptanceReport() {
        return acceptanceReport;
    }
}
