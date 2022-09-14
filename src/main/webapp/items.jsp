<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 11.09.2022
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<a href="/main.jsp"> Назад </a> <br>
<a href="/addItem.jsp"> Регистрация нового товара </a> <br>
<!--tr-строка  td-ячейка -->
<table>
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<h2> Список товара: </h2>");
        printWriter.write("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>Название товара</th>\n" +
                "        <th>Описание</th>\n" +
                "        <th>Цена</th>\n" +
                "    </tr>");

        List<Item> allItems = (List<Item>) request.getAttribute("allItems");
        for (Item item : allItems) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + item.getName() + "</td>" );
            printWriter.write("<td>" + item.getDescription()+ "</td>" );
            printWriter.write("<td>" + item.getPrice() + "</td>" );
            printWriter.write("</tr>");
        }

        printWriter.write("</center>");
    %>
</table>

</body>
</html>
