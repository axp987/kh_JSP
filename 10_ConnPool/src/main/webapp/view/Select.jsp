<%@page import="com.board1.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("select"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("#cl").change(function() {
			let va = this.value;
			location.href= "<%=request.getContextPath()%>/selectCh.do?val=" + va;
			$("#cl option").index($("#cl option:selected"));
		});
		
		// 페이지가 새로고침되서 selected 도 같이 없어지는듯
	});
</script>
<body>
<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 페이지</h3>
		<hr width="50%" color="blue">
		<form>
			<table border="1" cellspacing="0" width="800">
				<tr>
					<td colspan="5" align="center">
						<select id="cl" name="cl">
							<option>선택</option>
 						<%
							//if(list.size() == 0) {
								//for(int i=0; i<list.size(); i++) {
								//BoardDTO dto = list.get(i);
								//if() // 쿼리문써서 가져와야함 ㅋㅋ
						%>
									
						<%		
								//}
							//}
						%>

							<option value="10">공지사항</option>
							<option value="20">질 문</option>
							<option value="30">자유게시판</option>
							<option value="40">신 고</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>No</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<%
					if(list.size() == 0) {
				%>
						<tr>
							<td colspan="5" align="center">조회된 목록이 없습니다.</td>
						<tr>
				<%
					} else {
						for(int i=0; i<list.size(); i++) {
							BoardDTO dto = list.get(i);
				%>
							<tr onclick="location.href='<%=request.getContextPath() %>/content.do?num=<%=dto.getNo() %>'">
								<td><%=dto.getNo() %></td>
								<td><%=dto.getWriter() %></td>
								<td><%=dto.getTitle() %></td>
								<td><%=dto.getHit() %></td>
								<td><%=dto.getDate().substring(0, 10) %></td>
							</tr>
				<%
						}
					} //if
				%>
				<tr>
					<td colspan="5" align="right">
						<input type="button" value="게시글 작성" onclick="location.href='view/insert.jsp'">
					</td>
				</tr>
			</table>
			<br>
			<hr width="70%" color="red">
			<br> 
		</form>
			<form method="post" action="<%=request.getContextPath() %>/search.do">
				<!-- <select name="find_field"> -->
				<select name="find">
					<%-- String find_field = cont --%>
					<option value="title">제목</option>
					<option value="cont">내용</option>
					<option value="title_cont">제목+내용</option>
					<option value="writer">작성자</option>
				</select>
				<!-- <input type="text" name="find_name"> -->
				<input type="text" name="find_Object">
				<input type="submit" value="검색">
			</form>
	</div>
</body>
</html>