<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function goCart() {
		// 경로설정
		document.frm.action="<%=request.getContextPath() %>/user_cart_add.do"; 
		document.frm.submit(); // 버튼의 submit 역활
	}
</script>
</head>
<body>
	<jsp:include page="../include/user_top.jsp" />
	<table border="1" cellspacing="0" width="500">
		<c:set var="dto" value="${productCont }" />
			<c:if test="${!empty dto }">
				<tr>
					<td colspan="2" align="center">
						<b>[${dto.getName() }] 상품 정보</b>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" height="30">
				</tr>
				
				<tr>
					<td align="center">
						<img src="<%=request.getContextPath() %>/image/${dto.getImage()}" width="180" height="180">
					</td>
					<td>
						<form method="post" name="frm">
							<input type="hidden" name="all" value="${dto.getNo() }">
							<input type="hidden" name="all" value="${dto.getSpec() }">
							<input type="hidden" name="all" value="${dto.getImage() }">
							<input type="hidden" name="all" value="${userId }">
							<table border="0" cellspacing="0">
								<tr>
									<th>제품번호</th>
									<td>${dto.getNo() }</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" height="20"></td>
								</tr>
								
								<tr>	
									<th>제품 이름</th>
									<td>
										<input name="p_name" value="${dto.getName() }">
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" height="20"></td>
								</tr>
								
								<tr>	
									<th>제품 가격</th>
									<td>
										<input name="p_price" value="${dto.getPrice() }" readonly>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" height="20"></td>
								</tr>
								
								<tr>
									<th>제품 포인트: </th>
									<fmt:formatNumber value="${dto.getPoint() }" var="commaPoint"></fmt:formatNumber>
									<td>
										[${commaPoint }] 포인트
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" height="20"></td>
								</tr>
								
								<tr>	
									<th>제품 수량</th>
									<td>
										<input name="p_qty" value="${dto.getQty() }" min="1" max="50" value="1">
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" height="20"></td>
								</tr>
								<table border="0" cellspacing="0" width="90%" align="center">
									<tr>
										<td align="center">
											<a href="javascript:goCart()">
												<img src="<%=request.getContextPath() %>/image/btn_cart.png" border="0">
											</a>
										</td>
									</tr>								
								</table>
							</table>
						</form>
					</td>
				</tr>
				<tr height="250" valign="top">
					<td colspan="2">
						<br>
							<b>제품 소개</b>
							<br> <br>
							${dto.getCont() }
					</td>
				</tr>
			</c:if>
	</table> 
	<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>