<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(no) {
		let res = confirm("정말로 삭제하시겠습니까?");
		if(res) {
			location.href="admin_category_delete.do?cnum="+no;
		}
	}
</script>

</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
		<hr width="65%" color="marmoon">
			<h3>카테고리 리스트 페이지</h3>
		<hr width="65%" color="marmoon">
		<form>
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>No</th>
					<th>상품코드</th>
					<th>상품이름</th>
				</tr>
				<c:set var="list" value="${List }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${ dto.getNo() }</td>
							<td>${ dto.getCode() }</td>
							<td>${ dto.getName() }</td>
							<td>
								<input type="button" value="삭제" 
										onclick="check(${dto.getNo() })">
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty List }">
					<tr>
						<td colspan="4" align="center">
							<h3>조회된 카테고리가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>