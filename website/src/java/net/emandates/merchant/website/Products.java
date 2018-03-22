package net.emandates.merchant.website;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.parsers.ParserConfigurationException;
import net.emandates.merchant.library.AmendmentRequest;
import net.emandates.merchant.library.AmendmentResponse;
import net.emandates.merchant.library.B2BCommunicator;
import net.emandates.merchant.library.CancellationRequest;
import net.emandates.merchant.library.CancellationResponse;
import net.emandates.merchant.library.Configuration;
import net.emandates.merchant.library.CoreCommunicator;
import net.emandates.merchant.library.DirectoryResponse;
import net.emandates.merchant.library.NewMandateRequest;
import net.emandates.merchant.library.NewMandateResponse;
import net.emandates.merchant.library.SequenceType;
import net.emandates.merchant.library.StatusRequest;
import net.emandates.merchant.library.StatusResponse;
import org.xml.sax.SAXException;

public class Products extends HttpServlet {
    private CoreCommunicator coreComm;
    private B2BCommunicator b2bComm;
    
    @Override
    public void init() throws ServletException {
        try {
            Configuration.defaultInstance().Load(getServletContext().getResourceAsStream("/WEB-INF/emandates-config.xml"));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new ServletException(ex.getMessage());
        }
        
        coreComm = new CoreCommunicator();
        b2bComm = new B2BCommunicator();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getRequestURI().split("/");
        response.setContentType("text/html; charset=utf-8");
        switch (split[split.length-1]) {
        case "List":
            {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/index.jsp");
                rd.include(request, response);
                break;
            }
        case "Select":
            {
                DirectoryResponse dr = coreComm.directory();
                request.setAttribute("Model", dr);
                
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/buy.jsp");
                rd.include(request, response);
                break;
            }
        case "Transaction":
            {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/transaction.jsp");
                rd.include(request, response);
                break;
            }
        case "Amend":
            {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/amend.jsp");
                rd.include(request, response);
                break;
            }
        case "Cancel":
            {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/cancel.jsp");
                rd.include(request, response);
                break;
            }
        case "Status":
            {
                StatusResponse sr = coreComm.getStatus(new StatusRequest((String)request.getParameter("TransactionId")));
                request.setAttribute("Model", sr);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/status.jsp");
                rd.include(request, response);
                break;
            }
        }
    }
    
    private Double getDecimalValue(String value) {
        if (value == null || value.isEmpty())
        {
            return null;
        }
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        
        value = value.replace(',', df.getDecimalFormatSymbols().getDecimalSeparator());
        value = value.replace('.', df.getDecimalFormatSymbols().getDecimalSeparator());
        
        return Double.valueOf(value);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpServletRequest req = request;
        final HttpServletResponse res = response;
        
        String[] split = req.getRequestURI().split("/");
        switch (split[split.length-1]) {
        case "Transaction":
        {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/products/transaction.jsp");
            rd.include(req, res);
            break;
        }
        case "NewMandateResult":
            {
                Duration d = null;
                try {
                    String exp = (String)req.getParameter("ExpirationPeriod");
                    if (exp != null && !exp.isEmpty())
                        d = DatatypeFactory.newInstance().newDuration(exp);
                } catch (DatatypeConfigurationException ex) {
                    throw new ServletException(ex.getMessage());
                }
                
                NewMandateRequest nmr = new NewMandateRequest(
                    (String)req.getParameter("EntranceCode"),
                    (String) req.getParameter("Language"), 
                    d,
                    (String) req.getParameter("eMandateId"),
                    (String) req.getParameter("eMandateReason"),
                    (String) req.getParameter("DebtorReference"),
                    (String)req.getParameter("DebtorBankId"),
                    (String) req.getParameter("PurchaseId"),
                    SequenceType.valueOf((String) req.getParameter("SequenceType")),
                    getDecimalValue((String) req.getParameter("MaxAmount"))
                );
                
                if (req.getParameter("MessageId") != null && !req.getParameter("MessageId").isEmpty()) {
                    nmr.setMessageID((String) req.getParameter("MessageId"));
                }
                
                NewMandateResponse mandateResponse = coreComm.newMandate(nmr);
                req.setAttribute("Model", mandateResponse);
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/products/newmandateresult.jsp");
                rd.include(req, response);
                break;
            }
        case "Status":
            {
                StatusResponse sr = coreComm.getStatus(new StatusRequest((String)request.getParameter("TransactionId")));
                request.setAttribute("Model", sr);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products/status.jsp");
                rd.include(request, response);
                break;
            }
        case "AmendMandateResult":
            {
                Duration d = null;
                try {
                    String exp = (String)req.getParameter("ExpirationPeriod");
                    if (exp != null && !exp.isEmpty())
                        d = DatatypeFactory.newInstance().newDuration(exp);
                } catch (DatatypeConfigurationException ex) {
                    throw new ServletException(ex.getMessage());
                }
                
                AmendmentRequest ar = new AmendmentRequest(
                    (String)req.getParameter("EntranceCode"),
                    (String) req.getParameter("Language"), 
                    d,
                    (String) req.getParameter("eMandateId"),
                    (String) req.getParameter("eMandateReason"),
                    (String) req.getParameter("DebtorReference"),
                    (String)req.getParameter("DebtorBankId"),
                    (String) req.getParameter("PurchaseId"),
                    SequenceType.valueOf((String) req.getParameter("SequenceType")),
                    (String) request.getParameter("OriginalIBAN"),
                    (String) request.getParameter("OriginalDebtorBankId")
                );
                
                if (req.getParameter("MessageId") != null && !req.getParameter("MessageId").isEmpty()) {
                    ar.setMessageID((String) req.getParameter("MessageId"));
                }
                
                AmendmentResponse amendmentResponse = coreComm.amend(ar);
                req.setAttribute("Model", amendmentResponse);
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/products/amendmandateresult.jsp");
                rd.include(req, response);
                break;
            }
        case "CancelMandateResult":
            {
                Duration d = null;
                try {
                    String exp = (String)req.getParameter("ExpirationPeriod");
                    if (exp != null && !exp.isEmpty())
                        d = DatatypeFactory.newInstance().newDuration(exp);
                } catch (DatatypeConfigurationException ex) {
                    throw new ServletException(ex.getMessage());
                }
                
                CancellationRequest cr = new CancellationRequest(
                    (String)req.getParameter("EntranceCode"),
                    (String) req.getParameter("Language"), 
                    d,
                    (String) req.getParameter("eMandateId"),
                    (String) req.getParameter("eMandateReason"),
                    (String) req.getParameter("DebtorReference"),
                    (String)req.getParameter("DebtorBankId"),
                    (String) req.getParameter("PurchaseId"),
                    SequenceType.valueOf((String) req.getParameter("SequenceType")),
                    getDecimalValue((String) req.getParameter("MaxAmount")),
                    (String) request.getParameter("OriginalIBAN")
                );
                
                if (req.getParameter("MessageId") != null && !req.getParameter("MessageId").isEmpty()) {
                    cr.setMessageID((String) req.getParameter("MessageId"));
                }
                
                CancellationResponse cancellationResponse = b2bComm.cancel(cr);
                req.setAttribute("Model", cancellationResponse);
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/products/cancelmandateresult.jsp");
                rd.include(req, response);
                break;
            }
        }
    }
}
