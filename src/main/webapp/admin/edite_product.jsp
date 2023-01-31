<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 07.11.2022
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edite user</title>
</head>
<body>

<table border='2' width="80%">
    <h2> Отредактируйте данные:</h2>
    <br>
    <form action="/admin/editProductData" method="post">
        <p> name_product <input name="name_product" type="text" value="<%=request.getAttribute("name_product")%>"></p>
        <p> price_product <input name="price_product" type="text" value="<%=request.getAttribute("price_product")%>"></p>
        <p> description_product <input name="description_product" type="text" value="<%=request.getAttribute("description_product")%>"></p>
        <input type="submit" value="Change data">
    </form>
</table>
</body>
</html>