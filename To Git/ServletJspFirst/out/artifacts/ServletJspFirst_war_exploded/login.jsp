<%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 11.06.2020
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%--<% if (request.getAttribute("message") != null) { %>--%>

<%--<p style="color: red"><%= request.getAttribute("message")%>--%>
<%--</p>--%>

<%--<% } %>--%>

<form action="/login" method="post">
    email:<input type="text" name="email"> <br>
    password:<input type="password" name="password"><br>
    <input type="submit" value="login">
    <a href="register">Register</a>

</form>
</body>
</html>
