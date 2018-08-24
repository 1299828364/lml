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
    <title>Title</title>
</head>
<body>
<%
    //获得number
    String str=request.getParameter("number");


    String name=request.getParameter("name");
    byte[] data=name.getBytes("ISO-8859-1");
    name=new String(data,"utf-8");

    String age=request.getParameter("age");
    byte[] data1=age.getBytes("ISO-8859-1");
    age=new String(data1,"utf-8");


    String sex=request.getParameter("sex");
    byte[] data2=sex.getBytes("ISO-8859-1");
    sex=new String(data2,"utf-8");

    int number=Integer.parseInt(str);
%>
            <%--<form method="post" action="<%=request.getContextPath()%>/sss">--%>
                <%--<div>姓名<input type="text" name="names" id="names" value="<%=name%>"></div>--%>
                <%--<div>年龄<input type="text" name="ages" id="ages" value="<%=age%>"></div>--%>
                <%--<div>性别<input type="text" name="sexs" id="sexs" value="<%=sex%>"></div>--%>
                <%--<div><input type="text" name="IDs" value="<%=number%>" style="display: none"></div>--%>
                <%--<div><input type="submit" name="submit" value="修改asd"></div>--%>
            <%--</form>--%>

</body>
</html>
