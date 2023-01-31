<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 11.09.2022
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>All products</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<a href="/mainMenuServlet"> Назад </a> <br>
<a href="/add_product.jsp"> Регистрация нового товара </a> <br>
<!--tr-строка  td-ячейка -->
<%--<table>--%>
<%--    <%--%>
<%--        PrintWriter printWriter = response.getWriter();--%>
<%--        printWriter.write("<center>");--%>
<%--        printWriter.write("<h2> Список товаров: </h2>");--%>
<%--        printWriter.write("<table border=\"1\">\n" +--%>
<%--                "    <tr>\n" +--%>
<%--                "        <th>Название товара</th>\n" +--%>
<%--                "        <th>Описание</th>\n" +--%>
<%--                "        <th>Цена</th>\n" +--%>
<%--                "    </tr>");--%>

<%--        List<Product> allProducts = (List<Product>) request.getAttribute("allProducts");--%>
<%--        for (Product product : allProducts) {--%>
<%--            printWriter.write("<tr>");--%>
<%--            printWriter.write("<td>" + product.getName() + "</td>" );--%>
<%--            printWriter.write("<td>" + product.getDescription()+ "</td>" );--%>
<%--            printWriter.write("<td>" + product.getPrice() + "</td>" );--%>
<%--            printWriter.write("</tr>");--%>
<%--        }--%>

<%--        printWriter.write("</center>");--%>
<%--    %>--%>
<%--</table>--%>

<%--<table>--%>
<%--    <c:forEach var="element" items="${productList}" >--%>
<%--      <tr>--%>
<%--          <td>  <c:out value="${element.id}"/><p></td>--%>
<%--          <td>  <c:out value="${element.name}"/><p></td>--%>

<%--      </tr>>--%>
<%--    </c:forEach>--%>
<%--</table>--%>


<table border='2' width="80%">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>description</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td><c:out value="${product.id_product}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.description}"/></td>
            <td><a href='/admin/editProduct?id_product=${product.id_product}'>edit</a></td>
            <td><a href='/admin/deleteProduct?id_product=${product.id_product}'>delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
