<%@ page import="model.User" %>
<%@ page import="util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 18.06.2020
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% User user = (User) request.getAttribute("userDetail");%>
Անուն Ազգանուն։<%=user.getName()%> &nbsp;&nbsp;<%=user.getSurname()%> <br>
Սեռ։ <%=user.getGender()%>  <br>
email։ <%=user.getEmail()%>  <br>
createDate։ <%=DateUtil.convertDateToString(user.getCreationDate()) %>  <br>
lesson։ <%=user.getLesson().getName() %>  <br>
lesson_Duration։ <%=user.getLesson().getDuration() %> ամիս <br>
lesson_Price։ <%=user.getLesson().getPrice() %> $ <br>
<a href="/userHome"> Բոլոր Օգտվողները</a>

</body>
</html>
