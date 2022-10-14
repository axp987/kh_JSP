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
	<jsp:include page="../include/admin_top.jsp" />
		<hr width="65%" color="marmoon">
			<h3>상품등록 폼 페이지</h3>
		<hr width="65%" color="marmoon">
		<br>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_product_input_ok.do">
			<table border="1" cellspacing="0" width="400">
				<c:set var="list" value="${categoryList }" />
				<tr>
					<th>상품명</th>
					<td>
						<input name="all">
					</td>
				</tr>
				<tr>
					<th>카테고리 코드</th>
					<td>
						<select name="all">
								<c:if test="${empty list }">
									<option value="">:::</option>
								</c:if>
								<c:if test="${!empty list }">
									<c:forEach items="${list }" var="dto">
										<option value="${dto.getCode() }">${dto.getName() }[${dto.getCode() }]</option>
									</c:forEach>
								</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>상품 제조사</th>
					<td>
						<input name="all">
					</td>
				</tr>
				<tr>
					<th>상품 이미지</th>
					<td>
						<input type="file" name="image">
					</td>
				</tr>
				<tr>
					<th>상품 수량</th>
					<td>
						<input type="number" name="all"
							min="1" max="100" value="1">
					</td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td>
						<input name="all" maxlength="8">
					</td>
				</tr>
				<tr>
					<th>상품 사양</th>
					<td>
						<select name="all">
							<option value="none" selected>일반</option>
							<option value="hit" selected>인기</option>
							<option value="new" selected>신상</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>상품 설명</th>
					<td><textarea rows="7" cols="35" name="all"></textarea>
				</tr>
				
				<tr>
					<th>상품 포인트</th>
					<td>
						<input name="all">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="상품등록">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기회">
					</td>
				</tr>
			</table>
		</form>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>