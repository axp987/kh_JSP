<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardDTO contentList = (BoardDTO)request.getAttribute("content"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("#btnin").on("click", function() {
			let pwd = prompt("패스워드를 입력하세요.");
		});
	});
</script>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="">
			<table border="1" cellspacing="0">
				<tr>
					<th>No.</th>
					<td><%=contentList.getNo() %></td>
				</tr>
			
				<tr>
					<th>작성자</th>
					<td><%=contentList.getWriter() %></td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td><%=contentList.getTitle() %></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="20" cols="60" name="point" readonly><%=contentList.getCont() %></textarea></td>
				</tr>
				
				<tr>
					<td colspan = "2" align="right" style="border: none">
						<input type="button" id="btnin" value="수 정 하 기" onclick="location.href='<%=request.getContextPath()%>/password.do?pw=<%=contentList.getNo() %>'">
						<input type="button" value="뒤 로 가 기" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>