<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int no = Integer.parseInt(request.getParameter("num").trim());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="marmoon">
			<h3>Board 테이블 게시글 삭제 폼 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/delete_ok.do">
			<input type="hidden" name="all" value=<%=no %>>
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 비밀번호</th>
					<td><input type="password" name = "all"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭 제">
						&nbsp;&nbsp;
						<input type="button" value="취 소" onclick="location.href='content.do?num=<%=no %>'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>