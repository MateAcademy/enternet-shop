<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>index</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<center>
    <h4>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                response.getWriter().write(error);
            }
        %>
    </h4>

    Вход на сайт:
    <form action="/login" method="post">
        Введите email: <input name="email" type="text"> <br>
        Введите password: <input name="password" type="password"> <br>
        <button type="submit"> Enter</button> <br>
    </form>

</center>
</body>
</html>
