<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
</head>
<body>
	<div align="center">
		게시판 테이블
		<c:set var="list" value="${List }" />
		<form method="post" action="">
			<table border="1" cellspacing="0" class="table" width="400">
					<tr>
						<th scope="col">글번호</th>
						<th scope="col">글제목</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일자</th>
					</tr>
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
							<tr onclick="location.href='<%=request.getContextPath() %>/upload_content.do?num=${dto.getNo() }'">
								<td>${dto.getNo() }</td>
								<td>${dto.getTitle() }</td>
								<td>${dto.getHit() }</td>
								<td>${dto.getDate().substring(0, 10) }</td>
							</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="4" align="center" scope="row">
							<h3>조회된 리스트가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4" align="center" scope="row">
						<input type="button" value="추가" onclick="location.href='upload_write.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>