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
		<hr width="50%" color="gray">
			<h3>UPLOAD 테이블 게시글</h3>
		<hr width="50%" color="gray">
		<form method="post" action="upload_delete_ok.do">
			<input type="hidden" name="num" value="${param.num }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>
						삭제할 게시글 비밀번호
					</th>
				</tr>
				<tr>
					<td>
						<input type="password" name="pwd">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="삭제">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="location.href='upload_content.do?num=${param.num}'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>