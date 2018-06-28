$(function() {

	$(".ui.dropdown").dropdown();

	$("#amount").change(function() {
		if (isNaN($("#amount").val())) {
			$(this).val(1);
		}
		if(parseInt($("#amount").val()) > 999) {
			$(this).val(1);
		}
	});

	$("#amountSub").click(function() {
		var amount = parseInt($("#amount").val());
		if (amount > 1) {
			$("#amount").val(amount - 1);
		}
	});

	$("#amountAdd").click(function() {
		var amount = parseInt($("#amount").val());
		if (amount < 999) {
			$("#amount").val(amount + 1);
		}
	});

	var isbn = JSON.parse(sessionStorage['book']).isbn;

	$.post("./book_detail.do",{isbn: isbn, degree: 0}, function(result) {
		// alert(1);
		if (result.status == "success") {
	    	if (result.message) {
	    		// $("#loader").parent().removeClass("active");
	    		$(".loader-pannel").hide();
	    		showBookDetail(result.message);
	    		$(".book-info-container").removeClass("not-show");
	    	} else {
	    		$("#loader").removeClass("loader").text("没有找到该图书 =_=!");
	    	}
    	} else {
    		$("#loader").removeClass("loader").text("出错啦，刷新试试吧");
    	}
	});


	$.post("./book_degrees.do", {isbn: isbn}, function(result) {
		if (result.status == "success") {
			var arr = result.message;
			if (arr.length == 0) {
				alert("图书暂时无货");
				return;
			}
			arr.map(function(value) {
				if (value == 0) {
					$("#oldDegree").append($("<option value='0'>九成新</option>"));
				} else if (value == 1) {
					$("#oldDegree").append($("<option value='1'>七成新</option>"));
				} else if (value == 2) {
					$("#oldDegree").append($("<option value='2'>五成新</option>"));
				}
			});
			$("#oldDegree").children()[0].selected = true;
		}
	})

						
	                	
	                	


	showBookDetail = function(obj) {
		var book = obj.book;
		$("#bookName").text(book.title);
		$("#authorName").text(book.author);
		$("#bookSummary").text(book.summary);
		$("#bookPrice").text(obj.actualPrice);
		$("#bookImage").prop("src", book.imgUrl);
		$("#oldDegree").val(0);
	}

	$("#oldDegree").change(function() {
		var degree = $(this).val();
		$.post("./book_detail.do", {isbn: isbn, degree: degree}, function(result) {
			if (result.status == "success") {
				$("#bookPrice").text(result.message.actualPrice);
			} else {
				alert("服务器繁忙，请稍后再试");
			}
		});
	})


	$("#addToShopCart").click(function() {
		if (!sessionStorage['logined']) {
			alert("请先登录!");
			return;
		}
		var degree = $("#oldDegree").val();
		if (degree == "") {
			alert("请选择新旧程度");
			return;
		}
		var number = $("#amount").val();
		var data = {
			isbn: isbn,
			degree: degree,
			num: number
		};
		$.post("./user/trolley_add.do", data, function(result) {
			if (result.status == "success") {
				alert("添加成功");
			} else {
				alert("添加失败！请稍后再试");
			}
		})

	})

})