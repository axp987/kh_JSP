<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�μ�(DEPT) ���̺�</title>

</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>DEPT ���̺� ���� ������</h3>
		<hr width="50%" color="blue">
		<br> <br>
		<%--
			request.getContextPath()
			: ���� ������Ʈ���� ���ڿ��� ��ȯ�� �ִ� �޼ҵ�
		 --%>
		<a href="<%=request.getContextPath() %>/select">[��ü �μ� ���]</a>
		<!-- /select�� SelectServlet�� �ǹ� -->
	</div>


</body>
</html>