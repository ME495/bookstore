$(function() {

	$("#toLogin").click(function() {
		$("#signModal").modal("show");
	})

	$(".button").popup();

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

})