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
			<h3>JSP_BBS 테이블 답변형 게시판 수정 폼 페이지</h3>
		<hr width="50%" color="blue">
		<br>
		<c:set var="list" value="${List }" />
		<form method="post" action="<%=request.getContextPath()%>/bbs_modify_ok.do">
			<input type="hidden" name="all" value="${list.getNo() }">
			<table border="1" cellspacing="0" width="350">
				
				<c:if test="${!empty list }">
					<tr>
						<th>작성자</th>
						<td>
							${list.getWriter() }
						</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td><input type="text" value="${list.getTitle() }" name="all"></td>
					</tr>
					<tr>
						<th>글내용</th>
						<td>
							<textarea rows="7" cols="25" name="all">${list.getCont() }</textarea>
						</td>
					</tr>
					<tr>
						<th>글 비밀번호</th>
						<td>
							<input type="password" name="all">
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
						<input type="submit" value="수정 완료">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>