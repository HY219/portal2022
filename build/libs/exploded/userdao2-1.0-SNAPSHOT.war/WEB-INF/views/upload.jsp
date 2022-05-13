<%--
  Created by IntelliJ IDEA.
  User: jhy_0
  Date: 2022-05-13
  Time: 오후 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<form action = "/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>
<img src = "${url}" />
</body>
</html>
