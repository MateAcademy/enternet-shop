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

    <div class="login-box">
    Вход на сайт:
    <form action="/login" method="post">
        <div class="user-box">Введите email: <input name="email" type="text"> </div> <br>
        <div class="user-box">Введите password: <input name="password" type="password"> </div><br>
        <button type="submit"> Enter</button> <br>
    </form>
    </div>




</center>
</body>
</html>
