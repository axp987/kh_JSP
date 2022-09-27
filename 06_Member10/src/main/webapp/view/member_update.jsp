<%@page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% MemberDTO update = (MemberDTO)request.getAttribute("modify"); %>
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
		<br> <br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<input type="hidden" name="num" value="<%=update.getNum() %>">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>회원 아이디</th>
					<td><input type="text" name="mem_id" value="<%=update.getMemid() %>" readonly></td>
				</tr>
				
				<tr>
					<th>회원 이름</th>
					<td><input type="text" name="mem_name" value="<%=update.getMemname() %>" readonly></td>
				</tr>
				
				<tr>
					<th>회원 패스워드</th>
					<td><input type="text" name="mem_pwd"></td>
				</tr>
				
				<tr>
					<th>회원 나이</th>
					<td><input type="text" name="mem_age" value="<%=update.getAge() %>"></td>
				</tr>
				
				<tr>
					<th>회원 마일리지</th>
					<td><input type="text" name="mem_mileage" value="<%=update.getMileage() %>"></td>
				</tr>
				
				<tr>
					<th>회원 직업</th>
					<td><input type="text" name="mem_job" value="<%=update.getJob() %>"></td>
				</tr>
				
				<tr>
					<th>회원 주소</th>
					<td><input type="text" name="mem_addr" value="<%=update.getAddr() %>"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원 수정">
						&nbsp;&nbsp;
						<input type="reset" value="다시 작성">
					</td>
				</tr>
			</table>
		</form> 
	</div>
</body>
</html>