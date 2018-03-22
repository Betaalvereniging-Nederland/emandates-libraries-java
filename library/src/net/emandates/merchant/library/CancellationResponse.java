package net.emandates.merchant.library;

import javax.xml.datatype.XMLGregorianCalendar;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.AcquirerTrxRes;

/**
 * Represents a cancellation response
 */
public class CancellationResponse {
    private boolean isError;
    private ErrorResponse errorResponse;
    private String issuerAuthenticationURL;
    private String transactionID;
    private XMLGregorianCalendar transactionCreateDateTimestamp;
    private String rawMessage;
    
    private CancellationResponse(AcquirerTrxRes trxRes, String xml) {
        isError = false; 
        errorResponse = null;
        issuerAuthenticationURL = trxRes.getIssuer().getIssuerAuthenticationURL();
        transactionID = trxRes.getTransaction().getTransactionID();
        transactionCreateDateTimestamp = trxRes.getTransaction().getTransactionCreateDateTimestamp();
        rawMessage = xml;
    }
    
    private CancellationResponse(AcquirerErrorRes errRes, String xml) {
        isError = true;
        errorResponse = ErrorResponse.Get(errRes);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = xml;
    }
    
    private CancellationResponse(Throwable e) {
        isError = true;
        errorResponse = ErrorResponse.Get(e);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = null;
    }
    
    static CancellationResponse Parse(String xml) {
        try {
            AcquirerTrxRes trxRes = (AcquirerTrxRes) Utils.deserialize(xml, AcquirerTrxRes.class);
            return new CancellationResponse(trxRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new CancellationResponse(errRes, xml);
            }
            catch (Exception e2) {
                CancellationResponse cr = new CancellationResponse(e2);
                cr.rawMessage = xml;
                return cr;
            }
        }
    }
    
    static CancellationResponse Get(Throwable e) {
        return new CancellationResponse(e);
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
