<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../_header.jsp"></jsp:include>

<h2>Cancel</h2>

<div>
    <hr>

    <form action="<c:url value="/Products/CancelMandateResult" />" class="form-horizontal" method="post" role="form">        <div class="form-group">
            <p class="help-block">Transaction information</p>
            <div class="col-xs-3">
                <label for="EntranceCode">EntranceCode</label>
                <input class="form-control" id="EntranceCode" name="EntranceCode" type="text" value="">
            </div>
            <div class="col-xs-3">
                <label for="ExpirationPeriod">ExpirationPeriod</label>
                <input class="form-control" id="ExpirationPeriod" name="ExpirationPeriod" type="text" value="">
            </div>
            <div class="col-xs-3">
                <label for="Language">Language</label>
                <select class="form-control" id="Language" name="Language"><option selected="selected">en</option>
<option>nl</option>
<option>ro</option>
<option>it</option>
</select>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <p class="help-block">eMandate information</p>
            <div class="col-xs-2">
                <label for="DebtorBankId">DebtorBankId</label>
                <input class="form-control" id="DebtorBankId" name="DebtorBankId" type="text" value="">
            </div>
            <div class="col-xs-2">
                <label for="DebtorReference">DebtorReference</label>
                <input class="form-control" id="DebtorReference" name="DebtorReference" type="text" value="">
            </div>
            <div class="col-xs-3">
                <label for="eMandateId">eMandateId</label>
                <input class="form-control" id="eMandateId" name="eMandateId" type="text" value="">
            </div>
            <div class="col-xs-2">
                <label for="MessageId">MessageId</label>
                <input class="form-control" id="MessageId" name="MessageId" type="text" value="">
            </div>
            <div class="col-xs-2">
                <label for="MaxAmount">MaxAmount</label>
                <input class="form-control" id="MaxAmount" name="MaxAmount" type="text" value="0">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-3">
                <label for="SequenceType">SequenceType</label>
                <select class="form-control" id="SequenceType" name="SequenceType"><option>OOFF</option>
<option selected="selected">RCUR</option>
</select>
            </div>
            <div class="col-xs-3">
                <label for="eMandateReason">eMandateReason</label>
                <input class="form-control" id="eMandateReason" name="eMandateReason" type="text" value="">
            </div>
            <div class="col-xs-3">
                <label for="PurchaseId">PurchaseId</label>
                <input class="form-control" id="PurchaseId" name="PurchaseId" type="text" value="">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-3">
                <label for="OriginalIBAN">OriginalIBAN</label>
                <input class="form-control" id="OriginalIBAN" name="OriginalIBAN" type="text" value="">
            </div>
        </div>
        <hr>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Send cancellation request</button>
        </div>
</form>
</div>
    
<jsp:include page="../_footer.jsp"></jsp:include>
