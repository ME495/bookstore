<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/17
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建管理员</title>
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="container">
    <div class="page_header text-center" style="margin-bottom: 50px;">
        <h1>输入账号和密码创建管理员</h1>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">账号：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="admin_name">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">密码：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="password">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5 btn btn-primary" data-toggle="modal" data-target="#myModal" id="confirm">
            确定
        </div>
    </div>
</div>
</body>
<script type="application/javascript">
    $(document).ready(function () {
        $("#confirm").click(function () {
            var admin_name = $("#admin_name").val();
            var password = $("#password").val();
            var post_data = {
                "admin_name": admin_name,
                "password": password
            }
            $.post("./add_admin.do", post_data, function (data, status) {
                if (status == "success") {
                    if (data.status == "success") {
                        alert("添加成功！");
                    } else {
                        alert(data.message);
                    }
                } else {
                    alert("添加失败，网络错误！")
                }
            });
        });
    });
</script>
</html>
