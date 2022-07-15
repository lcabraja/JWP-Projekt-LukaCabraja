

<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="hr.lcabraja.webshop.util.CartUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@include file="../includes/head.jsp" %>
<body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous">
</script>

<c:import url="/admin"/>
<%@include file="includes/navbar.jsp" %>

<table class="table table-light">
    <thead>
    <tr>
        <th scope="col">Image</th>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Price</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><img src="${product.getImageHref() != null ? product.getImageHref() : ""}" width="50" height="50"></td>
            <td>${product.getTitle()}</td>
            <td>${product.getCategory().getName()}</td>
            <td>💶${product.getPrice()}</td>
            <td><a class="btn btn-danger" href="../admin/servlet/newproduct?id=${product.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>