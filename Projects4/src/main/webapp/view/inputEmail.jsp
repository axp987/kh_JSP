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
		x<h4>로그인 또는 회원가입</h4>
		
		<hr>
		
		<h2>에어비앤비에 오신 것을 환영합니다.</h2>
		
		<table>
			<tr>
				<td>
					<form method="post" action="<%=request.getContextPath() %>/sendMail.do">
						<input type="text" id="email" name="email" placeholder="Email">
						@
						<select name="domain">
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
						</select>
						<input type="submit" id="sendEmail" name="sendEmail" value="확인">
					</form>
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>