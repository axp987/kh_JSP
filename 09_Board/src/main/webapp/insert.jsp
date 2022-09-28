<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>게시판 등록</h3>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/insert.do">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="all"></td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td><input type="text" width="30" name="all"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="20" cols="60" name="all"></textarea></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="all"></td>
				</tr>
				<tr>
					<td colspan = "2" align="right" style="border: none">
						<input type="submit" value="등 록">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>