<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
			<h3>DEPT ���̺� �μ� �߰�</h3>
		<hr width="50%" color="gray">
	<form method="post" action="insertOk">
		<table border="1" cellspacing="0">
			<tr>
				<th>�μ�No.</th>
				<th><input type="text" name="deptNo" required></th>
			</tr>
			
			<tr>
				<th>�μ���</th>
				<th><input type="text" name="deptName" required></th>
			</tr>
			
			<tr>
				<th>�μ���ġ</th>
				<th><input type="text" name="deptLoc" required></th>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="�μ��߰�">
					<input type="reset" value="�ٽ��ۼ�">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>