<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<style type="text/css">
	.pagination {
		justify-content: center;
	}
</style>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>게시물 페이지</h3>
		<hr width="50%" color="red">
		<form>
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
					<th>작성일자</th>
				</tr>
				<%-- page, rowsize, block, totalRecord, allPage, startNo, 
				endNo, startBlock, endBlock, pageList --%>
				<c:set var="list" value="${List }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.getNo() }</td>
							<td>${dto.getTitle() }</td>
							<td>${dto.getHit() }</td>
							<td>${dto.getDate() }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="4" align="center">
							<h3>검색된 목록이 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
			<br>
			<%-- 검색 페이징 처리 영역 --%>
			<%-- allArr = page, rowsize, block, totalRecord, allPage, 5=startNo, 
				endNo, startBlock, endBlock, pageList --%>
			<nav align="center">
			  <ul class="pagination">
			    <li class="page-item">
			      <a class="page-link" href="board_search.do?page=1&search_field=${field }&search_keyword=${keyword }">First</a>
			    </li>
			    
			    <li class="page-item">
				    <c:if test="${page == 1 }">
				      <a class="page-link" href="board_search.do?page=1&search_field=${field }&search_keyword=${keyword }">Previous</a>			    
				    </c:if>
				    <c:if test="${page != 1 }">
				    	<a class="page-link" href="board_search.do?page=${Page - 1 }&search_field=${field }&search_keyword=${keyword }">Previous</a>
				    </c:if>
			    </li>
			   
			    <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i==page }">
						<li class="page-item active" aria-current="page">
							<a class="page-link" href="board_search.do?page=${i }&search_field=${field }&search_keyword=${keyword }">${i }</a>
						</li>
					</c:if>
					
					<c:if test="${i!=page }">
						<li class="page-item">
							<a class="page-link" href="board_search.do?page=${i }&search_field=${field }&search_keyword=${keyword }">${i }</a>
						</li>
					</c:if> 
				</c:forEach>
				
				<li class="page-item">
					<a class="page-link" href="board_search.do?page=${Page + 1 }&search_field=${field }&search_keyword=${keyword }">Next</a>
				</li> 
				<li class="page-item">
					<a class="page-link" href="board_search.do?page=${allPage }&search_field=${field }&search_keyword=${keyword }">End</a>
				</li>
			  </ul>
			</nav>
		</form>
		
		
	</div>
</body>
</html>