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
	<hr width="50%" color="gray">
		<h3>Products 테이블 제품 수정 폼 페이지</h3>
	<hr width="50%" color="gray">
	<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_product_modify_ok.do">
		<c:set var="dto" value="${list }" />
		<input type="hidden" name="all" value="${dto.getNo() }">
		<table border="1" cellspacing="0" width="85%">
				<tr>
					<th>카테고리 코드</th>
					<td>
						<input name="all" readonly value="${dto.getCode() }">
					</td>
				</tr>
				
				<tr>
					<th>제품명</th>
					<td>
						<input name="all" value="${dto.getName() }">
					</td>
				</tr>
				
				<tr>
					<th>제조사</th>
					<td>
						<input name="all" readonly value="${dto.getCompany() }">
					</td>
				</tr>
				
				<tr>
					<th>제품 이미지</th>
					<td>
						<img src="<%=request.getContextPath() %>/image/${dto.getImage()}" width="100" height="80">
						<input type="file" name="p_image_new">
						<%-- 이미지를 수정하지 않고 그대로 제품수정 버튼을 누르면 상품 등록 때 입력했던 제품의 이미지를 그대로 사용하여
							히든으로 넘겨줄 예정 --%>
						<input type="hidden" name="p_image_old" value="${dto.getImage() }">
					</td>
				</tr>
				
				<tr>
					<th>제품 수량</th>
					<td>
						<input type="number" name="all" min="1" max="100" value="${dto.getQty() }">
					</td>
				</tr>
				
				<tr>
					<th>제품 가격</th>
					<td>
						<input name="all" value="${dto.getQty() }">
					</td>
				</tr>
				
				<tr>
					<th>제품 사양</th>
					<td>
						<select name="all">
							<option value="none" <c:if test="${dto.getSpec() == 'none' }" /> selected>일반</option>
							<option value="hit" <c:if test="${dto.getSpec() == 'hit'}" /> selected>인기</option>
							<option value="new" <c:if test="${dto.getSpec() == 'new'}" /> selected>신상</option>
							<option value="recommand" <c:if test="${dto.getSpec() == 'recommand'}" /> selected>신상</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>제품 소개</th>
					<td>
						<textarea rows="7" cols="25" name="all">${dto.getCont() }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>제품 포인트</th>
					<td>
						<input name="all" value="${dto.getPoint() }">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제품수정">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
		</table>
	</form>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>