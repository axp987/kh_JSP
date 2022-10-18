<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/user_top.jsp" />
	<div align="center">
		<h3>쇼핑몰에 오신 걸 환영합니다.</h3>
		<c:set var="list" value="${productList }" />
		<c:if test="${empty list }">
			<span>제품 목록이 없습니다.</span>
		</c:if>
		<c:if test="${!empty list }">
			<hr width="85%" color="tomato">
				<h3>제품 목록 리스트 페이지</h3>
			<hr width="85%" color="tomato">
			<br>
			<table border="1" cellspacing="0">
					<tr>
						<c:forEach items="${list }" var="dto">
							<c:set var="count" value="${count+1 }" />
							<td align="center">
								<a href="<%=request.getContextPath() %>/user_product_view.do?pnum=${dto.getNo()}">
									<img src="<%=request.getContextPath()%>/image/${dto.getImage() }" width="120" height="120" border="0">
								</a>
								<br>
								${dto.getName() }
								<br>
									<fmt:formatNumber value="${dto.getPrice() }" />원<br>
									<fmt:formatNumber value="${dto.getPoint() }" var="commaPoint" />[${commaPoint }]포인트<br>			
							</td>
							<c:if test="${count % 3 == 0 }">
							</tr>
							<tr>								
							</c:if>
						</c:forEach>
			</table>
		</c:if>
	</div>
	<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>