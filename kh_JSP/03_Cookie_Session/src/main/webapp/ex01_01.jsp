<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {
			out.println("cookies[" + i + "] name : " + cookies[i].getName() + "<br>");
			// 쿠키 이름?
			
			out.println("cookies[" + i + "] value : " + cookies[i].getValue() + "<br>");
			// 쿠키의 값
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

</body>
</html>