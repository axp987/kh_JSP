<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String req_param = request.getParameter("param").trim();
	System.out.println("요청 param >>> " + req_param);
	
	// 응답이 필요하다
	out.println("AJAX 요청에 대답 응답입니다.");
	
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