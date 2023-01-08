<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<a href="/main_menu.jsp"> Назад </a> <br>

<table border='2' width="80%">
    <tr>
        <th>Email</th>
        <th>Password</th>

    </tr>
    <c:forEach var="user" items="${changeUser}">
        <tr>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.password}"/></td>

        </tr>
    </c:forEach>
</table>


<form action = "hello">
    Email <input type = "text" name = "text">
    Password <input type = "text" name = "password">
</form>

</body>
</html>