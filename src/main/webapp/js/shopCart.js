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

	var shopCart = [];

	$.get("/user/trolley_check.do", function(result) {
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

			let rowDiv = $("<div" + " class='shopcart-row'" + " data-bookisbn=" + book.isbn + "</div>");
			let item1Div = $("<div class='item1'><div class='ui checkbox'><input type='checkbox' onchange='chooseBook(event)'><label></label></div></div>");
			let item2Div = $("<div class='item2'><div class='shopcart-image fl'><img src='" + book.imgUrl + "'/></div><div class='shopcart-summary fl'><h5>" + book.title + "</h5><p>" + degree + " </p><p class='shopcart-bookprice'>￥<span>" + book.actualPrice +"</span></p></div></div>");
			let item3Div = $("<div class='item3'><i class='icon close' onclick='deleteBook(event)'></i></div>");
			let item4Div = $("<div class='item4'><i class='icon minus' onclick=subCount(event)></i><span class='shopcart-amount'>1</span><i class='icon plus' onclick=addCount(event)></i></div>");
			rowDiv.append(item1Div).append(item2Div).append(item3Div).append(item4Div);
			$(".shopcart-body").append(rowDiv);

			var bookObj = {
				isbn: book.isbn,
				number: 1,
				price: book.actual_price,
				selected: false
			};
			shopCart.push(bookObj);

		});

	}

	chooseAll = function() {
		if ($("#chooseAll").prop("checked")) {
			$(".shopcart-container :checkbox").map(function(index, item) {
				$(item).prop("checked", true);
				let isbn = $(item).parents(".shopcart-row").attr("data-bookisbn");
				for (let index in shopCart) {
					var book = shopCart[index];
					if (book.isbn == isbn) {
						book.selected = true;
					}
				}
			})
		} else {
			$(".shopcart-container :checkbox").map(function(index, item) {
				$(item).prop("checked", false);
				let isbn = $(item).parents(".shopcart-row").attr("data-bookisbn");
				for (let index in shopCart) {
					var book = shopCart[index];
					if (book.isbn == isbn) {
						book.selected = false;
					}
				}
			})
		}
		caculatePrice();
	}

	chooseBook = function(e) {
		let isbn = $(e.target).parents(".shopcart-row").attr("data-bookisbn");
		for (let index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn) {
				book.selected = $(e.target).prop("checked");
			}
		}
		caculatePrice();
	}

	addCount = function(e) {
		let isbn = $(e.target).parents(".shopcart-row").attr("data-bookisbn");
		for (let index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn) {
				book.number++;
				$(e.target).siblings("span").text(book.number);
			}
		}
		caculatePrice();
	}

	subCount = function(e) {
		let isbn = $(e.target).parents(".shopcart-row").attr("data-bookisbn");
		for (let index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn) {
				book.number = (book.number == 1 ? 1 : (book.number-1));
				$(e.target).siblings("span").text(book.number);
			}
		}
		caculatePrice();
	}

	deleteBook = function(e) {
		let isbn = $(e.target).parents(".shopcart-row").attr("data-bookisbn");
		for (let index in shopCart) {
			var book = shopCart[index];
			if (book.isbn == isbn) {
				book.selected = false;
			}
		}
		let row = $(e.target).parents(".shopcart-row");
		row.parent()[0].removeChild(row[0]);
		caculatePrice();
	}

	
	caculatePrice = function() {
		let totalPrice = 0;
		for (let index in shopCart) {
			let book = shopCart[index];
			if (book.selected) {
				totalPrice += book.price * book.number;
			}
		}
		totalPrice = Math.round(totalPrice * 100) / 100;
		$("#totalCount").text(totalPrice);
		return totalPrice;
	}


	
})