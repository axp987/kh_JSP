<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardDTO update = (BoardDTO)request.getAttribute("update"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h2>게시물 수정 페이지</h2>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<input type="hidden" name="all" value="<%=update.getNo()%>">
			<table width="800">
				<tr>	
					<th>작성자</th>
					<td><input type="text" name="all" value=<%=update.getWriter() %> required></td>
				</tr>

				<tr>	
					<th>
						<select name="all">
							<option value="10">공지사항</option>
							<option value="20">질 문</option>
							<option value="30">자유게시판</option>
							<option value="40">신 고</option>
						</select>
						제목
					</th>
					<td><input type="text" name="all" value=<%=update.getTitle() %> required></td>
				</tr>
				
				<tr>	
					<th>내 용</th>
					<td><textarea name="all" cols="75" rows="10"><%=update.getCont() %></textarea></td>
				</tr>
				
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="all" required></td> 
				<tr>
					<td colspan="2" align="center"><input type="submit" value="등 록">
						&nbsp;&nbsp;
												<input type="button" value="취 소" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>