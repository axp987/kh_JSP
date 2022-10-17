<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#title {
		float:right;
		margin-right: 300px;
		word-spacing: 20px;
		clear: both; 
	}
	
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<div align="center">
		<hr width="65%">
			<h2>관리자 페이지</h2>
			<div id="title">
				<a href="#">${adminName }님 환영합니다.</a>
				<a href="<%=request.getContextPath() %>/admin_logout.do">로그아웃</a>
			</div>
			
			
			<hr width="65%">
			<table width="800">
				<tr>
					<td>
						<a href="<%=request.getContextPath() %>/admin_main.do">관리자 홈</a>
					</td>
				
					<td>
						<a href="<%=request.getContextPath() %>/admin_category_input.do">카테고리 등록</a>
					</td>
				
					<td>
						<a href="<%=request.getContextPath() %>/admin_category_list.do">카테고리 목록</a>
					</td>
					
					<td>
						<a href="<%=request.getContextPath() %>/admin_product_input.do">상품 등록</a>
					</td>
					
					<td>
						<a href="<%=request.getContextPath() %>/admin_product_list.do">상품 목록</a>
					</td>
				</tr>
			</table>
			
		<hr width="65%">
		<br>