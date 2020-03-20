<%--
  Created by IntelliJ IDEA.
  User: migusdn
  Date: 20. 3. 16.
  Time: 오후 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form name="fileForm" action="/upload" method="post" enctype="multipart/form-data">
    <input multiple="multiple" type="file" name="file" />
    <input type="text" name="src" />
    <input type="submit" value="전송" />
</form>
</body>
</html>
