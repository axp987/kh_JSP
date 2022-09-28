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
	//function showPopup() { 
		//window.open("pwCheck.jsp", "a", "width=250, height=170, left=100, top=50"); 
	//}
</script>
<style>
 #main {
 	text-decoration: none;
 	color: black;
 }
 
 #main:hover {
 	color: skyblue;
 }
</style>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<a href="select.do" id="main" ><h3>게시판</h3></a>
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
					<th>비밀번호</th>
					<td>
						<%
							if(contentList.getPwd().length() != 0) {
								for(int i=0; i<contentList.getPwd().length(); i++) {
						%>
												*
						<%
								}
							}	
						%>
						
					</td>
				</tr>
				
				<tr>
					<th>작성일자</th>
					<td><%=contentList.getDate().substring(0, 10) %></td>
				</tr>
				
				<tr>
					<td colspan = "2" align="right" style="border: none">
						<input type="button" id="btnin" value="수 정 하 기(미완)" onclick=showPopup()>
						<input type="button" value="수 정 하 기" onclick="location.href='update2.do?num=<%=contentList.getNo()%>'">
						<input type="button" value="삭 제 하 기" onclick="if(confirm('정말로 삭제하시겠습니까?')) 
													{ location.href='delte.do?num=<%=contentList.getNo()%>' } 
													else { return }">
						<input type="button" value="뒤 로 가 기" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>