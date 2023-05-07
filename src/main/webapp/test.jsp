<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 07.05.2023
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello world</title>
</head>
<body>

<%
    out.println("<p>hello88</p>");
%>

    <% String s = "путин хуйло";

    out.println();


        PrintWriter pw = response.getWriter();
        pw.write(s);
        pw.write(new java.util.Date().toString());

        int a = 5;
        int b = a + 67;
        System.out.println(b);
        pw.flush();
        pw.close();

        PrintWriter pw2 = response.getWriter();
        pw2.write(new java.util.Date().toString());
        pw2.flush();
        pw2.close();

        PrintWriter pw3 = response.getWriter();

        pw3.flush();
        pw3.close();



    %>

</body>
</html>
