<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 07.11.2022
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edite user</title>
</head>
<body>
<table border='2' width="80%">
    <h2> Отредактируйте данные:</h2>
    <br>
    <form action="/admin/editUserData" method="post">
        <p> Name <input name="name" type="text" value="<%=request.getAttribute("name")%>"></p>
        <p> Email <input name="email" type="email" value="<%=request.getAttribute("email")%>"></p>
        <p> Password <input name="password" type="password" value="<%=request.getAttribute("password")%>"></p>
        <p> Role <input name="role" type="text" value="<%=request.getAttribute("role")%>"></p>
        <p> Available<input name="available" type="text" value="<%=request.getAttribute("available")%>"></p>
        <input type="submit" value="Change data">
    </form>

    <br>
    <br>

    <form action="/admin/editUserData" method="post">
        <p> Name <input name="name" type="text" value="<c:out value="${user.name}"/>" /> </p>
        <p> Email <input name="email" type="email" value="<c:out value="${user.email}"/>" /></p>
        <p> Password <input name="password" type="password" value="<c:out value="${user.password}"/>" /></p>
        <p> Role <input name="role" type="text" value="<c:out value="${user.role}"/>" /></p>
        <p> Available<input name="available" type="text" value="<c:out value="${user.available}"/>" /></p>
        <input type="submit" value="Change data">
    </form>
</table>
</body>
</html>
