<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../_header.jsp"></jsp:include>

<div class="jumbotron">
    <h1>eMandates Merchant Website</h1>
    <p class="lead">This is a test website for the eMandates Merchant Library.</p>
    <p>
        <a href="<c:url value="/Products/List" />" class="btn btn-primary btn-lg">Browse products &raquo;</a>
    </p>
</div>

<jsp:include page="../_footer.jsp"></jsp:include>
