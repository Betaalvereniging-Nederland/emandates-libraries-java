<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../_header.jsp"></jsp:include>

<h2>Buy</h2>

<div>
    <hr>

<c:choose>
    <c:when test="${Model.getIsError() == true}">
        <div class="alert alert-danger" role="alert">
            <div class="row">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                Error performing directory request: <br>
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
        <form action="<c:url value="/Products/Transaction" />" class="form-horizontal" method="post" role="form">             
            <div class="form-group">
                <div class="col-xs-6">
                    <h3>Product 1</h3>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <p class="help-block">Select the issuing bank</p>
                <div class="col-xs-6">
                    <select class="form-control" id="issuer" name="issuer">
                        
                        <c:forEach items="${Model.getDebtorBanksByCountry().keySet()}" var="c">
                            <optgroup label="${c}">
                                <c:forEach items="${Model.getDebtorBanksByCountry().get(c)}" var="b">
                                    <option value="${b.getDebtorBankId()}">${b.getDebtorBankName()}</option>    
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-6">
                    <button class="btn btn-primary">Next</button>
                </div>
            </div>
        </form>
    </c:otherwise>
</c:choose>
        
</div>

<jsp:include page="../_footer.jsp"></jsp:include>
