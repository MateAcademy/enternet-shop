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

<table border='2' width="80%">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>description</th>
  </tr>
  <c:forEach var="product" items="${productList}">
    <tr>
      <td><c:out value="${product.id_product}"/></td>
      <td><c:out value="${product.name}"/></td>
      <td><c:out value="${product.price}"/></td>
      <td><c:out value="${product.description}"/></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
