$(function() {

	var param = "?status0=true&status1=true&status2=true&index=0&size=99";

	var orderList = null;

	$.get("../user/my_order.do" + param, function(result) {
		if (result.status == "success") {
	    	if (result.message.length != 0) {
	    		// $("#loader").parent().removeClass("active");
	    		$(".loader-pannel").hide();
	    		orderList = result.message;
	    		showOrderList(result.message);
	    		$(".shopcart-container").removeClass("not-show");
	    	} else {
	    		$("#loader").removeClass("loader").text("还没有下过订单 u.u");
	    	}
    	} else {
    		$("#loader").removeClass("loader").text("出错啦，刷新试试吧");
    	}
	});


	showOrderList = function(booklist) {

		booklist.map(function(item) {
			var orderstatus = "";
			var color = "";
			if (item.status === 0) {
				orderstatus = "未发货";
				color = "red";
			} else if (item.status === 1) {
				orderstatus = "正在配送";
				color = "orange";
			} else {
				orderstatus = "已完成";
				color = "green";
			}
			var orderDetailUrl = "./orderDetail.html?orderId=" + item.orderId;

			var rowDiv = $("<div data-orderid=" + item.orderId + "></div>");
			var header = $("<h4 class='ui top attached block header'><span>订单号-" + item.orderId + "</span><span class='ui label " + color + " fr'>" + orderstatus + "</span></h4>");
			var body = $("<div class='ui bottom attached segment'><button class='ui button basic blue fr' onclick='toOrderDetail(event)'>查看详情</button><div>时间：<span>" + item.orderTime + "</span></div><div>地址：<span>" + item.address + "</span></div><div>金额：<span>" + item.money + "</span></div></div>");
			var divider = $("<div class='ui section divider'></div>");
			rowDiv.append(header).append(body).append(divider);
			$(".orderlist-container").append(rowDiv);
		});
	}

	toOrderDetail = function(e) {
		var orderId = $(e.target).parent().parent().attr("data-orderid");
		console.log(orderList);
		console.log(orderId);
		for (var i in orderList) {
			if (orderList[i].orderId == orderId) {
				sessionStorage['order'] = JSON.stringify(orderList[i]);
				window.location.href = "./user/orderDetail.html?orderId=" + orderId;
			}
		}
	}


})