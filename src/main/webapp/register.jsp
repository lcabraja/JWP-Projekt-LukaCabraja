
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div align="center">
    <h1>Dubravica</h1>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form action="AuthServlet/register" method="post">
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
        <input type="submit" value="Register" />
    </form>
</div>
</body>
</html>
