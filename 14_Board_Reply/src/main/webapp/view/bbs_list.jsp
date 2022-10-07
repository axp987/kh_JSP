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
		<table border="1" cellspacing="0" width="650">
			<tr>
				<th>글번호</th>
				<th>글작성자</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
				<th>Group</th>
				<th>Step</th>
				<th>Indent</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>${dto.getWriter() }</td>
						<td>${dto.getTitle() }</td>
						<td>${dto.getHit() }</td>
						<td>${dto.getDate().substring(0, 10) }</td>
						<td>${dto.getGroup() }</td>
						<td>${dto.getStep() }</td>
						<td>${dto.getIndent() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="7" align="center">
						<h3>조회된 목록이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="8" align="center">
					<input type="button" value="글쓰기" onclick="location.href='bbs_write.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>