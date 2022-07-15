<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="hr.lcabraja.webshop.model.Order" %>
<%@ page import="hr.lcabraja.webshop.servlet.CheckoutServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body>

<%@include file="includes/navbar.jsp"%>
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
    <div class="d-flex py-3"><h3>Total Price: $ ${total}</h3> <a class="mx-3 btn btn-primary" href="checkout.jsp">Check Out</a></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="product">
            <tr>
                <td>${product.getProduct().getTitle()}</td>
                <td>${product.getProduct().getCategory().getName()}</td>
                <td>$ ${product.getProduct().getPrice() * product.getQuantity()}</td>
                <td>
                    <form action="receipt-now" method="post" class="form-inline">
                        <div class="form-group d-flex justify-content-between">
                            <a class="btn bnt-sm btn-incre" href="cart/change-quantity?id=${product.getProduct().getId()}&action=inc"><i class="fas fa-plus-square"></i></a>
                            <input type="text" name="quantity" class="form-control" value="${product.getQuantity()}" readonly>
                            <a class="btn btn-sm btn-decre" href="cart/change-quantity?id=${product.getProduct().getId()}&action=dec"><i class="fas fa-minus-square"></i></a>
                        </div>
                    </form>
                </td>
                <td><a href="cart/remove-item?id=${product.product.id}" class="btn btn-sm btn-danger">Remove</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
