$(function() {

	// shopCart = [
	// 	{
	// 		isbn: "1",
	// 		number: 1,
	// 		price: 60.80,
	// 		selected: false
	// 	},
	// 	{
	// 		isbn: "2",
	// 		number: 1,
	// 		price: 60.80,
	// 		selected: false
	// 	},
	// 	{
	// 		isbn: "3",
	// 		number: 1,
	// 		price: 60.80,
	// 		selected: false
	// 	}
	// ];

	var url = window.location.href;
	var paymentId = /paymentId=(.+?)&/g.exec(url);
	var PayerID = /PayerID=(.+)/g.exec(url);
	if (paymentId != null && PayerID != null) {
		paymentId = paymentId[0];
		PayerID = PayerID[0];
		paymentId = paymentId.substring(paymentId.indexOf("=") + 1, paymentId.length - 1);
		PayerID = PayerID.substring(PayerID.indexOf("=") + 1);
		var data = {
			paymentId: paymentId,
			PayerID: PayerID
		};
		$.post("../user/paypalReturn.do", data, function(result) {
			if (result.status == "success") {
				alert("支付成功");
				window.location.href = "../user/orderList.html";
			} else {
				alert("支付失败！");
			}
		})

	}


	var shopCart = [];

	$.get("../user/trolley_check.do", function(result) {
		if (result.status == "success") {
	    	if (result.message.length != 0) {
	    		// $("#loader").parent().removeClass("active");
	    		$(".loader-pannel").hide();
	    		showShopCart(result.message);
	    		$(".shopcart-container").removeClass("not-show");
	    	} else {
	    		$("#loader").removeClass("loader").text("购物车是空的 o_o!");
	    	}
    	} else {
    		$("#loader").removeClass("loader").text("出错啦，刷新试试吧");
    	}
	});

	$("#toPay").click(function() {
		var data = [];
		for (var i in shopCart) {
			var book = shopCart[i];
			if (book.selected) {
				var obj = {
					isbn: book.isbn,
					num: book.num,
					degree: book.degree
				};
				data.push(obj);
			}
		}
		if (data.length == 0) {
			return;
		}
		var json = {
			trolleyMsg: JSON.stringify(data)
		};
		$.post("../user/payment.do", json, function(result) {
			if (result.status == "success") {
				window.location.href = result.message;
			} else {
				alert("请稍后再试");
			}
		})
	})

	showShopCart = function(booklist) {
		booklist.map(function(item, index) {
			var book = item;
			// console.log(index);
			var degree = "";
			if (book.degree == 0) {
				degree = "九成新";
			} else if (book.degree == 1) {
				degree = "七成新";
			} else {
				degree = "五成新";
			}

			var rowDiv = $("<div" + " class='shopcart-row'" + " data-bookisbn=" + book.isbn + " data-degree=" + book.degree + "></div>");
			var item1Div = $("<div class='item1'><div class='ui checkbox'><input type='checkbox' onchange='chooseBook(event)'><label></label></div></div>");
			var item2Div = $("<div class='item2'><div class='shopcart-image fl'><img src='" + book.imgUrl + "'/></div><div class='shopcart-summary fl'><h5>" + book.title + "</h5><p>" + degree + " </p><p class='shopcart-bookprice'>￥<span>" + book.actualPrice +"</span></p></div></div>");
			var item3Div = $("<div class='item3'><i class='icon close' onclick='devareBook(event)'></i></div>");
			var item4Div = $("<div class='item4'><i class='icon minus' onclick='subCount(event)'></i><span class='shopcart-amount'>" + book.num + "</span><i class='icon plus' onclick='addCount(event)'></i></div>");
			rowDiv.append(item1Div).append(item2Div).append(item3Div).append(item4Div);
			$(".shopcart-body").append(rowDiv);

			var bookObj = {
				isbn: book.isbn,
				num: 1,
				price: book.actualPrice,
				selected: false,
				degree: book.degree
			};
			shopCart.push(bookObj);

		});

	}

	chooseAll = function() {
		if ($("#chooseAll").prop("checked")) {
			$(".shopcart-container :checkbox").map(function(index, item) {
				$(item).prop("checked", true);
				for (var index in shopCart) {
					var book = shopCart[index];
					book.selected = true;
				}
			})
		} else {
			$(".shopcart-container :checkbox").map(function(index, item) {
				$(item).prop("checked", false);
				for (var index in shopCart) {
					var book = shopCart[index];
					book.selected = false;
				}
			})
		}
		caculatePrice();
	}

	chooseBook = function(e) {
		var book = findBookInShopCart(e);
		if (book != null) {
			book.selected = $(e.target).prop("checked");
		}
		caculatePrice();
	}

	addCount = function(e) {
		var book = findBookInShopCart(e);
		if (book != null) {
			var data = {
				isbn: book.isbn,
				num: book.num+1,
				degree: book.degree
			};
			$.post("../user/trolley_update.do", data, function(result) {
				if (result.status === "success") {
					book.num++;
					$(e.target).siblings("span").text(book.num);
				} else {
					alert("服务器繁忙");
				}
			})
		}
		caculatePrice();
	}

	subCount = function(e) {
		var book = findBookInShopCart(e);
		if (book != null) {
			if (book.num == 1) {
				return;
			}
			var data = {
				isbn: book.isbn,
				num: book.num-1,
				degree: book.degree
			};
			$.post("../user/trolley_update.do", data, function(result) {
				if (result.status === "success") {
					book.num--;
					$(e.target).siblings("span").text(book.num);
				} else {
					alert("服务器繁忙");
				}
			})

		}
		caculatePrice();
	}

	devareBook = function(e) {
		var book = findBookInShopCart(e);
		if (book != null) {
			var data = {
				isbn: book.isbn,
				degree: book.degree
			};
			$.post("../user/trolley_devare.do", data, function(result) {
				if (result.status == "success") {
					devareBookInShopCart(book);
			
					var row = $(e.target).parents(".shopcart-row");
					row.parent()[0].removeChild(row[0]);
					caculatePrice();
				} else {
					alert("系统繁忙，请稍后再试");
				}
			})
		}
	}


	findBookInShopCart = function(e) {
		var dom = $(e.target).parents(".shopcart-row");
		var isbn = dom.attr("data-bookisbn");
		var degree = dom.attr("data-degree");
		for (var index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn && book.degree == degree) {
				return book;
			}
		}
		return null;
	}

	devareBookInShopCart = function(book) {
		var index = null;
		for (var i in shopCart) {
			var item = shopCart[i];
			if (item === book) {
				index = i;
			}
		}
		if (index != null) {
			shopCart.splice(index, 1);
		}
	}

	
	caculatePrice = function() {
		var totalPrice = 0;
		for (var index in shopCart) {
			var book = shopCart[index];
			if (book.selected) {
				totalPrice += book.price * book.num;
			}
		}
		totalPrice = Math.round(totalPrice * 100) / 100;
		$("#totalCount").text(totalPrice);
		// console.log(shopCart);
		return totalPrice;
	}


	
})