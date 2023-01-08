<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <link rel="stylesheet" href="css/style.css">
    <title>index.jsp</title>
</head>

<body>


<div style="text-align: center;">
    <h4>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                response.getWriter().write(error);
            }
        %>
    </h4>

    <h2>Форма регистрации</h2>
    <form action="/register" method="post">
        Введите name: <input name="name" type="text"> <br>
        Введите email: <input name="email" type="text"> <br>
        Введите password: <input name="password" type="password"> <br>
        Повторите password: <input name="password" type="password"> <br>
        <button type="submit">Enter</button>
        <br>
    </form>

<%--    <br>--%>
<%--    <p>--%>
<%--        <button type="submit"> <a href="registration.jsp">Зарегистрироваться</a></button>--%>
<%--    </p>--%>


</div>
</body>
</html>
