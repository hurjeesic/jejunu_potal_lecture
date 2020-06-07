<%--
  Created by IntelliJ IDEA.
  User: jimen
  Date: 2020-06-06
  Time: 오후 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>File Upload!!!</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit">
</form>
${url}<br>
<img src="${url}">
</body>
</html>
