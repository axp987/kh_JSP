<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			// ajax에서 한글 깨짐 문제 해결
			ContentType: "application/x-www-form-urlencoded;charset=UTF-8",
			type="get"
		});
		
	});
</script>
</head>
<body>
	<div align="center">
		<form method="post" action="mailSend.jsp">
			이메일 : <input type="text" id="email" name="email">
			제목: <input type="text" id="subject" name="subject">
			내용: <input type="text" id="content" name="content">
			<input type="submit" id="sendEmail" name="sendEmail" value="확인">
		</form>			
			<br><br>
			인증번호: <input type="text" id="loginToken" name="loginToken">
	</div>
</body>
</html>