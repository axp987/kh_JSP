<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim(); */
	
	// ex02_01.jsp 페이지에서 넘어온 세션 정보 받기
	String sessionName = (String)session.getAttribute("name");
	String sessionAddr = (String)session.getAttribute("addr");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <h2>입력 폼(ex02.jsp) 페이지에서 넘어온 데이터</h2>
	<h3>
		입력 받은 아이디: <%=userId %><br>
		입력 받은 패스워드: <%=userPwd %><br>
	</h3>
	<hr> --%>
	<h2>세션으로 넘어온 데이터</h2>
	<hr>
	<h3>
		세션으로 받은 이름: <%=sessionName %>
		<br>
		세션으로 받은 주소: <%=sessionAddr %>
	</h3>
	<a href="ex02_04.jsp">다음으로</a>
</body>
</html>