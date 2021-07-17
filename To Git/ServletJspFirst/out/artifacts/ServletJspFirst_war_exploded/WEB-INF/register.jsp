<%@ page import="java.util.List" %>
<%@ page import="model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 12.06.2020
  Time: 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>
<form action="/register" method="post">

    name:<input type="text" name="name"> <br>
    surname:<input type="text" name="surname"> <br>
    email:<input type="text" name="email"> <br>
    password:<input type="password" name="password"> <br>
    gender:MALE <input type="radio" value="MALE" name="gender"> FEMALE
    <input type="radio" value="FEMALE" name="gender"> <br>
    <select name="lessonId">
        <%
            for (Lesson lesson : lessons) { %>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%></option>
        <% } %>
    </select><br>
    <input type="submit" value="Register">
    <a href="../login.jsp">Login</a>
</form>
</body>
</html>
