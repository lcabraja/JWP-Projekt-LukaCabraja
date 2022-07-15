<%@ page import="java.util.List" %>
<%@ page import="hr.lcabraja.webshop.model.Order" %>
<%@ page import="hr.lcabraja.webshop.servlet.CheckoutServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@include file="/includes/head.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<c:import url="/cart/all"/>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");

    double total = 0;
    for (Order item : orders ) {
        total += item.getQuantity() * item.getProduct().getPrice();
    }
    request.setAttribute("total", CheckoutServlet.df.format(total));
%>

<div class="container my-3">
    <div class="d-flex py-3"><h3>Summary</h3></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Product</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="product">
            <tr>
                <td>${product.getProduct().getTitle()}</td>
                <td>${product.getQuantity()}</td>
                <td>💶${product.getProduct().getPrice() * product.getQuantity()}</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    <p>Total: <b>💶${total}</b></p>
    <form action="checkout-perform?total=${total}" method="post">
        Payment method:
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="payment" value="PAYPAL" required>
            <label class="form-check-label">PayPal</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="payment" value="CASH" required>
            <label class="form-check-label">Cash</label>
        </div>
        <br>
        <c:choose>
            <c:when test="${username == null}">
                <div class="nav-item"><a class="nav-link" href="login.jsp">Login</a></div>
            </c:when>
            <c:otherwise>
                <input type="submit" class="btn btn-primary" value="Buy"/>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>
