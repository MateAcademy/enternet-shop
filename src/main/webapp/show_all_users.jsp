<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<a href="/mainMenuServlet"> Назад </a> <br>
<a href="/register"> Регистрация нового пользователя </a> <br>
<!--tr-строка  td-ячейка -->
<%--<table>--%>
<%--    <%--%>
<%--        PrintWriter printWriter = response.getWriter();--%>
<%--        printWriter.write("<center>");--%>
<%--        printWriter.write("<h2> Список пользователей </h2>");--%>
<%--        printWriter.write("<table border=\"1\">\n" +--%>
<%--                "    <tr>\n" +--%>
<%--                "        <th>Email</th>\n" +--%>
<%--                "        <th>Password</th>\n" +--%>
<%--                "        <th>Edit</th>\n" +--%>
<%--                "        <th>Delete</th>\n" +--%>
<%--                "    </tr>");--%>

<%--        List<User> allUsers = (List<User>) request.getAttribute("allUsers");--%>
<%--        for (User user : allUsers) {--%>
<%--            int t = 555;--%>
<%--            printWriter.write("<tr>");--%>
<%--            printWriter.write("<td>" + user.getEmail() + "</td>" );--%>
<%--            printWriter.write("<td>" + user.getPassword() + "</td>" );--%>
<%--            printWriter.write("<td>" + "<a href=\"/admin/delete/?t=" + t +"> delete </a>" + "</td>" );--%>
<%--            printWriter.write("</tr>");--%>
<%--        }--%>

<%--        printWriter.write("</center>");--%>
<%--    %>--%>
<%--</table>--%>

<table border='2' width="80%">
    <tr>
        <th>Email</th>
        <th>Password</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><a href='/admin/edit?id_user=${user.id_user}'>edit</a></td>
            <td><a href='/admin/deleteUser?id_user=${user.id_user}'>delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

<!-- как менять jsp без перезагрузки:  "hotswap" и настройки "tomcat"