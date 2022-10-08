<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% ProductDTO list = (ProductDTO)request.getAttribute("content"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="skyblue">
			<h3>제품 정보</h3>
		<hr width="50%" color="skyblue">
		<table border="1" cellspacing="0" width="400">
			<%
				if(list.equals(null)) {
			%>
					<tr>
						<td><h3>조회된 목록이 없습니다.</h3></td>
					</tr>
			<%
				} else {
			%>
			<tr>
				<th>제 품 No</th>
				<td><%=list.getPnum() %></td>
			</tr>
			
			<tr>
				<th>제 품 명</th>
				<td><%=list.getProductName() %></td>
			</tr>
			
			<tr>
				<th>입 고 가</th>
				<td><%=list.getInput_price() %></td>
			</tr>
			
			<tr>
				<th>출 고 가</th>
				<td><%=list.getOutput_price() %></td>
			</tr>
			
			<tr>
				<th>배 송 비</th>
				<td><%=list.getTrans_cost() %></td>
			</tr>
			
			<tr>
				<th>마 일 리 지</th>
				<td><%=list.getMileage() %></td>
			</tr>
			
			<tr>
				<th>카테고리</th>
				<td><%=list.getCategory_fk() %></td>
			</tr>
			
			<tr>
				<th>제 조 사</th>
				<%
					if(list.getCompany() == null) {
				%>
					<td>-</td>
				<%		
					} 
					else {
				%>	
					<td><%=list.getCompany() %></td>
				<%
					}
				%>
			</tr>
			
			<tr align="center">
				<td colspan="2">
					<input type="button" value="뒤로가기"
					onclick="history.back()">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>
					<input type="button" value="제품수정" onclick="location.href='update.do?num=<%=list.getPnum() %>'">
					&nbsp;&nbsp;
					<input type="button" value="제품삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')) {
						location.href='delete.do?num=<%=list.getPnum() %>'				
						} else { return; }
					">
					&nbsp;&nbsp;
					<input type="button" value="제품목록" onclick="location.href='select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>