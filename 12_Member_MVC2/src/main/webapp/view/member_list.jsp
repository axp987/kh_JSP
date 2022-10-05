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
			<hr width="50%" color="red">
				<h3>MEMEBER10 테이블 전체 회원 리스트</h3>
			<hr width="50%" color="red">
			<br>
			<form>
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>가입일</th>
				</tr>
				<c:set var="list" value="${List }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr onclick="location.href='<%=request.getContextPath()%>/content.do?num=${dto.getNum() }'">
							<td>${dto.getNum() }</td>
							<td>${dto.getMemid() }</td>
							<td>${dto.getMemname() }</td>
							<td>${dto.getDate().substring(0, 10) }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="4" align="center">
							<h3>조회된 리스트가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="회원 추가" onclick="location.href='insert.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>