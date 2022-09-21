<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//int deptno = Integer.parseInt(request.getParameter("deptno")); // String 반환
	int de = Integer.parseInt(request.getParameter("deptno"));
	//int no = (int)request.getAttribute("deptno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
			<h3>DEPT 테이블 수정 폼 페이지</h3>
		<hr width="50%" color="gray">
	<form method="post" action="updateOk">
		<table border="1" cellspacing="0">
			<tr>
				<th>부서No.</th>
				<th><input type="text" name="deptNo" value="<%=de %>" readonly></th>
			</tr>
			
			<tr>
				<th>부서명</th>
				<th><input type="text" name="reName" required></th>
			</tr>
			
			<tr>
				<th>부서위치</th>
				<th><input type="text" name="reLoc" required></th>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="부서수정">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>