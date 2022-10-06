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
		<c:set var="dto" value="${modify }" />
		<hr width="50%" color="lightgray">
			<h3>${dto.getBoard_writer() } 님 게시물 수정 폼 페이지</h3>
		<hr width="50%" color="lightgray">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/board_modify">
			<input type="hidden" name="board_no" value="${dto.getNo() }">
			<input type="hidden" name="db_pwd" value="${dto.getPwd() }">
			<input type="hidden" name="page" value="${Page() }">
			<table border="1" cellspcing="0" width="400">
				<tr>
					<th>작성자</th>
					<td>
						<input name="writer" value="${dto.getWriter() }">
					</td>
				</tr>
				
				<tr>
					<th>글제목</th>
					<td>
						<input name="title" value="${dto.getTitle() }">
					</td>
				</tr>
				
				<tr>
					<th>글 내용</th>
					<td>
						<textarea rows="7" cols="25" name="area">${dto.getCont }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>글 비밀번호</th>
					<td>
						<input name="pwd" type="password" value="${dto.getPwd() }">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="수정 완료">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
		
	</div>
</body>
</html>