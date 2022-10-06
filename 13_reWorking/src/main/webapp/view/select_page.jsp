<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>게시물 페이지</h3>
		<hr width="50%" color="red">
		<form>
			<c:set var="list" value="${List }" />
			<table>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성시간</th>
				</tr>
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>${dto.getWriter() }</td>
						<td>${dto.getTitle() }</td>
						<td>${dto.getDate().substring(0, 10) }</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="4" align="center">
							<h3>조회된 목록이 읎어요</h3>
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>