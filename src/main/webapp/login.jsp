<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    Object val = request.getAttribute("errorMessage");
    request.setAttribute("errorMessage", val == null ? "" : val);
%>
<div align="center">
    <h1>Login</h1>
    <c:if test="${errorMessage != \"\" }">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <form action="AuthServlet/login" method="post">
        <table style="with: 80%">
            <tr>
                <td>Username: </td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Login" />
    </form>
</div>
</body>
</html>
