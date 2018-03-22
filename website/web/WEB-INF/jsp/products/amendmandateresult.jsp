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
        <form action="<c:url value="/Products/Status" />" class="form-horizontal" method="post" role="form">            <div class="form-group">
                <div class="col-xs-4">
                    <label for="TransactionId">TransactionId</label>
                </div>
                <div class="col-xs-4">
                    <label for="TransactionCreateDateTimestamp">TransactionCreateDateTimestamp</label>
                </div>
                <div class="col-xs-4">
                    <label for="IssuerAuthenticationUrl">IssuerAuthenticationUrl</label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-4">
                    <input class="form-control" id="TransactionId" name="TransactionId" readonly="" type="text" value="${Model.getTransactionID()}">
                </div>
                <div class="col-xs-4">
                    <input class="form-control" id="TransactionCreateDateTimestamp" name="TransactionCreateDateTimestamp" readonly="" type="text" value="${Model.getTransactionCreateDateTimestamp()}">
                </div>
                <div class="col-xs-4">
                    ${Model.getIssuerAuthenticationURL()}
                </div>
            </div>
            <hr>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Get status</button>
            </div>
            <hr>
        </form>
    </c:otherwise>
</c:choose>
    
</div>

<jsp:include page="../_footer.jsp"></jsp:include>
