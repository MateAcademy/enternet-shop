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

    <h2>Login</h2>
    <form action="/login" method="post">
        Введите email: <input name="email" type="text"> <br>
        Введите password: <input name="password" type="password"> <br>
        <button type="submit">Enter</button>
        <br>
    </form>


    <br>
    Еще не зарегистрированы?
    <br>
    <p>
        <button type="submit"> <a href="/register">Зарегистрироваться</a></button>
    </p>


</div>
</body>
</html>
