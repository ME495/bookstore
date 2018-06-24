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

	let url = window.location.href;
	let paymentId = /paymentId=(.+?)&/g.exec(url);
	let PayerID = /PayerID=(.+)/g.exec(url);
	if (paymentId != null && PayerID != null) {
		paymentId = paymentId[0];
		PayerID = PayerID[0];
		paymentId = paymentId.substring(paymentId.indexOf("=") + 1, paymentId.length - 1);
		PayerID = PayerID.substring(PayerID.indexOf("=") + 1);
		let data = {
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
		let data = [];
		for (let i in shopCart) {
			let book = shopCart[i];
			if (book.selected) {
				let obj = {
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
		let json = {
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
			let book = item;
			// console.log(index);
			let degree = "";
			if (book.degree == 0) {
				degree = "九成新";
			} else if (book.degree == 1) {
				degree = "七成新";
			} else {
				degree = "五成新";
			}

			let rowDiv = $("<div" + " class='shopcart-row'" + " data-bookisbn=" + book.isbn + " data-degree=" + book.degree + "></div>");
			let item1Div = $("<div class='item1'><div class='ui checkbox'><input type='checkbox' onchange='chooseBook(event)'><label></label></div></div>");
			let item2Div = $("<div class='item2'><div class='shopcart-image fl'><img src='" + book.imgUrl + "'/></div><div class='shopcart-summary fl'><h5>" + book.title + "</h5><p>" + degree + " </p><p class='shopcart-bookprice'>￥<span>" + book.actualPrice +"</span></p></div></div>");
			let item3Div = $("<div class='item3'><i class='icon close' onclick='deleteBook(event)'></i></div>");
			let item4Div = $("<div class='item4'><i class='icon minus' onclick='subCount(event)'></i><span class='shopcart-amount'>" + book.num + "</span><i class='icon plus' onclick='addCount(event)'></i></div>");
			rowDiv.append(item1Div).append(item2Div).append(item3Div).append(item4Div);
			$(".shopcart-body").append(rowDiv);

			let bookObj = {
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
				for (let index in shopCart) {
					var book = shopCart[index];
					book.selected = true;
				}
			})
		} else {
			$(".shopcart-container :checkbox").map(function(index, item) {
				$(item).prop("checked", false);
				for (let index in shopCart) {
					var book = shopCart[index];
					book.selected = false;
				}
			})
		}
		caculatePrice();
	}

	chooseBook = function(e) {
		let book = findBookInShopCart(e);
		if (book != null) {
			book.selected = $(e.target).prop("checked");
		}
		caculatePrice();
	}

	addCount = function(e) {
		let book = findBookInShopCart(e);
		if (book != null) {
			let data = {
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
		let book = findBookInShopCart(e);
		if (book != null) {
			if (book.num == 1) {
				return;
			}
			let data = {
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

	deleteBook = function(e) {
		let book = findBookInShopCart(e);
		if (book != null) {
			let data = {
				isbn: book.isbn,
				degree: book.degree
			};
			$.post("../user/trolley_delete.do", data, function(result) {
				if (result.status == "success") {
					deleteBookInShopCart(book);
			
					let row = $(e.target).parents(".shopcart-row");
					row.parent()[0].removeChild(row[0]);
					caculatePrice();
				} else {
					alert("系统繁忙，请稍后再试");
				}
			})
		}
	}


	findBookInShopCart = function(e) {
		let dom = $(e.target).parents(".shopcart-row");
		let isbn = dom.attr("data-bookisbn");
		let degree = dom.attr("data-degree");
		for (let index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn && book.degree == degree) {
				return book;
			}
		}
		return null;
	}

	deleteBookInShopCart = function(book) {
		let index = null;
		for (let i in shopCart) {
			let item = shopCart[i];
			if (item === book) {
				index = i;
			}
		}
		if (index != null) {
			shopCart.splice(index, 1);
		}
	}

	
	caculatePrice = function() {
		let totalPrice = 0;
		for (let index in shopCart) {
			let book = shopCart[index];
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