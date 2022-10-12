<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center" class="input-group mb-3">
		<hr width="50%" color="marmoon">
			<h3>UPLOAD 테이블 자료실 게시판 상세정보</h3>
		<hr width="50%" color="marmoon">
		<%-- 
			enctype = "multipart/form-data"
			모든 문자를 인코딩하지 않음을 명시함.
			이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함
		--%>
		<c:set var="dto" value="${upCont }" />
		<form method="post" action="">
			<table border="1" cellspacing="0" width="600">
			<c:if test="${!empty dto }" >
				<tr>
					<th>제목</th>
					<td>${dto.getTitle() }</td> 
				</tr>
				
				<tr>
					<th>작성자</th>
					<td>${dto.getWriter() }</td>
				</tr>
				
				<tr>
					<th>파일첨부</th>
					<c:if test="${!empty dto.getFile() }" >
						<td><a href="<%=request.getContextPath() %>/upload/${dto.getFile() }" target="blank">${dto.getFile() }</a></td>					
					</c:if>
					<c:if test="${empty dto.getFile() }" >
						<td>파일이 없습니다.</td>					
					</c:if>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="7" cols="25" name="cont" readonly>${dto.getCont() }</textarea>
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getHit() }</td>
				</tr>
				
				<tr>
					<th>패스워드</th>
					<td>
						<c:forEach begin="1" end="${dto.getPwd().length() }">
							*
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<c:if test="${empty dto.getUpdate() }">
						<td>${dto.getDate() }</td>
					</c:if>
					<c:if test="${!empty dto.getUpdate() }">
						<td>${dto.getUpdate() }</td>
					</c:if>
				</tr>
				
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td>
						<h3>상세정보를 표시할 수 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글수정" onclick="location.href='upload_modify.do?num=${dto.getNo()}'">
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제" onclick="if(confirm('정말로 삭제할꺼야?')) {
															location.href='upload_delete.do?num=${dto.getNo()}'
															} else { return }">
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="전체목록" onclick="location.href='upload_list.do'">
					
				</td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>