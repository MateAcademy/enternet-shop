<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 04.09.2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%--<c:set var="test" value="TestVariable"/>--%>
<%--<c:out value="${error}">Fail</c:out>--%>
<a href="/main_menu.jsp"> Назад </a> <br>
<center>
    <h4>
      <%
          String error = (String) request.getAttribute("error");
          if (error!=null) {
              response.getWriter().write(error);
          }
      %> <br>
    </h4>

    <form action="/admin/register" method="post">
        Email <input name="email" type="email"> <br>
        password <input name="password" type="password"> <br>
        repeat password <input name="repeatPassword" type="password"> <br>
        <button type="submit"> Register </button> <br>
    </form>
</center>

</body>
</html>
