<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="/includes/head.jsp" %>
<body>

<c:import url="/products"/>
<%@include file="includes/navbar.jsp" %>
<form action="" method="get" style="width: 30%">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <input type="text" name="query" class="form-control"/>
        </div>
        <select name="categoryFilter" class="form-control">
            <option selected value="-1">Choose...</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.getId()}"/>
                    ${category.getName()}
                </option>
            </c:forEach>
        </select>
        <div class="input-group-append">
            <input class="btn btn-primary" type="submit" value="Search"/>
        </div>
    </div>

</form>
<div class="row row-cols-1 row-cols-md-2">
    <c:forEach items="${orders}" var="product">
        <div class="card" style="width: 18rem; margin: 0.5rem">
            <img class="card-img-top" src="${product.getProduct().getImageHref()}" alt="Card image cap">
            <div class="card-body">
                <c:choose>
                    <c:when test="${product.getQuantity() != 0}">
                        <a href="cart/remove-item?id=${product.getProduct().getId()}" class="btn btn-danger-outline"
                           style="width: 100%;">Remove from cart</a>
                    </c:when>
                    <c:otherwise>
                        <a href="cart/add-item?id=${product.getProduct().getId()}" class="btn btn-primary"
                           style="width: 100%;">Add to cart</a>
                    </c:otherwise>
                </c:choose>
                <h5 class="card-title">${product.getProduct().getTitle()}</h5>
                <h6>$ ${product.getProduct().getPrice()}</h6>
                <p class="card-text">${product.getProduct().getDescription()}</p>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>