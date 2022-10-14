<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>

	<div align="center">
		<hr width="65%" color="blue">
			<h3>관리자 로그인 페이지</h3>
		<hr width="65%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/admin_login_ok.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>관리자 아이디</th>
					<td><input type="text" name="admin_id" placeholder="아이디"></td>
				</tr>
				<tr>
					<th>관리자 비밀번호</th>
					<td><input type="password" name="admin_pwd" placeholder="패스워드"></td>
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