<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("id", mem_id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>MEMBER10 테이블 회원 등록 폼 페이지</h3> 
		<hr width="50%" color="blue">
		<form method="post" action="<%=request.getContextPath()%>/insert.do">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<td>회원아이디</td>
					<th><input type="text" name="mem_id"></th>
				</tr>
				
				<tr>
					<td>회원이름</td>
					<th><input type="text" name="mem_name"></th>
				</tr>
				
				<tr>
					<td>회원패스워드</td>
					<th><input type="text" name="mem_pwd"></th>
				</tr>

				<tr>
					<td>나이</td>
					<th><input type="text" name="mem_age"></th>
				</tr>
				
				<tr>
					<td>마일리지</td>
					<th><input type="text" name="mem_mileage"></th>
				</tr>
				
				<tr>
					<td>직업</td>
					<th><input type="text" name="mem_job"></th>
				</tr>
				
				<tr>
					<td>주소</td>
					<th><input type="text" name="mem_addr"></th>
				</tr>
				
				<tr>
					<td>가입일</td>
					<th><input type="text" name="mem_date"></th>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="확인">
						&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>