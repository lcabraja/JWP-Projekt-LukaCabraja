<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/admin"/>

<html>
<head>
    <title>New Product</title>
</head>
<body>
<div align="center">
    <h1>New Product</h1>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form action="../admin/servlet/newproduct" method="post">
        <table style="with: 80%">
            <tr>
                <td>id:</td>
                <td><input type="number" name="id"/></td>
            </tr>
            <tr>
                <td>name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
        </table>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
