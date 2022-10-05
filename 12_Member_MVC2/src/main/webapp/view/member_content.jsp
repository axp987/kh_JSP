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
		<form method="post" action="<%=request.getContextPath() %>/update.do">
			<c:set var="content" value="${list }" />
			<input type="hidden" name="num" value="${content.getNum() }">
			<table border="1" cellspacing="0" width="350">
				<c:if test="${!empty content }">
					<tr>
						<th>회원 아이디</th>
						<td>${content.getMemid() }</td>
					</tr>
					
					<tr>
						<th>회원 이름</th>
						<td>${content.getMemname() }</td>
					</tr>
			
					<tr>
						<th>회원 나이</th>
						<td>${content.getAge() }</td>
					</tr>
					
					<tr>
						<th>회원 마일리지</th>
						<td>
							<fmt:formatNumber>${content.getMileage() }</fmt:formatNumber>
						</td>
					</tr>
					
					<tr>
						<th>회원 직업</th>
						<td>${content.getJob() }</td>
					</tr>
					
					<tr>
						<th>회원 주소</th>
						<td>${content.getAddr() }</td>
					</tr>
					
					<tr>
						<th>가입일</th>
						<td>${content.getDate().substring(0, 10) }</td>
					</tr>
					
					<tr>
						<th>패스워드</th>
						<td>
							<c:if test="${content.getPwd().length() != 0 }">
								<c:forEach begin="1" end="${content.getPwd().length() }">
									*
								</c:forEach>
							</c:if>
							<br>
						<input type="password" name="pwd"></td>
				</c:if>
				<c:if test="${empty content }">
					<tr>
						<td colspan="4" align="center">
								<h3>조회된 리스트가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원수정(x)">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="회원수정" onclick="location.href='modify.do?num=${content.getNum()}'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="회원삭제" onclick="if(confirm('회원을 삭제하시겠습니까?')) {
																		location.href='delete.do?num=${content.getNum()}'
																		else { return; }">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="처음으로" onclick="location.href='<%=request.getContextPath()%>/select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>