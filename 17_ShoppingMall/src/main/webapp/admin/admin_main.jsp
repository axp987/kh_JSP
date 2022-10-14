<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul li {
		list-style: none;
		margin-left: 30px;
		display: inline-block;
	}
</style>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" /> <%-- <div> --%>
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin_category_input.do">쇼핑 카테고리 등록</a></li>
			<li><a href="<%=request.getContextPath() %>/admin_category_list.do">쇼핑 카테고리 목록</a></li>
			<li><a href="<%=request.getContextPath() %>/admin_product_input.do">쇼핑 상품 등록</a></li>
		</ul>
	<jsp:include page="../include/admin_bottom.jsp" /> <%-- </div> --%>
</body>
</html>