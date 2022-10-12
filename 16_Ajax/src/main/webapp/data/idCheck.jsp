<%@page import="com.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("paramId").trim();

	MemberDAO dao = MemberDAO.getInstance();
	int res = dao.checkMemeberId(userId);
	
	// ajax에게 응답 해주면 됨
	out.println(res); // idCheck.jsp >> customer.jsp의 ajax에 data 변수에 들어감
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>check</title>
</head>
<body>
	
</body>
</html>