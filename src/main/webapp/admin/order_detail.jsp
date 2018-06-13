<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/13
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <div class="page_header text-center">
        <h1>订单详情</h1>
    </div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>isbn</th>
                <th>书名</th>
                <th>新旧程度</th>
                <th>作者</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        var order_id = getUrlParam("order_id");
        var json = JSON.parse("{}");
        json["order_id"] = order_id;
        $.post("./order_detail.do", json, function (data, status) {
            console.log(data);
            if (status == "success") {
                console.log(data);
                if (data.status == "success") {
                    $.each(data.message, function (i, value) {
                        var degree = null;
                        if (value.degree == 0) degree = "九成新";
                        else if (value.degree == 1) degree = "七成新";
                        else if (value.degree == 2) degree = "五成新";
                        else degree = "三成新";
                        var $e = $("<tr></tr>");
                        $e.append("<td>" + value.isbn + "</a></td>");
                        $e.append("<td>" + value.title + "</td>");
                        $e.append("<td>" + degree + "</td>");
                        $e.append("<td>" + value.author + "</td>");
                        $e.append("<td>" + value.unitPrice + "</td>");
                        $e.append("<td>" + value.num + "</td>");
                        $("tbody").append($e);
                    });
                }
            }
        });
    });
</script>
</html>
