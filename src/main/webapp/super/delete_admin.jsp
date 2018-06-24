<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/17
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除管理员</title>
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>管理员账号</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
<script type="application/javascript">
    function delete_admin(e) {
        var admin_name = e.attr("title");
        $.post("./delete_admin.do", {"admin_name": admin_name}, function (data, status) {
            if (status == "success" && data.status == "success") {
                alert("删除成功！");
                location.reload();
            } else {
                alert("删除失败！");
            }
        });
    }
    $(document).ready(function () {
        $.post("./admin_list.do", function (data, status) {
            if (status == "success" && data.status == "success") {
                $.each(data.message, function (i, value) {
                    var $e = $("<tr></tr>");
                    var $b = $("<button>删除</button>");
                    $b.attr("class", "btn btn-primary");
                    $b.attr("title", value.adminName);
                    $b.attr("onclick", "delete_admin($(this))");
                    $e.append("<td>" + value.adminName + "</td>");
                    $e.append($("<td></td>").append($b));
                    $("tbody").append($e);
                })
            } else {
                alert("发生错误，无法显示管理员！");
            }
        });
    });
</script>
</html>
