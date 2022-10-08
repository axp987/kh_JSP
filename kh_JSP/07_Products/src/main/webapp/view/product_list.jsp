<%@page import="com.products.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<ProductDTO> product = (List<ProductDTO>)request.getAttribute("pList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.hide {
		
	}
</style>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function () {
			$(".hide").hide();
		
			$("#deletebtn").on("click", function() {
				$(".hide").toggle();
				
			});
	});
</script>
</head>
<body>
	<div align="center">
		<hr width="50%" color ="">
			<h2>전체 제품 목록</h2>
		<hr width="50%" color ="">
		<form>
			<table border="1" cellspacing="0">
				<tr>
					<td>제품명No.</td>
					<td>카테고리Code</td>
					<td>제품명</td>
					<td>제조사</td>
					<td class="hide">선 택</td>
				</tr>
				<%
					if(product.size() != 0) {
						for(int i=0; i<product.size(); i++) {
							ProductDTO dto = product.get(i);
				%>
							<tr>
								<td><%=dto.getPnum() %></td>
								<td><%=dto.getCategory_fk() %></td>
								
								<td><a href="<%=request.getContextPath() %>/content.do?num=<%=dto.getPnum() %>">
									<%=dto.getProductName() %></a>
								</td>
								<%
									if(dto.getCompany() == null) {
								%>
									<td>-</td>
								<%		
									} else {
								%>
										<td><%=dto.getCompany() %></td>	
								<%
									}
								%>
								<td class="hide" align="center">
									<input type="button" value="X" 
										onclick="location.href='<%=request.getContextPath() %>/delete.do?num=<%=dto.getPnum() %>'">
								</td>
							</tr>
				<%			
						}
				%>
				<%
					} else {
				%>
						<tr colspan="4">
							<td><h2>조회된 제품이 없습니다.</h2></td>
						</tr>
				<%
					}
				%>
				<tr>
					<td colspan="5" align="right">
						<input type="button" value="제품 추가" 
							onclick="location.href='<%=request.getContextPath() %>/insert.do'">
						<!-- <input type="button" id="deletebtn" class="bn" value="제품 삭제"> -->
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>