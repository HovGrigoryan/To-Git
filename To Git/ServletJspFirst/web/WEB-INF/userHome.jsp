<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 12.06.2020
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("users");

    if (user != null) {
%>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>info</td>
    </tr>

        <% for (User user1 : users) { %>
        <tr>
            <td><%=user1.getId()%>
            </td>
            <td><%=user1.getName()%>
            </td>
            <td><%=user1.getSurname()%>
            </td>
            <td><%=user1.getEmail()%>
            </td>
            <td><a href="/userDetail?userId=<%=user.getId()%>">User Detail</a></td>

        </tr>

    <% } %>

</table>
<% } %>
All Users
Welcome <%= user.getName() %> <%= user.getSurname() %> <br> <a href="/logout">Logout</a>

</body>
</html>
