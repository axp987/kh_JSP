<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<style type="text/css">
	.center {
		text-align: center;
	}
</style>
</head>
<body>
	<jsp:include page="../include/user_top.jsp" />
	<table border="1" cellspacing="0" width="650" align="center">
		<tr>
			<td colspan="7" align="center">
				<h3>장바구니 현황</h3>
			</td>
		</tr>
		<tr>
			<th width="10%">주문번호</th>
			<th width="10%">상품번호</th>
			<th width="15%">상품명</th>
			<th width="12%">수량</th>
			<th width="15%">단가</th>
			<th width="15%">합계액</th>
			<th width="10%">삭제</th>
		</tr>
		<c:set var="list" value="${cartList }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.getNum() }</td>
					<td>${dto.getPnum() }</td>
					<td>
						<img src="<%=request.getContextPath() %>/image/${dto.getImage()}" width="50%" height="50%">
						<br>
						${dto.getName() }
					</td>
					<td>${dto.getQty() }</td>
					<td>
						<fmt:formatNumber value="${dto.getPrice() }" />원
					</td>
					<td>
						<c:set var="danga" value="${dto.getPrice() }" />
						<c:set var="amount" value="${dto.getQty() }" />
						<fmt:formatNumber value="${danga * amount }" />원
					</td>
					<td>
						<a href="<%=request.getContextPath() %>/user_cart_delete.do?num=${dto.getNum() }">[삭제]</a>
					</td>
					
					<c:set var="total" value="${total + (danga * amount) }" />
				</tr>
			</c:forEach>
				<tr>
					<td colspan="4" align="right">
						<b><font color="red">장바구니 총액: <fmt:formatNumber value="${total }" />원</font></b>
					</td>
					<td colspan="3" align="center">
						<a href="#">[결제하기]</a>
						&nbsp;&nbsp;&nbsp;
						<a href="javascript:history.go(-2)">뒤로가기</a>
					</td>
				</tr>
		</c:if>
		<c:if test="${empty list }">
			<tr>
				<td colspan="7" align="center">
					<h3>장바구니에 아무것도 없어요</h3>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>