<%--
  Created by IntelliJ IDEA.
  User: jimen
  Date: 2020-05-22
  Time: 오후 6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="kr.ac.jejunu.user.UserDao" %>
<%@ page import="kr.ac.jejunu.user.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    User user = userDao.get(91);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Hello <%=user.getName()%>!!!
</h1>
</body>
</html>
