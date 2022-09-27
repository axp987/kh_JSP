<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<EmpDTO> emplist = (List<EmpDTO>)request.getAttribute("List"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="skyblue">
			<h2>사원 전체 목록</h2>
		<hr width="50%" color="skyblue">
		<form>
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>사원No</th>
					<th>이 름</th>
					<th>부서번호</th>
					<th>입사일자</th>
				</tr>
					<%
						if(emplist.size() != 0) {
							for(int i=0; i<emplist.size(); i++) {
								EmpDTO dto = emplist.get(i);
					%>
								<tr>
									<td><%=dto.getEmpno() %></td>
									<td>
										<a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getEmpno()%>">
										<%=dto.getEname() %>
										</a>
									</td>
									<td><%=dto.getDeptno() %></td>
									<td><%=dto.getDate().substring(0, 10) %></td>
								</tr>			
					<%
							}//for
						} else {
							
					%>
						<tr>
							<td><h3>조회된 목록이 없습니다.</h3></td>
						</tr>
					<%		
						} //if
					%>
					<tr>
						<td colspan="4" align="right">
							<input type="button" value="사원등록"
								onclick="location.href='<%=request.getContextPath()%>/insert.do'">
						</td>
					</tr>
			</table>
		</form>
	</div>

</body>
</html>