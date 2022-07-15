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

<c:import url="/admin/"/>
<%@include file="includes/navbar.jsp" %>

<div>
    <form class="form-row" action="" style="width: 30%">
        <div class="input-group">
            <div class="input-group-prepend"><input class="form-control" type="text" name="user"></div>
            <div class="input-group-append"><input class="form-control" type="local-datetime" name="datetime"></div>
            <div class="input-group-append"><input class="btn btn-primary" type="submit" value="Filter"></div>
        </div>
    </form>
    <c:forEach items="${transactions}" var="transaction">
        <h3><b>${transaction.getReceipt().getUser().getUsername()} @ ${transaction.getReceipt().getCreatedAt()}</b></h3>
        <table class="table table-light">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${transaction.getOrders()}" var="product">
                <tr>
                    <td>${product.getProduct().getTitle()}</td>
                    <td>${product.getQuantity()}</td>
                    <td>💶${product.getProduct().getPrice()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table class="table table-light">
            <thead>
            <tr>
                <th scope="col"><b>Total</b></th>
                <th scope="col"><b>Payment method</b></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>💶${transaction.getReceipt().getTotal()}</td>
                <td>${transaction.getReceipt().getPayment()}</td>
            </tr>
            </tbody>
        </table>
    </c:forEach>
</div>
</body>
</html>
