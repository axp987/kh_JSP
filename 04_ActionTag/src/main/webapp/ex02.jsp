<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<%-- <%@ include file="./include/header.jsp" %> --%>
	<jsp:include page="./include/header.jsp" />
		<h2 align="center">본문 JSP 페이지 입니다....</h2>
		
	<hr width="50%" color="red">
	<%-- <%@ include file="./include/footer.jsp" %> --%>
	<jsp:include page="./include/footer.jsp" />
</div>
</body>
</html>