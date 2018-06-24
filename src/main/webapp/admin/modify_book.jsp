<%--
  Created by IntelliJ IDEA.
  User: ME495
  Date: 2018/6/15
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改图书</title>
    <meta name="referrer" content="no-referrer">
</head>
<body>
<%@include file="navbar.jspf" %>
<div class="container">
    <div class="page_header text-center" style="margin-bottom: 50px;">
        <h1>请输入要修改图书的ISBN和新旧程度</h1>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">ISBN：</label>
            <div class="col-xs-4">
                <input class="form-control" type="text" id="input_isbn">
            </div>
        </div>
    </div>
    <div class="form-horizontal col-xs-offset-3">
        <div class="form-group">
            <label class="col-xs-2 control-label">新旧程度：</label>
            <div class="col-xs-4">
                <select class="selectpicker form-control" id="input_degree">
                    <option>九成新</option>
                    <option>七成新</option>
                    <option>五成新</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5 btn btn-primary" data-toggle="modal" data-target="#myModal" id="submit">
            确定
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改图书</h4>
                </div>
                <div class="modal-body">
                    <div class="text-center">
                        <img class="img-thumbnail">
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
                                <input class="form-control" type="number" min="0" step="1" id="num">
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-xs-offset-2">
                        <div class="form-group">
                            <label class="col-xs-2 control-label">售价：</label>
                            <div class="col-xs-6">
                                <input class="form-control" type="number" min="0" step="0.1" id="actual_price">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="confirm">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="application/javascript">
    $(document).ready(function () {
        var degree, isbn;
        $("#submit").click(function () {
            isbn = $("#input_isbn").val();
            var degree_text = $("#input_degree").val();
            if (degree_text == "九成新") degree = 0;
            else if (degree_text == "七成新") degree = 1;
            else degree = 2;
            var post_data = {"isbn": isbn, "degree": degree};
            console.log(post_data);
            $.post("../book_detail.do", post_data, function (data, status) {
                if (status == "success" && data.status == "success") {
                    var message = data.message;
                    console.log(message);
                    $("#isbn").val(message.book.isbn);
                    $("#title").val(message.book.title);
                    $("#author").val(message.book.author);
                    $("#publisher").val(message.book.publisher);
                    $("#summary").val(message.book.summary);
                    $("#original_price").val(message.book.originalPrice);
                    $("img").attr("src", message.book.imgUrl);
                    $("#degree").val(degree_text);
                    $("#num").val(message.num);
                    $("#actual_price").val(message.actualPrice);
                } else if (status == "fail") {
                    alert("修改失败，网络错误！");
                } else if (data.status == "fail") {
                    if (data.message) alert(data.message);
                    else alert("修改失败！");
                }
            });
        });
        $("#confirm").click(function () {
            var num = $("#num").val();
            var actual_price = $("#actual_price").val();
            var post_data = {
                "isbn": isbn,
                "degree": degree,
                "num": num,
                "actual_price": actual_price
            };
            console.log(degree);
            $.post("./modify_book.do", post_data, function (data, status) {
                if (status == "success" && data.status == "success") {
                    alert("修改成功！");
                } else {
                    alert("修改失败！");
                }
            });
        });
    });
</script>
</html>
