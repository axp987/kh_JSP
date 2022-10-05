<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="marmoon">
			<h3>Member10 테이블 회원 등록 폼 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<c:set var="list" value="${Modify }" />
		<form method="post" action="<%=request.getContextPath() %>/modify_ok.do">
			<table border="1" cellspacing="0" width="350">
				<c:if test="${!empty list }">
					<input type="hidden" name="all" value="${list.getNum() }" />
					<input type="hidden" name="all" value="${list.getPwd() }" />
					<tr>
						<th>회원 아이디</th>
						<td><input type="text" name="all" value="${list.getMemid() }" readonly></td>
					</tr>
					
					<tr>
						<th>회원 이름</th>
						<td><input type="text" name="all" value="${list.getMemname() }"></td>
					</tr>
					
					<tr>
						<th>회원 비밀번호</th>
						<td><input type="password" name="all"></td>
					</tr>
					
					<tr>
						<th>회원 나이</th>
						<td><input type="number" name="all" value="${list.getAge() }"></td>
					</tr>
					
					<tr>
						<th>회원 마일리지</th>
						<td><input type="number" name="all" value="${list.getMileage() }"></td>
					</tr>
					
					<tr>
						<th>회원 직업</th>
						<td><input type="text" name="all" value="${list.getJob() }"></td>
					</tr>
					
					<tr>
						<th>회원 주소</th>
						<td><input type="text" name="all" value="${list.getAddr() }"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="수정">
							&nbsp;&nbsp;&nbsp;
							<input type="reset" value="다시작성">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<th>
							<h2>조회된 목록이 없습니다.</h2>
						</th>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>