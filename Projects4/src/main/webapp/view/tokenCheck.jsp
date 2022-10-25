<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 코드</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript" src="../js/tokenCheck.js"></script>
</head>
<body>
	<div align="center">
		◀<h4>전화번호 인증하기</h4>
		<hr>
			<c:set var="infor" value="${user }" />
			<form id="formsubmit" method="post" action="<%=request.getContextPath() %>/tokenCheck_ok.do">
				${infor.getEmail() }으로 보내드린 인증 코드를 입력하세요.
				<br>
				<input type="text" name="inputToken" id="inputToken" placeholder="인증번호를 입력해주세요.">
				<span id="ck"></span>
			</form>
		<hr>
	</div>
</body>
</html>