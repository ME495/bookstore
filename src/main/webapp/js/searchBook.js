$(function() {

	console.log(sessionStorage['abc']);
	//通过当前url获取查找的关键字
	var url = window.location.href;
	var keywordReg = /keyword=(.+?)&/g;
	var keyword = keywordReg.exec(url)[0];
	keyword = keyword.substring(keyword.indexOf("=") + 1, keyword.length - 1);
	keyword = decodeURI(keyword);

	var indexReg = /index=(.)+?&/g;
	var index = indexReg.exec(url)[0];
	index = index.substring(index.indexOf("=") + 1, index.length - 1);

	var size = 15;

	var currentPage = Math.floor(index / 15) + 1;

	$(".pagination-container .page-num:eq(" + (currentPage - 1) + ")").addClass("active");

	var searchData = {
		index: index,
		key_word: keyword,
		size: size
	};
	$.post("/search_book.do", searchData, function(result) {
		// result = JSON.parse(result);
		if (result.status == "fail") {
			$("#loader").removeClass("loader").text(result.message);
			$(".pagination-container").hide();
		} else if (result.message.length === 0) {
			$("#loader").removeClass("loader").text("=_=! 没有数据");
			$(".pagination-container").hide();
		} else {
			$("#loader").parent().removeClass("active");
			$(".loader-pannel").hide();
			showBooks(result.message);
			$(".books-container").removeClass("not-show");
			sessionStorage['booklist'] = JSON.stringify(result.message);
		}
	});

	$(".pagination-container .page-num").click(function() {
		var jumpPage = $(this).text();
		var jumpIndex = (jumpPage - 1) * size;
		window.location.href = "./searchResult.html?keyword=" + keyword + "&index=" + jumpIndex + "&size=15";
	})


})