<%@page import="com.member.model.vari"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	List<vari> member_list = (List<vari>)request.getAttribute("List"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form>
			<table border="1" cellspacing="0">
				<tr>
					<th>회원No</th>
					<th>회원이름</th>
					<th>회원나이</th>
					<th>회원직업</th>
					<th>회원주소</th>
					<th>회원가입일</th>
				</tr>
					<%
						if(member_list.size() != 0) {
							for(int i=0; i<member_list.size(); i++) {
								vari va = member_list.get(i);
					%>
							<tr>
						<td><%=va.getNum() %></td>
						<td><%=va.getId() %></td>
						<td>
							<a href="<%=request.getContextPath() %>/content.do?num=<%=va.getNum() %>">
							<%=va.getName() %></a>
						</td>
						<td><%=va.getJob() %></td>
						<td><%=va.getAddr() %></td>
						<td><%=va.getdate().substring(0, 10) %></td>
						
					</tr>
					<%	
							}
						} else {
					%>
						<tr>
							<td colspan="6" align="center">
							<h3>회원 목록이 없습니다...</h3>
							</td>
						</tr>	
					<%
						}
					%>
					<tr>
						<td colspan="6" align="center"><input type="button" value="회원등록"
								onclick="location.href='view/member_join.jsp'">
						</td>
			</table>
		</form>
	</div>
</body>
</html>