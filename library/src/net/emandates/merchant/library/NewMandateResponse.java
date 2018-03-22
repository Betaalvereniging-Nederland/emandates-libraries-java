package net.emandates.merchant.library;

import javax.xml.datatype.XMLGregorianCalendar;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.AcquirerTrxRes;

/**
 * Describes a new mandate response
 */
public class NewMandateResponse {
    private boolean isError;
    private ErrorResponse errorResponse;
    private String issuerAuthenticationURL;
    private String transactionID;
    private XMLGregorianCalendar transactionCreateDateTimestamp;
    private String rawMessage;
    
    private NewMandateResponse(AcquirerTrxRes trxRes, String xml) {
        isError = false; 
        errorResponse = null;
        issuerAuthenticationURL = trxRes.getIssuer().getIssuerAuthenticationURL();
        transactionID = trxRes.getTransaction().getTransactionID();
        transactionCreateDateTimestamp = trxRes.getTransaction().getTransactionCreateDateTimestamp();
        rawMessage = xml;
    }
    
    private NewMandateResponse(AcquirerErrorRes errRes, String xml) {
        isError = true;
        errorResponse = ErrorResponse.Get(errRes);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = xml;
    }
    
    private NewMandateResponse(Throwable e) {
        isError = true;
        errorResponse = ErrorResponse.Get(e);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = null;
    }
    
    static NewMandateResponse Parse(String xml) {
        try {
            AcquirerTrxRes trxRes = (AcquirerTrxRes) Utils.deserialize(xml, AcquirerTrxRes.class);
            return new NewMandateResponse(trxRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new NewMandateResponse(errRes, xml);
            }
            catch (Exception e2) {
                NewMandateResponse nmr = new NewMandateResponse(e2);
                nmr.rawMessage = xml;
                return nmr;
            }
        }
    }
    
    static NewMandateResponse Get(Throwable e) {
        return new NewMandateResponse(e);
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
     * @return The URL to which to redirect the creditor so they can authorize the transaction
     */
    public String getIssuerAuthenticationURL() {
        return issuerAuthenticationURL;
    }

    /**
     * @return The transaction ID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return Date set to when this transaction was created
     */
    public XMLGregorianCalendar getTransactionCreateDateTimestamp() {
        return transactionCreateDateTimestamp;
    }

    /**
     * @return The response XML
     */
    public String getRawMessage() {
        return rawMessage;
    }
}
