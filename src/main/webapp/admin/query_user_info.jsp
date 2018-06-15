<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/14
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户信息</title>
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="container">
    <div class="page_header text-center" style="margin-bottom: 50px;">
        <h1>请输入用户账号</h1>
    </div>
    <div class="form-horizontal col-xs-offset-4" style="margin-bottom: 50px;">
        <div class="form-group">
            <label class="col-xs-2 control-label">账号：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="user_name">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5 btn btn-primary" id="submit">
            确定
        </div>
    </div>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $("#submit").click(function () {
            var user_name = $("#user_name").val();
            var url = "./user_info_detail.jsp";
            url = addUrlParam(url, "user_name", user_name);
            location.href = url;
        });
    });
</script>
</html>
