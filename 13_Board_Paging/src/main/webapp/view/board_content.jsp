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
		<c:set var="dto" value="${Cont }" />
		<hr width="50%" color="tomato">
			<h3>${dto.getWriter() }님의 게시물 상세 페이지</h3>
		<hr width="50%" color="blue">
		<form method="post" action="">
			<table border="1" cellspacing="0" width="500">
			<c:if test="${!empty dto }">		
				<tr>
					<th>작성자</th>
					<td>${dto.getWriter() }</td>
				</tr>
				
				<tr>
					<th>글제목</th>
					<td>${dto.getTitle() }</td>
				</tr> 
				
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="50" readonly>${dto.getCont() }</textarea></td>
				</tr> 
				
				<tr>
					<th>패스워드</th>
					<td>
						<c:if test="${dto.getPwd().length() != 0 }">
							<c:forEach begin="1" end="${dto.getPwd().length() }">
								*
							</c:forEach>
						</c:if>
					</td>
				</tr> 
				
				<tr>
					<th>조회수</th>
					<td>${dto.getHit() }</td>
				</tr> 
				<tr>
					<c:if test="${empty dto.getUpdate() }">
						<th>작성일자</th>
						<td>${dto.getDate() }</td>
					</c:if>
					
					<c:if test="${!empty dto.getUpdate() }">
						<th>작성일자</th>
						<td>${dto.getUpdate() }</td>
					</c:if>
				</tr>
			</c:if>
				<c:if test="${empty dto }">
					<tr>
						<td colspan="2" align="center">
							<h3>조회된 게시물이 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정" onclick="location.href='board_update.do?num=${dto.getNo()}&page=${Page }'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')) {
											location.href='board_delete.do?num=${dto.getNo()}&page=${Page }'
											} else { return; }">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="◀" onclick="location.href=''">
						<input type="button" value="▶">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>