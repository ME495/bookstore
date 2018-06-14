<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/14
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <div class="page_header text-center" style="margin-bottom: 50px;">
        <h1>请输入要添加的图书</h1>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">ISBN：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="isbn">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">新旧程度：</label>
            <div class="col-xs-4">
            <select class="selectpicker form-control">
                <option>九成新</option>
                <option>七成新</option>
                <option>五成新</option>
            </select>
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">数量：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="num">
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
            var isbn = $("#isbn").val();
            var degree = $("select").val();
            var num = $("#num").val();
            var url = "./confirm_add_book.jsp";
            url = addUrlParam(url, "isbn", isbn);
            url = addUrlParam(url, "degree", degree);
            url = addUrlParam(url, "num", num);
            location.href = encodeURI(url);
        });
    });
</script>
</html>
