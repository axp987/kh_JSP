<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="orange">
			<h2>페이지 이동</h2>
		<hr width="50%" color="orange">
		<br><br>
		<h3>페이지 이동(forward)</h3>
		<form method="post" action="ex06_1.jsp">
			<p>아이디: <input type="text" name="id">
			<p>비밀번호: <input type="password" name="pwd">
			<br><br>
			<input type="submit" value="로그인"> 
		</form>
		
		<hr width="50%">
		
		<h3>페이지 이동(redirect)</h3>
		<form method="post" action="ex06_2.jsp">
			<p>아이디: <input type="text" name="id">
			<p>비밀번호: <input type="password" name="pwd">
			<br><br>
			<input type="submit" value="로그인"> 
		</form>
	</div>
</body>
</html>