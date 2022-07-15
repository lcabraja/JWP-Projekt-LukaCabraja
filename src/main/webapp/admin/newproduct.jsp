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
                <td>title:</td>
                <td><input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>description:</td>
                <td><input type="text" name="description"/></td>
            </tr>
            <tr>
                <td>price:</td>
                <td><input type="number" name="price"/></td>
            </tr>
            <tr>
                <td>category:</td>
                <td>
                    <select name="category">
                        <option selected value="-1">Choose...</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.getId()}"/>
                            ${category.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>imageHref:</td>
                <td><input type="url" name="imageHref"/></td>
            </tr>
        </table>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
