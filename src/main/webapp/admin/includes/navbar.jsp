<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="hr.lcabraja.webshop.model.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Object role = request.getSession().getAttribute("userRole");
    boolean isAdmin = role != null ? role == Role.ADMINISTRATOR : false;
    String path = request.getContextPath();
    request.setAttribute("isAdmin", isAdmin);
    request.setAttribute("path", path);
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="../index.jsp">User Site</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:if test="${isAdmin == true}">
                    <li class="nav-item"><a class="nav-link" href="${path}/admin/products.jsp">Products</a></li>
                    <li class="nav-item"><a class="nav-link" href="${path}/admin/categories.jsp">Categories</a></li>
                    <li class="nav-item"><a class="nav-link" href="${path}/admin/orders.jsp">Orders</a></li>
                    <li class="nav-item"><a class="nav-link" href="${path}/admin/audits.jsp">Audits</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
