<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../_header.jsp"></jsp:include>

<h2>Result</h2>

<div>
    <hr>
    
<c:choose>
    <c:when test="${Model.getIsError() == true}">
        <div class="alert alert-danger" role="alert">
            <div class="row">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                Error performing transaction request: <br>
                <c:if test="${Model.getErrorResponse().getErrorCode().isEmpty() == false}">
                    Message: ${Model.getErrorResponse().getErrorCode()}<br>
                </c:if>
                <c:if test="${Model.getErrorResponse().getErrorMessage().isEmpty() == false}">
                    Message: ${Model.getErrorResponse().getErrorMessage()}<br>
                </c:if>
                <c:if test="${Model.getErrorResponse().getErrorDetails().isEmpty() == false}">
                    Details: ${Model.getErrorResponse().getErrorDetails()}<br>
                </c:if>
                    <c:if test="${Model.getErrorResponse().getConsumerMessage().isEmpty() == false}">
                    Consumer message: ${Model.getErrorResponse().getConsumerMessage()}<br>
                </c:if>
                <c:if test="${Model.getErrorResponse().getSuggestedAction().isEmpty() == false}">
                    Suggested action: ${Model.getErrorResponse().getSuggestedAction()}<br>
                </c:if>
            </div>
        </div>
    </c:when>
    
    <c:otherwise>
        <form role="form" class="form-horizontal">
            <div class="form-group">
                <div class="col-xs-4">
                    <label for="TransactionId">TransactionId</label>
                </div>
                <div class="col-xs-4">
                    <label for="StatusDateTimestamp">StatusDateTimestamp</label>
                </div>
                <div class="col-xs-4">
                    <label for="Status">Status</label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-4">
                    ${Model.getTransactionID()}
                </div>
                <div class="col-xs-4">
                    ${Model.getStatusDateTimestamp()}
                </div>
                <div class="col-xs-4">
                    ${Model.getStatus()}
                </div>
            </div>
        </form>
        <c:if test="${Model.getStatus().equals('Success')}">
            <form role="form" class="form-horizontal">
                <div class="form-group">
                    <textarea class="form-control" cols="20" id="acc" name="acc" readonly="true" rows="15">${Model.getAcceptanceReport().getRawMessage()}</textarea>
                </div>
            </form>
            <a href="<c:url value="/Products/Amend" />">Create amendment request</a>             | 
            <a href="<c:url value="/Products/Cancel" />">Create cancellation request</a>            <br>
        </c:if>
    </c:otherwise>
</c:choose>

<jsp:include page="../_footer.jsp"></jsp:include>
