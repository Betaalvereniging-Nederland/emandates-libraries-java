package net.emandates.merchant.library;

/**
 * Describes a status request
 */
public class StatusRequest {
    private String transactionID;
    
    /**
     * Parameterless constructor, so it can be used as a Model in views
     */
    public StatusRequest() {
    }
    
    /**
     * Constructor that highlights all required fields for this object
     * @param transactionID The transaction ID to check
     */
    public StatusRequest(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
