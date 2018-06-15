<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/14
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>确认添加图书</title>
    <meta name="referrer" content="no-referrer">
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="container">
    <div class="text-center">
        <img>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">isbn：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="isbn">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">书名：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="title">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">作者：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="author">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">出版社：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="publisher">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">摘要：</label>
            <div class="col-xs-6">
                <textarea class="form-control" rows="10" readonly="readonly" id="summary"></textarea>
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">原始价格：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="original_price">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">新旧程度：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="degree">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-2">
        <div class="form-group">
            <label class="col-xs-2 control-label">数量：</label>
            <div class="col-xs-6">
                <input class="form-control" readonly="readonly" type="text" id="num">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5 btn btn-primary" id="submit">
            确认添加
        </div>
    </div>
</div>
</body>
<script src="../js/referrer-killer.js"></script>
<script src="../js/url_utils.js"></script>
<script type="application/javascript">
    var isbn = getUrlParam("isbn");
    var degree = decodeURI(getUrlParam("degree"));
    var num = getUrlParam("num");
    var title = null;
    var author = null;
    var publisher = null;
    var summary = null;
    var original_price = null;
    var img_url = null;
    function showData(data) {
        title = data.title;
        author = JSON.stringify(data.author).replace(/\[|]/g,'');
        publisher = data.publisher;
        summary = data.summary;
        original_price = data.price.replace(/(\d+.\d+)[元￥$]/, '$1');
        img_url = data.images.small;
        // document.getElementById('image_kill_referrer').innerHTML = ReferrerKiller.imageHtml(img_url);
        // $("#image_kill_referrer").html(ReferrerKiller.imageHtml(img_url));
        $("img").attr("src", img_url);
        $("#isbn").val(isbn);
        $("#title").val(title);
        $("#author").val(author);
        $("#publisher").val(publisher);
        $("#summary").val(summary);
        $("#original_price").val(original_price);
        $("#degree").val(degree);
        $("#num").val(num);
    }
    $.ajax({
        url: "https://api.douban.com/v2/book/isbn/:"+isbn,
        type: "GET",
        timeout: 2000,
        dataType: "jsonp"
    }).success(function (data) {
        showData(data);
    }).fail(function () {
        alert("没有找到对应的图书！");
        location.href = "./add_book.jsp";
    });
    var degree_num = null;
    if (degree == "九成新") degree_num = 0;
    else if (degree == "七成新") degree_num = 1;
    else degree_num = 2;
    $("#submit").click(function () {
        if (summary.length > 255) {
            summary = summary.substr(0, 249) + "......";
        }
        var param = {
            "isbn": isbn,
            "title": title,
            "author": author,
            "publisher": publisher,
            "summary": summary,
            "img_url": img_url,
            "original_price": original_price,
            "degree": degree_num,
            "num": num
        };
        $.post("./add_book.do", param, function (data, status) {
            if (status == "success" && data.status == "success") {
                alert("添加成功！");
            } else {
                alert("添加失败！");
            }
            location.href = "./add_book.jsp";
        });
    });
</script>
</html>
