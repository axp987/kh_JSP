<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardDTO content = (BoardDTO)request.getAttribute("contentList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 상세 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/update.do">
			<input type="hidden" name="all" value="<%=content.getNo() %>">
			<table width="800">
				<tr>
					<td></td>
					<th>작성자</th>
					<td><%=content.getWriter() %></td>
				</tr>
				
				<tr>	
					<th align="right">
						<select name="all">
							<option value="10">공지사항</option>
							<option value="20">질 문</option>
							<option value="30">자유게시판</option>
							<option value="40">신 고</option>
						</select>
					</th>
					<th>제목</th>
					<td><%=content.getTitle() %></td>
				</tr>
				
				<tr>
					<td></td>	
					<th valign="top">내 용</th>
					<td><textarea name="all" cols="75" rows="10" readonly><%=content.getCont() %></textarea></td>
				</tr>
				<tr>
					<td></td>
					<th>패스워드</th>
					<td><input type="password" name="all"></td>
				</tr>
				
				<tr>
					<td></td>
					<td>마지막 수정시간</td>
					<td><%=content.getUpdate().substring(2) %></td>
				</tr>
				<tr>
				
					<td></td>
					<td colspan="2" align="center">
						<input type="submit" value="수 정">
						&nbsp;&nbsp;
						<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath() %>/delete.do?num=<%=content.getNo() %>'">
						&nbsp;&nbsp;
						<input type="button" value="홈" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>