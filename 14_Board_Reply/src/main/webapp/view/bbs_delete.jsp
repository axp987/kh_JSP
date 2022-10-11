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
			<h3>JSP_BBS 테이블 답변형 게시판 글 삭제 폼 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath() %>/bbs_delete_ok.do">
			<input type="hidden" name="bbs_no" value="${param.num }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>삭제한 게시글 비밀번호</th>
					<td>
						<input type="password" name="pwd">	
					</td>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="삭제">
						</td>
					</tr>
			</table>
		</form>
	</div>
</body>
</html>