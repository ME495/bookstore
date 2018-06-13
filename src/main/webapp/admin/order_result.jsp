<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/13
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <div class="text-center">
        <ul id="pagination" class="pagination pagination4"></ul>
    </div>
    <table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th>订单号</th>
            <th>下单人</th>
            <th>地址</th>
            <th>下单时间</th>
            <th>状态</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <div id="info" class="alert alert-warning hidden">
        没有找到相应的订单！
    </div>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script src="../js/jqPaginator.js"></script>
<script type="application/javascript">
    function show_data(data) {
        $("tbody").empty();
        $("#info").attr("class", "alert alert-warning hidden");
        $.each(data, function (i, value) {
            var status = null;
            if (value.status == "0") status = "未发货";
            else if (value.status == "1") status = "已发货";
            else status = "确认收货";
            var $e = $("<tr></tr>");
            $e.append("<td>" + value.orderId + "</td>");
            $e.append("<td>" + value.userName + "</td>");
            $e.append("<td>" + value.address + "</td>");
            $e.append("<td>" + value.orderTime + "</td>");
            $e.append("<td>" + status + "</td>");
            $e.append("<td>" + value.money + "</td>");
            $("tbody").append($e);
        });
    }

    function query(json) {
        $.post("./order_query.do", json, function (data, status) {
            console.log(data);
            if (status == "success") {
                if (data.status == "fail") {
                    alert("查询失败！");
                    location.href = "./query_order.jsp";
                } else if (data.message.length > 0) {
                    show_data(data.message);
                } else {
                    $("tbody").empty();
                    $("#info").attr("class", "alert alert-warning");
                }
            }
        });
    }

    $(document).ready(function () {
        var status0 = get_url_param("status0");
        var status1 = get_url_param("status1");
        var status2 = get_url_param("status2");
        var user_name = get_url_param("user_name");
        var begin_time = get_url_param("begin_time");
        var end_time = get_url_param("end_time");
        var index = 0;
        var size = 20;
        var json = JSON.parse("{}");
        json["status0"] = status0;
        json["status1"] = status1;
        json["status2"] = status2;
        if (user_name) json["user_name"] = user_name;
        if (begin_time) json["begin_time"] = begin_time;
        if (end_time) json["end_time"] = end_time;
        json["index"] = index;
        json["size"] = size;
        // query(json);

        $("#pagination").jqPaginator({
            totalPages: 100,
            currentPage: 1,

            prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
            onPageChange: function (num) {
                index = (num-1) * size;
                json["index"] = index;
                query(json);
            }
        });
    });
</script>
</html>
