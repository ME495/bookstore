//首页的JS
$(function() {


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


    //首页显示的图书内容
    var browse_data = {
    	index: 0,
    	size: 15
    };
    $.post("/browse_book.do", browse_data, function(result) {
    	// console.log(result);
    	// result = JSON.parse(result);
    	if (result.status == "success") {
    		$("#loader").parent().removeClass("active");
    		$(".loader-pannel").hide();
    		showBooks(result.message);
    		$(".books-container").removeClass("not-show");
    		sessionStorage['booklist'] = JSON.stringify(result.message);
    	} else {
    		$("#loader").removeClass("loader").text("出错啦，刷新试试吧");
    	}

    });

    

})