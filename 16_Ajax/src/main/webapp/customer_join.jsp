<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript" src="js/cus.js"></script>
<style type="text/css">
	span {
		width: 150px;
		color: red;
	}
	
	input {
		border: 1px solid gray;
	}
	
	table {
		width= "85%";
	}
	
	.table_bg {
		background-color: pink;
	}
	
	th, td {
		border: 1px solid gray;
		text-align: center;
		padding: 3px;
	}
</style>
<body>
	<div align="center">
		<hr width="85%" color="gray">
			<h3>회원 정보 입력 페이지</h3>
		<hr width="85%" color="gray">
		<br>
		<form method="post" name="inForm" id="inForm">
			<table cellspacing="0">
				<tr class="table_bg">
					<th>아이디</th>
					<th>이 름</th>
					<th>나 이</th>
					<th>연락처</th>
					<th>주 소</th>
				</tr>
				
				<tr>
					<td>
						<input name="id" id="id" size="15">
						<br>
						<span>중복결과여부</span>
					</td>
					<td>
						<input name="name" id="name" size="15">
					</td>
					
					<td>
						<input name="age" id="age" size="15">
					</td>
					
					<td>
						<input name="phone" id="phone" size="15">
					</td>
					
					<td>
						<input name="addr" id="addr" size="15">
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="button" value="가입하기" id="btn">
					</td>
				</tr>
			</table>
			<br>
			<hr>
			<h2>고객 리스트</h2>
			<table id="listTable" cellspacing="0" width="600">
				<tr>
					<td>번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>나이</td>
					<td>연락처</td>
					<td>주소</td>
					<td>삭제</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>