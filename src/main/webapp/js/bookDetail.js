$(function() {

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
})