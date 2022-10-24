<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 코드</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			ContentType: "application/x-www-form-urlencoded;charset=UTF-8",
			type: "post"
		});
		
		
		$("#inputToken").keyup(function() {
			if($.trim($("#inputToken").val().length < 6)) {
				let warning = '<font color="red">숫자 6개를 입력해주세요.</font>';
				$("#ck").text('');
				$("#ck").show();
				$("#ck").append(warning);
			} else if($.trim($("inputToken").val().length <= 7)) {
				let over = $("inputToken").val();
				$("inputToken").val(over.slice(0, -1));
				$("#ck").text('');
				$("#ck").show();
			}
		});
	});
</script>
</head>
<body>
	<div align="center">
		◀<h4>전화번호 인증하기</h4>
		<hr>
			<c:set var="infor" value="${user }" />
			<form method="post" action="<%=request.getContentType()%>/tokenCheck_ok.do">
				<input type="hidden" name="email" value="${infor.getEmail() }">
				<input type="hidden" name="token" value="${infor.getToken() }">
				${infor.getEmail() }으로 보내드린 인증 코드를 입력하세요.
				<br>
				<input type="text" name="inputToken" id="inputToken" placeholder="인증번호를 입력해주세요.">
				<span id="ck"></span>
			</form>
		<hr>
	</div>
</body>
</html>