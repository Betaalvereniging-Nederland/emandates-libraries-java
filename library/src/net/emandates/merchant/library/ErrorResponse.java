package net.emandates.merchant.library;

import schemas.idx.AcquirerErrorRes;

/**
 * Describes an error response
 */
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String errorDetails;
    private String suggestedAction;
    private String consumerMessage;
    
    private ErrorResponse(AcquirerErrorRes errRes) {
        errorCode = errRes.getError().getErrorCode();
        errorMessage = errRes.getError().getErrorMessage();
        errorDetails = errRes.getError().getErrorDetail();
        suggestedAction = errRes.getError().getSuggestedAction();
        consumerMessage = errRes.getError().getConsumerMessage();
    }
    
    private ErrorResponse(Throwable e) {
        errorCode = "";
        errorMessage = e.getMessage();
        errorDetails = e.getCause() != null? e.getCause().getMessage() : "";
        suggestedAction = "";
        consumerMessage = "";
    }
    
    static ErrorResponse Get(AcquirerErrorRes errRes) {
        return new ErrorResponse(errRes);
    }
    
    static ErrorResponse Get(Throwable e) {
        return new ErrorResponse(e);
    }

    /**
     * @return Unique identification of the error occurring within the iDx transaction
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return Descriptive text accompanying Error.errorCode
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return Details of the error
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * @return Suggestions aimed at resolving the problem
     */
    public String getSuggestedAction() {
        return suggestedAction;
    }

    /**
     * @return A (standardised) message that the merchant should show to the consumer
     */
    public String getConsumerMessage() {
        return consumerMessage;
    }
}
