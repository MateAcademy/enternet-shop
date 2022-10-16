<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 07.09.2022
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<a href="/main_menu.jsp"> Назад </a> <br>
<a href="/add_user.jsp"> Регистрация нового пользователя </a> <br>
<!--tr-строка  td-ячейка -->
<table>
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<h2> Список пользователей </h2>");
        printWriter.write("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>Email</th>\n" +
                "        <th>Password</th>\n" +
                "    </tr>");

        List<User> allUsers = (List<User>) request.getAttribute("allUsers");
        for (User user : allUsers) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + user.getEmail() + "</td>" );
            printWriter.write("<td>" + user.getPassword() + "</td>" );
            printWriter.write("</tr>");
        }

        printWriter.write("</center>");
    %>
</table>

</body>
</html>

<!-- как менять jsp без перезагрузки:  "hotswap" и настройки "tomcat"