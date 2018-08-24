<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 18716
  Date: 2018/8/14
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="java.util.*" import="model.Info" %>
<%
    List<Info> Infos = (List<Info>)request.getAttribute("infos");
%>
<html>
<head>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/information.css">
    <title>Title</title>
</head>
<body>

        <div class="modal " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            修改信息
                        </h4>
                    </div>
                    <div class="modal-body">
                        姓名<input id="na" type="text" maxlength="5" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')">
                    </div>

                    <div class="modal-body">
                        年龄<input id="ag" type="text" maxlength="2" onkeyup="value=this.value.replace(/\D+/g,'')">
                    </div>

                    <div class="modal-body">
                        性别<input id="se" type="text" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"><span class="tip" id="setip"></span>
                    </div>

                    <div class="modal-body">
                        成绩<input id="gr" maxlength="3" onkeyup="value=this.value.replace(/\D+/g,'')" type="text"><span class="tip" id="grtip"></span>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button id="chan" type="button" class="btn btn-primary" onclick="change1()">
                            提交更改
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <div class="modal " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel1">
                            新建学生
                        </h4>
                    </div>
                    <div class="modal-body">
                        姓名<input id="na1" maxlength="5" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" type="text"><span class="tip" id="na1tip"></span>
                    </div>

                    <div class="modal-body">
                        年龄<input id="ag1" type="text" maxlength="2" onkeyup="value=this.value.replace(/\D+/g,'')"><span class="tip " id="ag1tip" ></span>
                    </div>

                    <div class="modal-body">
                        性别<input id="se1" type="text" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"><span class="tip" id="se1tip"></span>
                    </div>

                    <div class="modal-body">
                        成绩<input id="gr1" maxlength="3" onkeyup="value=this.value.replace(/\D+/g,'')" type="text"><span class="tip" id="gr1tip"></span>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button id="chan1" type="button" class="btn btn-primary" onclick="add()">
                            新增
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
<div id="all">

    <div id="inf">
        <div id="tit"><strong>学生信息</strong></div>
        <table id="tableID" class="table table-striped">
            <thead style="...">
            <tr>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>成绩</th>
                <th>
                    <%--<a href="jsp/add.jsp">--%>
                    <button type="button" value="" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1">新增</button>
                    <%--</a>--%>
                </th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0;i < Infos.size();i++){
            %>
            <tr>
                <td><%=Infos.get(i).getName()%></td>
                <td><%=Infos.get(i).getAge()%></td>
                <td><%=Infos.get(i).getSex()%></td>
                <td><%=Infos.get(i).getGrade()%></td>
                <td id="select">
                    <button type="button" id="change" class="btn btn-primary btn-lg" onclick="delet(this ,<%=Infos.get(i).getID()%>)" >删除</button>
                    <%--<a href="jsp/change.jsp?number=<%=Infos.get(i).getID()%>&name=<%=Infos.get(i).getName()%>&age=<%=Infos.get(i).getAge()%>&sex=<%=Infos.get(i).getSex()%>">--%>
                    <input type="button" value="修改" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
                           onclick="change(<%=Infos.get(i).getID()%>,'<%=Infos.get(i).getName()%>',<%=Infos.get(i).getAge()%>,'<%=Infos.get(i).getSex()%>',<%=Infos.get(i).getGrade()%>)">
                    <%--</a>--%>
                </td>
                <td>
                    <%--<a href="/Servlet3?number=<%=Infos.get(i).getID()%>">--%>
                    <%--<input type="submit" value="删除">--%>
                    <%--</a>--%>
                </td>
            </tr>

            <%}%>
            </tbody>
        </table>
    </div>

</div>

        <script src="../js/jquery-3.3.1.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script language=javascript>
            var is=true;
            var IDs;
            function add() {
                if(is==true){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/add",
                        dataType:"json",
                        data:{"name":$("#na1").val(),"age":$("#ag1").val(),"sex":$("#se1").val(),"grade":$("#gr1").val()},
                        type:"POST",
                        success:function (result) {
                            if(result==false){
                                alert("添加失败");
                            }
                            else{
                                $("#myModal").modal('hide');
                                alert("添加成功");
                                window.location.href="/ServletTemp";
                            }
                        }
                    })
                }

            }

            function change1() {
                if(is==true){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/sss",
                        dataType:"json",
                        data:{"name":$("#na").val(),"age":$("#ag").val(),"sex":$("#se").val(),"ID":IDs,"grade":$("#gr").val()},
                        type:"POST",
                        success:function (result) {
                            if(result==false){
                                alert("修改失败");
                            }
                            else{
                                $("#myModal").modal('hide');
                                alert("修改成功");
                                window.location.href="/ServletTemp";
                            }
                        }
                    })
                }else {

                }



            }

            function delet(obj,ID) {


                $.ajax({
                    url:"<%=request.getContextPath()%>/www",
                    dataType:"json",
                    data:{"ID":ID},
                    type:"POST",
                    success:function (result) {
                        alert(result);
                        if(result==false){
                            alert("删除失败");
                        }
                        else{
                            alert("删除成功");
                            var tr=obj.parentNode.parentNode;
                            tr.parentNode.removeChild(tr);
                        }
                    }
                })
            }


            $("#se").bind("input propertychange",function () {
                if($("#se").val()=="男"||$("#se").val()=="女"){
                    is=true;
                    $("#setip").text("");
                }else {
                    is=false;
                    $("#setip").text("请输入男或女");
                }

            })



            $("#gr1").bind("input propertychange",function () {
                if($("#gr1").val()<=100){
                    id=true;
                    $("#gr1tip").text("");
                }else {
                    is=false;
                    $("#gr1tip").text("请输入正确的成绩");
                }

            })

            $("#gr").bind("input propertychange",function () {
                if($("#gr").val()<=100){
                    is=true;
                    $("#grtip").text("");
                }else {
                    is=false;
                    $("#grtip").text("请输入正确的成绩");
                }

            })

            $("#se1").bind("input propertychange",function () {
                if($("#se1").val()=="男"||$("#se1").val()=="女"){
                    is=true;
                    $("#se1tip").text("");
                }else {
                    is=false;
                    $("#se1tip").text("请输入男或女");
                }

            })





            function change(ID, name, age, sex, grade) {
                IDs=ID;
                $("#na").val(name);
                $("#ag").val(age);
                $("#se").val(sex);
                $("#gr").val(grade);

            }
        </script>

</body>
</html>
