<%@page import="com.shop.model.CategoryDTO"%>
<%@page import="com.shop.model.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	CategoryDAO dao = CategoryDAO.getInstance();
	List<CategoryDTO> list = dao.getCategoryList();
	request.setAttribute("List", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
			<hr width="65%">
			<table border="1" cellspacing="0" width="800">
				<tr>
					<td colspan="2" align="center">
						최상단<br>
						<a href="<%=request.getContextPath() %>/user_main.do">쇼핑몰 홈</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=request.getContextPath() %>/user_cart_list.do">장바구니</a>
						&nbsp;&nbsp;&nbsp;
						<a href="#">${username }님 환영합니다.</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=request.getContextPath() %>/user_logout.do">로그아웃</a>
					</td>
				</tr>
				<tr>
					<td width="350" align="center" valign="top">
						
						<table cellspacing="0">
							<c:set var="list" value="${List }" />
							<tr>
								<td>카테고리 코드</td>
							</tr>	
							<c:if test="${!empty list }">
								<c:forEach items="${list }" var="dto">
									<tr>
										<td>
											<a href="<%=request.getContextPath() %>/user_category_list.do?code=${dto.getCode()}">
											${dto.getName() }[${dto.getCode() }]
											</a>
										</td>										
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</td>
					<%-- 이하 여역은 본문 영역이 됨(현재 여기는 top) --%>
					<td>
			