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
			<h3>게시물 등록 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/board_write_Ok.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="all"></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="all"></td>
				</tr> 
				
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="50" name="all"></textarea></td>
				</tr> 
				
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="all"></td>
				</tr> 
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>