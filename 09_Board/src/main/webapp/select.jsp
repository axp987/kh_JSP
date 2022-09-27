<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<BoardDTO> select = (List<BoardDTO>)request.getAttribute("selectList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 페이지</h3>
		<hr width="50%" color="blue">
		<form>
			<table border="1" cellspacing="0" width="800">
				<tr>
					<th>No</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<%
					if(select.size() == 0) {
				%>
						<tr>
							<td colspan="5" align="center">조회된 목록이 없습니다.</td>
						<tr>
				<%
					} else {
						for(int i=0; i<select.size(); i++) {
							BoardDTO dto = select.get(i);
				%>
							<tr onclick="location.href='<%=request.getContextPath()%>/content.do?num=<%=dto.getNo() %>'">
								<td><%=dto.getNo() %></td>
								<td><%=dto.getWriter() %></td>
								<td><%=dto.getTitle() %></td>
								<td><%=dto.getHit() %></td>
								<td><%=dto.getDate() %></td>
							</tr>
				<%				
						}
					} //if
				%>
				<tr>
					<td colspan="5" align="right">
						<input type="button" value="게시글 작성" onclick="location.href='insert.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>