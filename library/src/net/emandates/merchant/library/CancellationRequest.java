package net.emandates.merchant.library;

import javax.xml.datatype.Duration;
import net.emandates.merchant.library.misc.MessageIdGenerator;

/**
 * Describes a cancellation request.
 */
public class CancellationRequest {
    private String entranceCode;
    private String language;
    private Duration expirationPeriod;
    private String debtorBankID;
    private String messageID;
    private String eMandateID;
    private SequenceType sequenceType;
    private String eMandateReason;
    private String debtorReference;
    private String purchaseID;
    private Double maxAmount;
    private String originalIBAN;
    
    /**
     * Parameterless constructor, so it can be used as a Model in views.
     */
    public CancellationRequest() {
    }
    
    /**
     * Constructor that highlights all required fields for this object; use this one to specify your own messageId.
     * @param entranceCode An 'authentication identifier' to facilitate continuation of the session between creditor
     * and debtor, even if the existing session has been lost.
     * @param language This field enables the debtor bank's site to select the debtor's preferred language (e.g. the
     * language selected on the creditor's site), if the debtor bank's site supports this: Dutch = 'nl', English = 'en'
     * @param expirationPeriod The period of validity of the transaction request as stated by the creditor measured
     * from the receipt by the debtor bank. The debtor must authorise the transaction within this period.
     * @param eMandateID ID that identifies the mandate and is issued by the creditor
     * @param eMandateReason Reason of the mandate
     * @param debtorReference Reference ID that identifies the debtor to creditor, which is issued by the creditor
     * @param debtorBankID BIC of the Debtor Bank
     * @param purchaseID A purchaseID that acts as a reference from eMandate to the purchase-order
     * @param sequenceType Indicates type of eMandate: one-off or recurring direct debit.
     * @param maxAmount Maximum amount. Not allowed for Core, optional for B2B.
     * @param originalIBAN IBAN of the original mandate
     * @param messageID Message ID for pain message
     */
    public CancellationRequest(String entranceCode, String language, Duration expirationPeriod, String eMandateID, String eMandateReason,
        String debtorReference, String debtorBankID, String purchaseID, SequenceType sequenceType, Double maxAmount,
        String originalIBAN, String messageID) {
        this.entranceCode = entranceCode;
        this.language = language;
        this.expirationPeriod = expirationPeriod;
        this.eMandateID = eMandateID;
        this.eMandateReason = eMandateReason;
        this.debtorReference = debtorReference;
        this.debtorBankID = debtorBankID;
        this.purchaseID = purchaseID;
        this.sequenceType = sequenceType;
        this.maxAmount = maxAmount;
        this.originalIBAN = originalIBAN;
        this.messageID = messageID;
    }
    
    /**
     * Constructor that highlights all required fields for this object; use this one if you wish the library to
     * generate a MessageId.
     * @param entranceCode An 'authentication identifier' to facilitate continuation of the session between creditor
     * and debtor, even if the existing session has been lost.
     * @param language This field enables the debtor bank's site to select the debtor's preferred language (e.g. the
     * language selected on the creditor's site), if the debtor bank's site supports this: Dutch = 'nl', English = 'en'
     * @param expirationPeriod The period of validity of the transaction request as stated by the creditor measured
     * from the receipt by the debtor bank. The debtor must authorise the transaction within this period.
     * @param eMandateID ID that identifies the mandate and is issued by the creditor
     * @param eMandateReason Reason of the mandate
     * @param debtorReference Reference ID that identifies the debtor to creditor, which is issued by the creditor
     * @param debtorBankID BIC of the Debtor Bank
     * @param purchaseID A purchaseID that acts as a reference from eMandate to the purchase-order
     * @param sequenceType Indicates type of eMandate: one-off or recurring direct debit.
     * @param maxAmount Maximum amount. Not allowed for Core, optional for B2B.
     * @param originalIBAN IBAN of the original mandate
     */
    public CancellationRequest(String entranceCode, String language, Duration expirationPeriod, String eMandateID, String eMandateReason,
        String debtorReference, String debtorBankID, String purchaseID, SequenceType sequenceType, Double maxAmount,
        String originalIBAN) {
        this.entranceCode = entranceCode;
        this.language = language;
        this.expirationPeriod = expirationPeriod;
        this.eMandateID = eMandateID;
        this.eMandateReason = eMandateReason;
        this.debtorReference = debtorReference;
        this.debtorBankID = debtorBankID;
        this.purchaseID = purchaseID;
        this.sequenceType = sequenceType;
        this.maxAmount = maxAmount;
        this.originalIBAN = originalIBAN;
        this.messageID = MessageIdGenerator.New();
    }

    /**
     * @return the entranceCode
     */
    public String getEntranceCode() {
        return entranceCode;
    }

    /**
     * @param entranceCode the entranceCode to set
     */
    public void setEntranceCode(String entranceCode) {
        this.entranceCode = entranceCode;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the expirationPeriod
     */
    public Duration getExpirationPeriod() {
        return expirationPeriod;
    }

    /**
     * @param expirationPeriod the expirationPeriod to set
     */
    public void setExpirationPeriod(Duration expirationPeriod) {
        this.expirationPeriod = expirationPeriod;
    }

    /**
     * @return the debtorBankID
     */
    public String getDebtorBankID() {
        return debtorBankID;
    }

    /**
     * @param debtorBankID the debtorBankID to set
     */
    public void setDebtorBankID(String debtorBankID) {
        this.debtorBankID = debtorBankID;
    }

    /**
     * @return the messageID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * @param messageID the messageID to set
     */
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     * @return the eMandateID
     */
    public String geteMandateID() {
        return eMandateID;
    }

    /**
     * @param eMandateID the eMandateID to set
     */
    public void seteMandateID(String eMandateID) {
        this.eMandateID = eMandateID;
    }

    /**
     * @return the sequenceType
     */
    public SequenceType getSequenceType() {
        return sequenceType;
    }

    /**
     * @param sequenceType the sequenceType to set
     */
    public void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    /**
     * @return the eMandateReason
     */
    public String geteMandateReason() {
        return eMandateReason;
    }

    /**
     * @param eMandateReason the eMandateReason to set
     */
    public void seteMandateReason(String eMandateReason) {
        this.eMandateReason = eMandateReason;
    }

    /**
     * @return the debtorReference
     */
    public String getDebtorReference() {
        return debtorReference;
    }

    /**
     * @param debtorReference the debtorReference to set
     */
    public void setDebtorReference(String debtorReference) {
        this.debtorReference = debtorReference;
    }

    /**
     * @return the purchaseID
     */
    public String getPurchaseID() {
        return purchaseID;
    }

    /**
     * @param purchaseID the purchaseID to set
     */
    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     * @return the maxAmount
     */
    public Double getMaxAmount() {
        return maxAmount;
    }

    /**
     * @param maxAmount the maxAmount to set
     */
    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * @return the originalIBAN
     */
    public String getOriginalIBAN() {
        return originalIBAN;
    }

    /**
     * @param originalIBAN the originalIBAN to set
     */
    public void setOriginalIBAN(String originalIBAN) {
        this.originalIBAN = originalIBAN;
    }
}
