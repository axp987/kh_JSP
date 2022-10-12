<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//ex04.jsp 페이지에서 넘어온 데이터들을 받아 주어야 한다.
	// trim(): 맨 앞과 맨 뒤의 공백을 제거해주는 메소드
	// String
	String pw = request.getParameter("pwd").trim();
	String name = request.getParameter("name").trim();
	String phone = request.getParameter("phone").trim();
	String addr = request.getParameter("addr");
	String []hobbys = request.getParameterValues("hobby");
	String position = request.getParameter("position");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>가입 회원 정보</h2>
		
		<table border="1" cellspacing="0">
			<tr>
				<th>아이디</th>
				<td><%=request.getParameter("id").trim() %></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><%= %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=pw %></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><%=phone %></td>
			</tr>
			
			<tr>
				<th>주소</th>
				<td><%=addr %></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><%=hobbys %></td>
			</tr>
			<tr>
				<th>거주지</th>
				<td><%=position %></td>
			</tr>
		</table>
	</div>
</body>
</html>