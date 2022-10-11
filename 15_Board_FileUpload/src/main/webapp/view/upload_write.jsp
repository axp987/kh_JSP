<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" class="input-group mb-3">
		<hr width="50%" color="marmoon">
			<h3>UPLOAD 테이블 자료실 게시판 글쓰기</h3>
		<hr width="50%" color="marmoon">
		<%-- 
			enctype = "multipart/form-data"
			모든 문자를 인코딩하지 않음을 명시함.
			이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함
		--%>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload_write_ok.do">
			<table width="400">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td> 
				</tr>
				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="write"></td>
				</tr>
				
				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="upload_file"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="7" cols="25" name="cont"></textarea>
					</td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<th colspan="2" align="center">
						<input type="submit" value="추가">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>