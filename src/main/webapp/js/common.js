//几个页面通用的JS
$(function() {

	$("#toLogin").click(function() {
		$("#loginModal").modal("show");
	});
	$("#toRegister").click(function() {
		$("#registerModal").modal("show");
	})

	$(".button").popup();

	$(".dropdown").dropdown();

	$(".message .close").click(function() {
		$(this).closest(".message").transition("fade");
	})

	
	//检查登录状态
	$.post("/check_login.do", function(result) {
		// result = JSON.parse(result);
		if (result.status === "success") {
			$("#userinfo").text(result.message.name).parents("li").show();
			$("#toLogin, #toRegister").hide();
			sessionStorage['logined'] = true;
		}
	})


	//自己写的一个小插件，类似于Andriod中的Toast消息提示
	var timer1, timer2;
	$.charlieToast = function(message, type) {
        clearTimeout(timer1);
        clearTimeout(timer2);

        $("#toast").text(message);

        if (type === 1) {
            $("#toast").css("background", "#9de263");
        } else {
            $("#toast").css("background", "#F01786");
        }

        $("#toast").css("opcaity", "1");

        $("#toast").show().addClass("toast-show");
        timer1 = setTimeout(function() {
            $("#toast").removeClass("toast-show").addClass("toast-hide");
        }, 1600);
        timer2 = setTimeout(function() {
            $("#toast").removeClass("toast-hide").hide();
        }, 2200);
    }

   	showToast = function(message) {
   		alert(message);
   	}

   	//注册和登录部分
    $("#registerButton").click(function() {
    	let user_name = $("#registerModal [name='user_name']").val();
    	let password = $("#registerModal [name='password']").val();
    	// var email = $("#registerModal [name='email']").val();
    	let phone = $("#registerModal [name='phone']").val();
    	let real_name = $("#registerModal [name='real_name']").val();
    	let address = $("#registerModal [name='address']").val();
    	if (user_name.trim() == "" || password.trim() == "" || phone.trim() == "" || real_name.trim() == "" || address.trim() == "") {
    		$("#registerErrorMsg p").text("请填入完整信息");
    		$("#registerErrorMsg").transition('show');
    	} else {
    		var data = {
    			user_name: user_name,
    			password: password,
    			// email: email,
    			phone: phone,
    			real_name: real_name,
    			address: address
    		};
    		$.post("/signup.do", data, function(result) {
    			// result = JSON.parse(result);
    			alert(result.status);
    			if (result.status === "fail") {
    				$("#registerErrorMsg p").text(result.message);
    				$("#registerErrorMsg").transition("show");
    			} else {
    				showToast("注册成功，请前往登录");
    				setTimeout(function() {
    					$("#registerModal").modal("hide");
    				}, 1000);
    			}
    		})
    	}
    });


    $("#loginButton").click(function() {
    	var user_name = $("#loginModal [name='user_name']").val();
    	var password = $("#loginModal [name='password']").val();
    	if (user_name.trim() == "" || password.trim() == "") {
    		$("#loginErrorMsg p").text("请填入完整信息");
    		$("#loginErrorMsg").transition('show');
    	} else {
    		var data = {
    			user_name: user_name,
    			password: password
    		};
    		$.post("/user_login.do", data, function(result) {
    			// result = JSON.parse(result);
    			if (result.status == "fail") {
    				$("#registerErrorMsg p").text("用户名或密码错误");
    				$("#registerErrorMsg").transition("show");
    			} else {
    				showToast("登录成功");
    				setTimeout(function() {
    					window.location.reload();
    				}, 1000);
    			}
    		})
    	}
    });

    $("#searchBook").click(function() {
    	$(".search-container .label").addClass("hidden");
    })

    //查找图书
    $("#toSearch").click(function() {
    	var searchText = $("#searchBook").val().trim();
    	console.log(searchText);
    	if (searchText == "") {
    		$(".search-container .label").removeClass("hidden");
    	} else {
    		var url = "/searchResult.html?keyword=" + searchText + "&index=0&size=15";
    		url = encodeURI(url);
    		window.location.href = url;
    	}
    });

    showBooks = function(booklist) {
    	for (var i = 0; i < booklist.length; i++) {
    		var book = booklist[i];
    		var div1 = $("<div class='book-item'></div>");
	    	var a1 = $("<a class='book-link' data-bookindex=\'" + i + "\' onclick='toBookDetail(event)'></a>");
    		var imgDiv = document.createElement("img");
    		$(imgDiv).addClass("book-image-normal").attr("src", book.imgUrl);
    		var bookNameDiv = document.createElement("div");
    		$(bookNameDiv).addClass("book-name").text(book.title);
    		var bookPriceDiv = document.createElement("div");
    		$(bookPriceDiv).addClass("book-price").text("￥" + book.originalPrice);
    		$(a1).append(imgDiv).append(bookNameDiv);
    		$(div1).append(a1).append(bookPriceDiv);
    		$(".books-container").append(div1);
    	}

    }

    toBookDetail = function(e) {
    	var index = $($(e.target).parent()).attr("data-bookindex");
    	var books = JSON.parse(sessionStorage['booklist']);
    	console.log("books:" + books);
    	sessionStorage['book'] = JSON.stringify(books[index]);
    	window.location.href = "/bookDetail.html?isbn=" + books[index].isbn;
    }


    $("#toShopCart").click(function() {
    	// alert(sessionStorage['logined']);
    	if (sessionStorage['logined']) {
    		// console.log("jump");
    		window.location.href = "/user/shopCart.html";
    	}
    });

    $("#toOrderList").click(function() {
    	if (sessionStorage['logined']) {
    		window.location.href = "/user/orderList.html";
    	}
    })


})