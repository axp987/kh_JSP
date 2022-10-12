<%@page import="com.products.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ProductDTO cont = (ProductDTO)request.getAttribute("modify"); 
	List<CategoryDTO> list = (List<CategoryDTO>)request.getAttribute("List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lisghtgray">
			<h3>PRODUCTS 테이블 제품 정보 수정 폼 페이지</h3>
		<hr width="50%" color="lisghtgray">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<input type="hidden" name="product_num" value="<%=cont.getPnum() %>">
			<table border="1" cellspacing="0" width=400>
				<tr>
					<th>카테고리 코드</th>
					<td>
						<select name="product_category">
							<%
								if(list.size() == 0) {
							%>
									<option value="">:::카테고리 코드 없음</option>
							<%		
								} else { // 카테고리 코드가 있는 경우
									for(int i=0; i<list.size(); i++) {
										CategoryDTO cdto = (CategoryDTO)list.get(i);
										if(cdto.getCategory_code().equals(cont.getCategory_fk())) {
							%>
										<option value="<%=cdto.getCategory_code() %>" selected>
										<%=cdto.getCategory_name() %> [<%=cdto.getCategory_code() %>]</option>
							<%
								
										} else {
							%>
										<option value="<%=cdto.getCategory_code() %>" disabled>
										<%=cdto.getCategory_name() %> [<%=cdto.getCategory_code() %>]</option>
							<%
										} //if
							%>	
							<%
									} //for
							%>
							<%		
								} // if
							
							%>
						</select>
					</td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input type="text" name="product_name" value="<%=cont.getProductName() %>"></td>
				</tr>
				
				<tr>
					<th>제품 코드</th>
					<td><input type="text" name="product_code" value="<%=cont.getEp_code_fk() %>"></td>
				</tr>
				
				<tr>
					<th>제품 입고가</th>
					<td><input type="text" name="product_input" value="<%=cont.getInput_price() %>"></td>
				</tr>
				
				<tr>
					<th>제품 출고가</th>
					<td><input type="text" name="product_output" value="<%=cont.getOutput_price() %>"></td>
				</tr>
				
				<tr>
					<th>제품 배송비</th>
					<td><input type="text" name="product_trans" value="<%=cont.getTrans_cost() %>"></td>
				</tr>
				
				<tr>
					<th>제품 마일리지</th>
					<td><input type="text" name="product_mileage" value="<%=cont.getMileage() %>"></td>
				</tr>
				
				<tr>
					<th>제품 제조사</th>
					<td><input type="text" name="product_company" value="<%=cont.getCompany() %>" readonly></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제품수정">
						&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>	
	</div>
	
</body>
</html>