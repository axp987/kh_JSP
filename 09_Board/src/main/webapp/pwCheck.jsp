<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/style.css">
<body>
	<div align="center">
		<form method="post" action="<%=request.getContextPath() %>/password.do">
			<h3>패스워드를 입력해주세요.</h3>
			<input type="password" name="pw">
			<br> <br>
			<input type="submit" value="확인">
			<input type="button" value="종료" onclick=windows.close()>
		</form>
	</div>
</body>
</html>