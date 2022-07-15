<%@ page import="java.util.HashMap" %>
<%@ page import="hr.lcabraja.webshop.model.Role" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">
            <img src="https://www.pekara-dubravica.hr/web-assets/img/logo.png">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                <%
                    HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute("cart");
                    String username = (String) request.getSession().getAttribute("userUsername");

                    Object role = request.getSession().getAttribute("userRole");
                    boolean isAdmin = role != null ? role == Role.ADMINISTRATOR : false;

                    request.setAttribute("username", username);
                    request.setAttribute("isAdmin", isAdmin);
                    request.setAttribute("cart", cart);
                %>
                <c:if test="${isAdmin == true}">
                    <li class="nav-item"><a class="nav-link" href="admin/products.jsp">Admin</a></li>
                </c:if>
                <c:if test="${cart != null && cart.size() != 0}">
                    <li class="nav-item">
                        <a class="nav-link" href="cart.jsp">Cart
                            <span class="badge badge-danger">${cart.size()}</span>
                        </a>
                    </li>
                </c:if>
                <c:choose>
                    <c:when test="${username == null}">
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link">${username}</a></li>
                        <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
                        <li class="nav-item"><a class="nav-link" href="AuthServlet/logout">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
