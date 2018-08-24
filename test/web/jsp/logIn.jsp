<%--
  Created by IntelliJ IDEA.
  User: 18716
  Date: 2018/8/14
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/logIn.css">
    <title>登陆</title>
    <script src="../js/jquery-3.3.1.min.js"></script>



</head>
<body>
<%
    boolean bool=false;
    //获取客户端Cookie数组
    Cookie[] cookies = request.getCookies();

//判断客户端是否存在Cookie对象
    if (cookies != null) {
//预定义保存用户名和密码的变量

        String userName = "";
        String password = "";
//遍历Cookie数组

        for (int i = 0; i < cookies.length; i++) {
//取得每一个Cookie对象
            Cookie cookie = cookies[i];
//判断Cookie的名称是否等于"accpLoginName"
            if ("accpLoginName".equals(cookie.getName())) {
                userName = cookie.getValue();
            }

//判断Cookie的名称是否等于"accpLoginPass"
            if ("accpLoginPass".equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
//判断用户名和密码是否合法
        if (cookies.length>1){
            bool=true;
        }



    }
//判断对客户端的Cookie的操作是否成功，成功则显示登录成功后的页面，
//否则，重定向到登录页面
    if(bool)
        response.sendRedirect("/ServletTemp");

    else{


    }


%>


    <div id="all">
        <div id="log">
            <span id="img" ><strong>用户登陆</strong></span>
            <div class="lo">&nbsp&nbsp&nbsp用户名：<input type="text" onkeyup="value=this.value.replace(/\D+/g,'')" name="name" class="in"   maxlength="20" id="name"></div>
            <div  id="chkName" class="chk"></div>
            <div class="lo">&nbsp&nbsp&nbsp&nbsp密码：&nbsp&nbsp&nbsp  <input class="in" type="password"  name="password" id="password"></div>
            <div id="chkPassword" class="chk "></div>
            <div class="lo click"><input type="submit" class="btn btn-primary btn-lg" id="submit" value="登陆" onclick="check()">&nbsp&nbsp&nbsp
                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">注册</button></div>
            <%--<div><a href="jsp/enroll.jsp"><button type="button">注册</button></a></div>--%>
            <div class="lo click"></div>
            <div><input type="checkbox" id="checkbox" >自动登陆</div>
        </div>
    </div>




    <div class="modal " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content kuang">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                        新建学生
                    </h4>
                </div>
                <div class="modal-body en">
                    账号<input id="ID" type="text" onkeyup="value=this.value.replace(/\D+/g,'')" maxlength="20"><span id="chkEnrollName" class="chk"></span>
                </div>

                <div class="modal-body en">
                    密码<input id="passw" type="text"><span id="chkEnrollPassword" class="chk"></span>
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cle()">关闭
                    </button>
                    <button id="chan1" type="button" class="btn btn-primary" onclick="enroll()">
                        新增
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <script type="text/javascript">

        $("#name").bind("click",function (){
            $("#chkName").text("");
        })

        $("#password").bind("click",function (){
            $("#chkPassword").text("");
        })

        $("#name").bind("blur",function (){
            if ($("#name").val()==""){
                $("#chkName").text("账号不能为空");

            }
        })


        $("#password").bind("blur",function (){
            if ($("#password").val()==""&&$("#name").val()!=""){
                $("#chkPassword").text("密码不能为空");
                alert($("#name").val());
            }
        })

        function cle() {
            $("#passw").val("");
            $("#ID").val("");
        }

        function check(){

            var n=$("#name").val();
            var p=$("#password").val();
            alert(n+" "+p);
           $.ajax({
               url:"/infoma",
               dataType:"json",
               data:{"name":n,"password":p,"check":$("#checkbox").is(":checked")},
               type:"POST",
               success:function (result) {
                   if(result==false){
                       alert("您输入的账号或密码错误");
                       $("#password").val("");
                   }
                   else{
                       window.location.href="/ServletTemp";
                   }
               }
           })
        }

        $("#ID").bind("click",function (){
            $("#chkEnrollName").text("");
        })

        $("#passw").bind("click",function (){
            $("#chkEnrollPassword").text("");
        })

        $("#ID").bind("blur",function (){
            if ($("#ID").val()==""){
                $("#chkEnrollName").text("账号不能为空");

            }
        })

        $("#ID").bind("input propertychange",function(event){
            console.log($("#ID").val());
            if($("#ID").val().length<6){
                $("#chkEnrollName").text("ID不少于6位");
            }else{
                $("#chkEnrollName").text("");
            }

        });
        $("#passw").bind("input propertychange",function(event){
            console.log($("#passw").val());
            if($("#passw").val().length<8){
                $("#chkEnrollPassword").text("密码不少于8位");
            }else{
                $("#chkEnrollPassword").text("");
            }
        })

        $("#passw").bind("blur",function (){
            if ($("#passw").val()==""&&$("#ID").val()!=""){
                $("#chkEnrollPassword").text("密码不能为空");
            }
        })

        function enroll() {
            var password=$("#passw").val();
            var ID=$("#ID").val();
            if(ID==""){
                $("#chkEnrollName").text("账号不能为空");
            }else if(password==""){
                $("#chkEnrollPassword").text("密码不能为空");
            }else if($("#ID").val().length<6) {

            }else if($("#passw").val().length<8){

            }
            else {
                $.ajax({
                    url:"/enroll",
                    dataType:"json",
                    data:{"ID":ID,"password":password},
                    type:"POST",
                    success:function (result) {
                        if(result==false){
                            alert("账户已存在");
                            $("#passw").val("");
                            $("#ID").val("");
                        }
                        else{
                            alert("注册成功");
                            $("#myModal").modal('hide');
                            $("#passw").val("");
                            $("#ID").val("");
                        }
                    }
                })
            }

        }

    </script>
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>
