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
    <title>Edite product</title>
    <meta charset="utf-8">
</head>
<body>

<p action="/edite_product" method="post">
    First name <input type="text" name="firstName"/>
    Last name <input type="text" name="lastName"/>
    <input type="checkbox" name="agree" value="yes"> Согласен с политикой обработки данных </input>

    <selec  multiple name="profession" size ="2">
        <option> admin</option>
        <option> user2</option>
        <option> user3</option>
        <option> user4</option>
    </selec>

    <p><input type="radio" name="book" value="1"> 11</p>
    <p><input type="radio" name="book" value="2"> 22</p>
<p><input type="radio" name="book" value="3" checked> 33 </p>
    <input type="submit" value="выбрать">
</form>

</body>
</html>
