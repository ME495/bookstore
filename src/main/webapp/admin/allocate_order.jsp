<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/13
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分配订单</title>
</head>
<body>
<%@include file="navbar.jspf"%>
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
            <th>金额</th>
        </tr>
        </thead>
        <tbody id="order_list">
        </tbody>
    </table>
    <div id="info" class="alert alert-warning hidden">
        此页没有为分配的订单！
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">订单详情</h4>
                </div>
                <div class="modal-body">
                    <label>订单号：</label>
                    <label id="show_order_id"></label>
                    <br>
                    <label>下单人：</label>
                    <label id="show_name"></label>
                    <br>
                    <label>手机号：</label>
                    <label id="show_phone"></label>
                    <br>
                    <label>地址：</label>
                    <label id="show_address"></label>
                    <br>
                    <label>总金额：</label>
                    <label id="show_money"></label>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>书名</th>
                            <th>新旧程度</th>
                            <th>作者</th>
                            <th>单价</th>
                            <th>数量</th>
                        </tr>
                        </thead>
                        <tbody id="order_detail">
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" id="closs" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="print">打印</button>
                    <button type="button" class="btn btn-primary" id="confirm">确定分配</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script src="../js/jqPaginator.js"></script>
<script src="../js/print.js"></script>
<script type="application/javascript">
    var element;
    function show_data(data) {
        $("#order_list").empty();
        $("#info").attr("class", "alert alert-warning hidden");
        $.each(data, function (i, value) {
            var $e = $("<tr></tr>");
            $e.append("<td>" + value.orderId + "</td>");
            $e.append("<td>" + value.userName + "</td>");
            $e.append("<td>" + value.address + "</td>");
            $e.append("<td>" + value.orderTime + "</td>");
            $e.append("<td>" + value.money + "</td>");
            var $span = $("<span class='allocate btn btn-primary' data-toggle='modal' data-target='#myModal'>分配订单</span>");
            $span.attr("order_id", value.orderId);
            $span.attr("name", value.realName);
            $span.attr("address", value.address);
            $span.attr("phone", value.phone);
            $span.attr("money", value.money);
            $e.append($("<td></td>").append($span));
            $("#order_list").append($e);
        });
        $(".allocate").click(function () {
            element = $(this);
            var order_id = $(this).attr("order_id");
            $("#show_order_id").html($(this).attr("order_id"));
            $("#show_name").html($(this).attr("name"));
            $("#show_phone").html($(this).attr("phone"));
            $("#show_address").html($(this).attr("address"));
            $("#show_money").html($(this).attr("money"));
            console.log(order_id);
            $.post("./order_detail.do", {"order_id": order_id}, function (data, status) {
                if (data.status == "success" && data.status == "success") {
                    $("#order_detail").empty();
                    $.each(data.message, function (i, value) {
                        var degree = null;
                        if (value.degree == 0) degree = "九成新";
                        else if (value.degree == 1) degree = "七成新";
                        else if (value.degree == 2) degree = "五成新";
                        else degree = "三成新";
                        var $e = $("<tr></tr>");
                        $e.append("<td>" + value.title + "</td>");
                        $e.append("<td>" + degree + "</td>");
                        $e.append("<td>" + value.author + "</td>");
                        $e.append("<td>" + value.unitPrice + "</td>");
                        $e.append("<td>" + value.num + "</td>");
                        $("#order_detail").append($e);
                    });
                } else {
                    alert("分配失败！");
                    $("#closs").click();
                }
            });
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
        var index = 0;
        var size = 20;
        var json = JSON.parse("{}");
        json["status0"] = "true";
        json["status1"] = "false";
        json["status2"] = "false";
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
        
        $("#print").click(function () {
            var order_id = $("#show_order_id").html();
            location.href = "./print.do?order_id=" + order_id;
        });

        $("#confirm").click(function () {
            $.post("./allocate_order.do", {"order_id": element.attr("order_id")}, function (data, status) {
                if (status == "success") {
                    if (data.status == "success") {
                        element.parent().html("<span class='btn btn-default' disabled='disabled'>分配成功</span>");
                        $("#closs").click();
                    } else {
                        alert("分配失败！");
                    }
                }
            });
        });
    });

    
</script>
</html>
