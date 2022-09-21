<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("id", "hong");
	
	// 유효 기간 설정 방법
	// setMaxAge(): 쿠키의 유효기간을 설정하는 메소드.
	// 유효 기간 설정 방법: 60초 * 30 = 30
	cookie.setMaxAge(60 * 30);
	
	// 쿠키를 클라이언트 컴퓨터에 전송
	response.addCookie(cookie);
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="ex01_01.jsp">
		<input type="submit" value="생성된 쿠키 확인">
	</form>
	
</body>
</html>