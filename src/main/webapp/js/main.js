$(function() {

	$("#toLogin").click(function() {
		$("#loginModal").modal("show");
	});
	$("#toRegister").click(function() {
		$("#registerModal").modal("show");
	})

	$(".button").popup();

	$(".message .close").click(function() {
		$(this).closest(".message").transition("fade");
	})

	//焦点图切换功能
	var currentAdImageIndex = 1;
	var lastAdImageIndex = 0;

	$(".ad-box img:not(:eq(" + lastAdImageIndex + "))").hide();

	var adTimer = setInterval(function() {
		changeAdImage();
	}, 5000);

	changeAdImage = function() {
		// $(".ad-box img:eq(" + lastAdImageIndex + ")").removeClass("active");
		$(".ad-box img:eq(" + lastAdImageIndex + ")").fadeOut();
		$(".ad-box-dots span:eq(" + lastAdImageIndex + ")").removeClass("active");

		$(".ad-box img:eq(" + currentAdImageIndex + ")").fadeIn();
		// $(".ad-box img:eq(" + currentAdImageIndex + ")").addClass("active");
		$(".ad-box-dots span:eq(" + currentAdImageIndex + ")").addClass("active");
		lastAdImageIndex = currentAdImageIndex;
		currentAdImageIndex++;
		currentAdImageIndex = currentAdImageIndex % 5;
	}

	$(".ad-box-dots span").click(function() {
		var index = $(".ad-box-dots span").index(this);
		currentAdImageIndex = index;
		changeAdImage();
	});

	$(".ad-box img").mouseenter(function() {
		clearInterval(adTimer);
	})

	$(".ad-box img").mouseleave(function() {
		adTimer = setInterval(function() {
			changeAdImage();
		}, 5000);
	});


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
    	var user_name = $("#registerModal [name='user_name']").val();
    	var password = $("#registerModal [name='password']").val();
    	var email = $("#registerModal [name='email']").val();
    	if (user_name.trim() == "" || password.trim() == "" || email.trim() == "") {
    		$("#registerErrorMsg p").text("请填入完整信息");
    		$("#registerErrorMsg").transition('show');
    	} else {
    		var data = {
    			user_name: user_name,
    			password: password,
    			email: email
    		};
    		$.post("/user/signip.do", data, function(result) {
    			result = JSON.parse(result);
    			if (result.status == "fail") {
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
    		$.post("/user/signip.do", data, function(result) {
    			result = JSON.parse(result);
    			if (result.status == "fail") {
    				$("#registerErrorMsg p").text("用户名或密码错误");
    				$("#registerErrorMsg").transition("show");
    			} else {
    				showToast("登录成功");
    				setTimeout(function() {
    					window.location.refresh();
    				})
    			}
    		})
    	}
    })


})