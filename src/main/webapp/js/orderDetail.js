$(function() {

	var orderId = /orderId=(.+)/g.exec(window.location.href)[0];
	orderId = orderId.substring(orderId.indexOf("=") + 1);
	// alert(orderId);

	var data = {
		order_id: orderId
	};

	$.post("../user/order_detail.do", data, function(result) {
		if (result.status == "success") {
	    	if (result.message.length != 0) {
	    		// $("#loader").parent().removeClass("active");
	    		$(".loader-pannel").hide();
	    		showOrderDetail(result.message);
	    		$(".shopcart-container").removeClass("not-show");
	    	} else {
	    		$("#loader").removeClass("loader").text("订单是空的 o_o?");
	    	}
    	} else {
    		$("#loader").removeClass("loader").text("出错啦，刷新试试吧");
    	}
	});


	showOrderDetail = function(booklist) {

		var order = JSON.parse(sessionStorage['order']);
		var orderstatus = "";
		var color = "";
		if (order.status === 0) {
			orderstatus = "未发货";
			color = "red";
		} else if (order.status === 1) {
			orderstatus = "正在配送";
			color = "orange";
		} else {
			orderstatus = "已收货";
			color = "green";
		}
		$("#orderStatus").text(orderstatus).addClass(color);
		$("#orderTotalPrice").text("￥" + order.money);

		booklist.map(function(item) {
			var degree = "";
			if (item.degree == 0) {
				degree = "九成新";
			} else if (item.degree == 1) {
				degree = "七成新";
			} else {
				degree = "五成新";
			}
			var rowDiv = $("<div class='orderdetail-item'><div class='orderdetail-content'><div class='orderdetail-img'><img src='" + item.imgUrl + "' /></div><div class='orderdetail-summary'><div class='orderdetail-booknum'>数量：" + item.num + "</div><h5>" + item.title + "</h5><p>" + degree + "</p><p class='shopcart-bookprice'>￥" + item.unitPrice + "</p></div></div></div>");
			$(".orderdetail-body").append(rowDiv);
		});

		if (order.status === 1) {
			$("#confirmRecieved").removeClass("not-show");
		}
	} 

	$("#confirmRecieved").click(function() {
		$.post("../user/confirm_order.do", data, function(result) {
			if (result.status == "success") {
				alert("已确认收货");
				window.location.href = "/user/orderList.html";
			} else {
				alert("出错啦，请稍后再试");
			}
		})
	})

})