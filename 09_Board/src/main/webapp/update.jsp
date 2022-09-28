<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardDTO updateList = (BoardDTO)request.getAttribute("UpdateContent"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 수정 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do?num=<%=updateList.getNo() %>">
			<input type="hidden" name="num" value="<%=updateList.getNo() %>">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="all" value=<%=updateList.getWriter() %>></td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td><input type="text" name="all" value=<%=updateList.getTitle() %>></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="20" cols="60" name="all"><%=updateList.getCont() %></textarea></td>
				</tr>
				
				<tr>
					<th>패 스 워 드</th>
					<td><input type="password" name="pw" value=""></td>
				</tr>
				
				<tr>
					<td colspan = "2" align="right" style="border: none">
						<input type="submit" value="수 정 완 료">
						&nbsp;&nbsp;
						<input type="button" value="돌 아 가 기" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>