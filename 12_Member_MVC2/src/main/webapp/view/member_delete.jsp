<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lightgray">
			<h3>Memeber10 테이블 회원 삭제 폼 페이지</h3>
		<hr width="50%" color="lightgray">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/delete_ok.do">
			<input type="hidden" value="${No }" name="num">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>비밀번호를 입력해주세요</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원삭제">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
			</table>
		</form>
	</div>
</body>
</html>