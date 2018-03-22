<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../_header.jsp"></jsp:include>

<h2>Products list</h2>

<table class="table">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Product 1</td>
        <td>
            <a href="<c:url value="/Products/Select" />">Buy</a>
        </td>
    </tr>
</table>

<jsp:include page="../_footer.jsp"></jsp:include>
