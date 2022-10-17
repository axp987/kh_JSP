<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="65%" color="blue">
			<h3>사용자 로그인 페이지</h3>
		<hr width="65%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/user_login_ok.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>사용자 아이디</th>
					<td><input type="text" name="user_id" placeholder="아이디"></td>
				</tr>
				<tr>
					<th>사용자 비밀번호</th>
					<td><input type="password" name="user_pwd" placeholder="패스워드"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">
						<input type="button" value="뒤로가기" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>