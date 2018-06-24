$(function() {

	$(".ui.dropdown").dropdown();

	$("#amount").change(function() {
		if (isNaN($("#amount").val())) {
			$(this).val(1);
		}
		if(parseInt($("#amount").val()) > 999) {
			$(this).val(1);
		}
	});

	$("#amountSub").click(function() {
		var amount = parseInt($("#amount").val());
		if (amount > 1) {
			$("#amount").val(amount - 1);
		}
	});

	$("#amountAdd").click(function() {
		var amount = parseInt($("#amount").val());
		if (amount < 999) {
			$("#amount").val(amount + 1);
		}
	});

	var book = JSON.parse(sessionStorage['book']);
	console.log(book);
	$("#bookName").text(book.title);
	$("#authorName").text(book.author);
	$("#bookSummary").text(book.summary);
	$("#bookPrice").text(book.originalPrice);
	$("#bookImage").prop("src", book.imgUrl);


	$("#addToShopCart").click(function() {
		let isbn = book.isbn;
		let degree = $("#oldDegree").val();
		if (degree == "") {
			alert("请选择新旧程度");
			return;
		}
		let number = $("#amount").val();
		let data = {
			isbn: isbn,
			degree: degree,
			num: number
		};
		$.post("/user/trolley_add.do", data, function(result) {
			if (result.status == "success") {
				alert("添加成功");
			} else {
				alert("添加失败！请稍后再试");
			}
		})

	})

})