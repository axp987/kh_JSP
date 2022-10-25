/**
 * 
 */
 
 $(function() {
	$.ajaxSetup({
		ContentType: "application/x-www-form-urlencoded;charset=UTF-8",
		type: "post"
	});
	
	$("#signUpbtn").on("click", function() {
		if($(".lname").val() == '' || $(".fname").val() == '') {
			alert("이름을 입력해주세요.");
			return false;
		}
		else if($(".bir").val() == '') {
			alert("생년월일을 입력해주세요.");
			return false;
		} else if($(".phone").val() == '') { 
			alert("번호를 입력해주세요.");
			return false;			
		} else {
			$.ajax({
				url: "Projects4/signUp.do",
				data: $("#inform").serialize(),
				datatype: "text",
				success: function(data) {
					if(data > 0) {
						location.href=""; // 회원가입이 완료되었을때 이동경로
					}
					else {
						alert("오 류");
					}
				},
				error: function() {
					alert("통신 오류")
				}
			});	// $.ajax({
		}
	});	 // $("#signUpbtn")
		
}); //$(function()
	
