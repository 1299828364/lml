<%@ page import="java.sql.Statement" %>
<%@ page import="static util.DBUtil.getConnection" %><%--
  Created by IntelliJ IDEA.
  User: 18716
  Date: 2018/8/15
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title1111</title>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/enroll">
    <div>姓名<input type="text" name="user" id="names"></div>
    <div>密码<input type="text" name="password" id="password"></div>
    <input type="submit" value="确定">
</form>



</body>
</html>