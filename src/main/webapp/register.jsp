<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 04.09.2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>register</title>
</head>
<body>

<%--<c:set var="test" value="TestVariable"/>--%>
<%--<c:out value="${error}">Fail</c:out>--%>

<center>
    <h4>
      <%
          String error = (String) request.getAttribute("error");
          if (error!=null) {
              response.getWriter().write(error);
          }
      %>
    </h4>

    <form action="/register" method="post">
        Email <input name="email" type="email"> <br>
        password <input name="password" type="password"> <br>
        repeat password <input name="repeatPassword" type="password"> <br>
        <button type="submit"> Register </button> <br>
    </form>
</center>

</body>
</html>
