package net.emandates.merchant.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.DirectoryRes;

/**
 * Describes a directory response
 */
public class DirectoryResponse {
    /**
     * A debtor bank contained in a directory response
     */
    public class DebtorBank {
        private String debtorBankCountry;
        private String debtorBankName;
        private String debtorBankId;

        /**
         * @return Country name
         */
        public String getDebtorBankCountry() {
            return debtorBankCountry;
        }

        /**
         * @return Bank name
         */
        public String getDebtorBankName() {
            return debtorBankName;
        }

        /**
         * @return BIC
         */
        public String getDebtorBankId() {
            return debtorBankId;
        }

        /**
         * @param debtorBankCountry the debtorBankCountry to set
         */
        void setDebtorBankCountry(String debtorBankCountry) {
            this.debtorBankCountry = debtorBankCountry;
        }

        /**
         * @param debtorBankName the debtorBankName to set
         */
        void setDebtorBankName(String debtorBankName) {
            this.debtorBankName = debtorBankName;
        }

        /**
         * @param debtorBankId the debtorBankId to set
         */
        void setDebtorBankId(String debtorBankId) {
            this.debtorBankId = debtorBankId;
        }
    }
    
    private boolean isError;
    private ErrorResponse errorResponse;
    private XMLGregorianCalendar directoryDateTimestamp;
    private List<DebtorBank> debtorBanks;
    private String rawMessage;
    
    private DirectoryResponse(DirectoryRes dirRes, String xml) {
        isError = false;
        errorResponse = null;
        directoryDateTimestamp = dirRes.getCreateDateTimestamp();
        
        for (final DirectoryRes.Directory.Country c : dirRes.getDirectory().getCountry()) {
            for (final DirectoryRes.Directory.Country.Issuer i : c.getIssuer()) {
                DebtorBank db = new DebtorBank();
                db.setDebtorBankCountry(c.getCountryNames());
                db.setDebtorBankId(i.getIssuerID());
                db.setDebtorBankName(i.getIssuerName());
                
                getDebtorBanks().add(db);
            }
        }
        
        rawMessage = xml;
    }
    
    private DirectoryResponse(AcquirerErrorRes errRes, String xml) {
        isError = true;
        errorResponse = ErrorResponse.Get(errRes);
        directoryDateTimestamp = null;
        getDebtorBanks().clear();
        rawMessage = xml;
    }
    
    private DirectoryResponse(Throwable e) {
        isError = true;
        errorResponse = ErrorResponse.Get(e);
        directoryDateTimestamp = null;
        getDebtorBanks().clear();
        rawMessage = null;
    }
    
    static DirectoryResponse Parse(String xml) {
        try {
            DirectoryRes dirRes = (DirectoryRes) Utils.deserialize(xml, DirectoryRes.class);
            return new DirectoryResponse(dirRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new DirectoryResponse(errRes, xml);
            }
            catch (Exception e2) {
                DirectoryResponse dr = new DirectoryResponse(e2);
                dr.rawMessage = xml;
                return dr;
            }
        }
    }
    
    static DirectoryResponse Get(Throwable e) {
        return new DirectoryResponse(e);
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
     * @return Date set to when this directory was last updated
     */
    public XMLGregorianCalendar getDirectoryDateTimestamp() {
        return directoryDateTimestamp;
    }

    /**
     * @return List of available debtor banks
     */
    public final List<DebtorBank> getDebtorBanks() {
        if (debtorBanks == null) {
            debtorBanks = new ArrayList<>();
        }
        return debtorBanks;
    }
    
    /**
     * @return The response XML
     */
    public String getRawMessage() {
        return rawMessage;
    }

    /**
     * @return List of available debtor banks as a map where the key is each country and the value is a list of banks in that country
     */
    public final Map<String, List<DebtorBank>> getDebtorBanksByCountry() {
        List<DebtorBank> banks = getDebtorBanks();
        HashMap<String, List<DebtorBank>> ret = new HashMap<>();
        
        for (DebtorBank bank : banks) {
            if (ret.containsKey(bank.getDebtorBankCountry())) {
                ret.get(bank.getDebtorBankCountry()).add(bank);
            }
            else {
                ret.put(bank.getDebtorBankCountry(), new ArrayList<DebtorBank>());
                ret.get(bank.getDebtorBankCountry()).add(bank);
            }
        }
        
        return ret;
    }
}
