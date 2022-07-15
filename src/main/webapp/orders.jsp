<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%@include file="/includes/head.jsp"%>
<body>
<c:import url="/orders"/>
<%@include file="includes/navbar.jsp"%>
<h2>Orders</h2>
<c:forEach items="${transactions}" var="transaction">
    <h3><b>${transaction.getReceipt().getCreatedAt()}</b></h3>
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
                <td>💶${product.getProduct().getPrice() * product.getQuantity()}</td>
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
            <td>$ ${transaction.getReceipt().getTotal()}</td>
            <td>${transaction.getReceipt().getPayment()}</td>
        </tr>
        </tbody>
    </table>
</c:forEach>

</body>
</html>
