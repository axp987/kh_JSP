<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)request.getParameter("id").trim();	// 공백 제거 메소드
	String userPw = (String)request.getParameter("pw").trim();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>
			입력받은 아이디: <%=userId %>
			<br>
			입력받은 패스워드: <%=userPw %>
		</h2>
	</div>
</body>
</html>