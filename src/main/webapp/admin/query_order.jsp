<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/13
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单查询</title>
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">订单状态：</label>
            <div class="checkbox-inline col-xs-offset-1">
                <input type="checkbox" id="status0">未发货
            </div>
            <div class="checkbox-inline">
                <input type="checkbox" id="status1">已发货
            </div>
            <div class="checkbox-inline">
                <input type="checkbox" id="status2">确认收货
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">下单用户：</label>
            <div class="col-xs-6">
                <input class="form-control" type="text" id="user_name">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">开始时间：</label>
            <div class="col-xs-6">
                <input class="form-control" type="text" id="begin_time">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">结束时间：</label>
            <div class="col-xs-6">
                <input class="form-control" type="text" id="end_time">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5 btn btn-primary" id="submit">
            查询
        </div>
    </div>
</div>
</body>
<script src="../js/url_utils.js"></script>
<script src="http://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<link href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/i18n/jquery-ui-timepicker-zh-CN.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css"
      rel="stylesheet">
<script type="application/javascript">
    $(document).ready(function () {
        $("#begin_time").prop("readonly", true).datetimepicker({
            changeYear: true,
            showSecond: true,
            timeFormat: 'HH:mm:ss',
            dateFormat: "yy-mm-dd",
            onClose: function (selectedDate) {
                $("#end_time").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#end_time").prop("readonly", true).datetimepicker({
            changeYear: true,
            showSecond: true,
            timeFormat: 'HH:mm:ss',
            dateFormat: "yy-mm-dd",
            onClose: function (selectedDate) {
                $("#begin_time").datepicker("option", "maxDate", selectedDate);
                $("#end_time").val($(this).val());
            }
        });
        $("#submit").click(function () {
            var status0 = "false", status1 = "false", status2 = "false";
            if ($("#status0").is(":checked")) status0 = "true";
            if ($("#status1").is(":checked")) status1 = "true";
            if ($("#status2").is(":checked")) status2 = "true";
            var user_name = $("#user_name").val();
            var begin_time = $("#begin_time").val();
            var end_time = $("#end_time").val();
            var url = "./order_result.jsp";
            url = add_url_param(url, "status0", status0);
            url = add_url_param(url, "status1", status1);
            url = add_url_param(url, "status2", status2);
            if (user_name) url = add_url_param(url, "user_name", user_name);
            if (begin_time) url = add_url_param(url, "begin_time", begin_time);
            if (end_time) url = add_url_param(url, "end_time", end_time);
            location.href = url;
        });
    });
</script>
</html>
