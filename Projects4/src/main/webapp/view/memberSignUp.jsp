<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String mail = (String)session.getAttribute("membermail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript" src="../js/normalJoin.js"></script>
</head>
<body>
	<div align="center">
		◀<h3>회원 가입 완료하기</h3>
		<hr>
	</div>
	<div>
		<form method="post" id="inform" name="inform" action="">
			<input type="text" id="email" class="field" name="email" placeholder="<%=mail %>" readonly>
			<br>
			<input type="text" id="lname" class="field" name="lastname" placeholder="이름(예:길동)">
			<br>
			<input type="text" id="fname" class="field" name="firstname" placeholder="성(예: 홍)">
			<p>
			<input type="text" id="bir" class="field" name="birth" placeholder="생년월일(19900101)">
			<br>
			만 18세 이상의 성인만 회원으로 가입할 수 있습니다. 생일은 다른 회원에게 공개되지 않습니다.
			<p>
			<input type="text" id="ph" class="field" name="phone" placeholder="핸드폰 번호">
			<br>
			예약 확인과 영수증을 이메일로 보내드립니다.
			<p>
			<input type="submit" id="signUpbtn" name="signUpbtn" value="동의 및 계속하기">
		</form>		
	</div>
</body>
</html>