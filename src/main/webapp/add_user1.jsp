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
<center>
    <h4>
        <%    String error = (String) request.getAttribute("error");
            if (error!=null) {
                response.getWriter().write(error);
            }
        %>
    </h4>
    <a href="/mainMenuServlet"> Назад </a>
    <br>
    <form action="/register1" method="post">
        Name <input name="name" type="text"> <br>
        Email <input name="email" type="email"> <br>
        password <input name="password" type="password"> <br>
        repeat password <input name="repeatPassword" type="password"> <br>
        <button type="submit"> Register </button> <br>
    </form>
</center>

</body>
</html>
