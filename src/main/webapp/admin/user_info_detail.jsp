<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/14
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="container">
    <div class="page_header text-center" style="margin-bottom: 50px;">
        <h1>用户信息</h1>
    </div>
    <table class="table">
        <tbody>
        <tr>
            <td>账号：</td>
            <td id="user_name"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <div class="form-inline">
                    <input type="text" class="form-control" id="password" placeholder="输入修改后的密码">
                    <span class="btn btn-primary" id="modify">修改</span>
                </div>
            </td>
        </tr>
        <tr>
            <td>手机号：</td>
            <td id="phone"></td>
        </tr>
        <tr>
            <td>真实姓名：</td>
            <td id="real_name"></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td id="address"></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        var user_name = getUrlParam("user_name");
        $.post("./get_user.do", {"user_name": user_name}, function (data, status) {
            if (status == "success" && data.status == "success") {
                var json = data.message;
                $("#user_name").html(json.userName);
                $("#phone").html(json.phone);
                $("#real_name").html(json.realName);
                $("#address").html(json.address);
            } else {
                alert("查询失败！");
                location.href = "./query_user_info.jsp";
            }
        });
        $("#modify").click(function () {
            var user_name = $("#user_name").html();
            var password = $("#password").val();
            $.post("./pwdModify.do", {"user_name": user_name, "password": password}, function (data, status) {
                if (status == "success" && data.status == "success") {
                    alert("密码修改成功！");
                    $("#password").val("");
                } else {
                    alert("密码修改失败！");
                }
            });
        });
    });
</script>
</html>
