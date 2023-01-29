<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %><%--
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

<a href="/getAllProducts"> Назад </a> <br>
<center>
    Page add items:
    <form action="/addProducts" method="post">
        Название товара <input name="name" type="text"> <br>
        Цена <input name="price" type="text"> <br>
        Описание <input name="description" type="text"> <br>
        <button type="submit"> Register</button>
        <br>
    </form>
</center>

<%--<%--%>
<%--    PrintWriter printWriter = response.getWriter();--%>
<%--    printWriter.write("<table border=\"1\">\n" +--%>
<%--            "<tr>\n" +--%>
<%--            "<th>Name</th>\n" +--%>
<%--            "<th>Description</th>\n" +--%>
<%--            "<th>Price</th>\n" +--%>
<%--            "</tr>");--%>
<%--    List<Product> productList = (List<Product>) request.getAttribute("productList");--%>
<%--    for(Product product : productList) {--%>
<%--        printWriter.write("<tr>");--%>
<%--        printWriter.write("<td>" + product.getName() + "</td>");--%>
<%--        printWriter.write("<td>" + product.getDescription() + "</td>");--%>
<%--        printWriter.write("<td>" + product.getPrice() + "</td>");--%>
<%--        printWriter.write("</tr>");--%>
<%--    }--%>
<%--    printWriter.write("</table>");--%>
<%--%>--%>

</body>
</html>
