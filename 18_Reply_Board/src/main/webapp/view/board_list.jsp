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
		<hr width="50%" color="marmoon">
			<h3>TBL_BOARD 테이블 게시판 전체 리스트 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>글작성일자</th>
				<th>수정일자</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>조회된 목록이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr onclick="location.href='<%=request.getContextPath() %>/board_content.do?num=${dto.getBno()}'">
						<td>${dto.getBno() }</td>
						<td>${dto.getTitle() }</td>
						<td>${dto.getRegdate().substring(0, 10) }</td>
						<c:if test="${empty dto.getRegupdate() }">
							<td> </td>
						</c:if>
						<c:if test="${!empty dto.getRegupdate() }">
							<td>${dto.getRegupdate.substring(0, 10)}</td>
						</c:if>
					</tr>
				</c:forEach>
				
			</c:if>
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="글쓰기" onclick="location.href='board_write.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>