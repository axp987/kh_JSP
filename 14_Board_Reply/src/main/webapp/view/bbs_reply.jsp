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
		<hr width="50%" color="blue">
			<h3>JSP_BBS 테이블 답변형 게시판 답글 폼 페이지</h3>
		<hr width="50%" color="blue">
		<br>
		<c:set var="list" value="${reply }" />
		<form method="post" action="<%=request.getContextPath()%>/bbs_reply_ok.do">
			<input type="hidden" name="reply_all" value="${list.getNo() }">
			<input type="hidden" name="reply_all" value="${list.getGroup() }">
			<input type="hidden" name="reply_all" value="${list.getStep() }">
			<input type="hidden" name="reply_all" value="${list.getIndent() }">
			<table border="1" cellspacing="0" width="350">
				
				<c:if test="${!empty list }">
					<tr>
						<th>작성자</th>
						<td>
							<input type="text" name="reply_all" value="${list.getWriter() }">
						</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td><input type="text" name="reply_all" value="(Re)${list.getTitle() }" name="reply_all"></td>
					</tr>
					<tr>
						<th>글내용</th>
						<td>
							<textarea rows="7" cols="25" name="reply_all">${list.getCont() }</textarea>
						</td>
					</tr>
					<tr>
						<th>글 비밀번호</th>
						<td>
							<input type="password" name="reply_all">
						</td>
					</tr>
					
					<tr>
						<th>등록일</th>
						<td>
							${list.getDate() }
						</td>
					</tr>
					
					<tr>
						<th>수정일</th>
						<td>
							<c:if test="${empty list.getUpdate() }">
									${list.getDate()}
							</c:if>
							<c:if test="${!empty list.getUpdate() }">
								${list.getUpdate() }
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td>
							<h3>수정 정보를 출력할 수 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="답변 추가">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>