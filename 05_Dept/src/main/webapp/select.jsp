<%@page import="com.khie.model.*"  %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");
	// request�� ��ȯ���� Object
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>DEPT ���̺� ��ü ����Ʈ ���</h3>
		<hr width="50%" color="red">
		<br> <br>
		<table border="1" cellspacing="0">
			<tr>
				<th>�μ�No.</th>
				<th>�μ���</th>
				<th>�μ���ġ</th>
				<th>�μ�����</th>
			</tr>
			<%
				if(dept.size() != 0) { // �����Ͱ� dept�� ���� ��
					// ������ ����ŭ �ݺ��ؼ� ȭ�鿡 ���
					for(int i=0; i<dept.size(); i++) {
						DeptDTO dto = dept.get(i); // ����
			%>
			<tr>
				<td><%=dto.getDeptno() %></td>
				<td><%=dto.getDname() %></td>
				<td><%=dto.getLOC() %></td>
			</tr>
			<%			
					} // for���� end
				} //if��
				else { %>
					<tr>
						<td colspan="4" align="center">
						<h3>�˻��� �����Ͱ� �����ϴ�.</h3>
						</td>
					</tr>
			<%
				}
			%>
					<tr>
						<td colspan="4" align="right">
							<input type="button" value="�μ�����" onclick="location.href='insert.jsp'">
						</td>
					</tr>
		</table>
	</div>
</body>
</html>