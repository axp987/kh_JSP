<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	function process() {
		$.ajax({
			type: "get",
			url: "test.jsp",
			data: {param: "Hello Ajax!!!"},
			datatype: "text",
			success: function(data) { // function(성공시 들어올 변수명)
				$(".message").append(data);
			},
			error: function(data) {
				alert("에러가 발생하였습니다.");
			}
		});
	}
</script>
</head>
<body>
	<div align="center">
		<input type="button" value="전송" onclick="process()">
		<div class="message"></div>
	</div>
</body>
</html>