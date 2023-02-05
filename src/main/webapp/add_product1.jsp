<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 09.09.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div>
<h4>
    <%
        Object error = request.getAttribute("error");
        if (error != null) {
            String massageToUser = String.valueOf(error).toLowerCase(Locale.ROOT);
            response.getWriter().write(massageToUser);
        }
    %>
</h4>
</div>

    <a href="/mainMenuServlet"> Назад </a> <br>
<br>
<center>
    Page add product:
    <form action="/addProducts1" method="post">
        Название товара <input name="name" type="text"> <br>
        Цена <input name="price" type="text"> <br>
        Описание <input name="description" type="text"> <br>
        <button type="submit"> Register</button>
    </form>
</center>

</body>
</html>
