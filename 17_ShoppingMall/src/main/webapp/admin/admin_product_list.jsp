<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
		<hr width="65%" color="marmoon">
			<h3>SHOP_PRODUCTS 리스트 페이지</h3>
		<hr width="65%" color="marmoon">
		<form>
			<table border="1" cellspacing="0">
				<tr>
					<td>제품No</td>
					<td>카테고리 코드</td>
					<td>제품명</td>
					<td>이미지</td>
					<td>제품가격</td>
					<td>수량</td>
					<td>제조사</td>
					<td>제품등록일</td>
					<td>수정</td>
					<td>삭제</td>
				</tr>
				<c:set var="list" value="${List }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						
						<tr>
							<td>${dto.getNo() }</td>
							<td>${dto.getCode() }</td>
							<td>${dto.getName() }</td>
							<td>
							 	<%-- <c:out value="${dto.getImage() }"/> --%>
								<%-- <img src="<%=request.getContextPath() %>/image/${dto.getImage()}" width="50" height="8"/> --%>
								<img src="../image/${dto.getImage() }" />
							</td>
							
							<td>${dto.getPrice() }</td>
							<td>${dto.getQty() }</td>
							<td>${dto.getCompany() }</td>
							<td>${dto.getDate().substring(0, 10) }</td>
							<td>
								<a href="<%=request.getContextPath() %>/admin_product_modify.do?pnum=${dto.getNo() }">수정</a>
							</td>
							<td>
								<a href="<%=request.getContextPath() %>/admin_product_delete.do?pnum=${dto.getNo() }">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td>조회된 목록이 없습니다.</td>
					</tr>
				</c:if>
			</table>
		</form>
	<jsp:include page="../include/admin_bottom.jsp" />	
</body>
</html>