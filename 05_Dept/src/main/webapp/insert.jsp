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
			<h3>DEPT 테이블 부서 추가</h3>
		<hr width="50%" color="gray">
	<form method="post" action="insertOk">
		<table border="1" cellspacing="0">
			<tr>
				<th>부서No.</th>
				<th><input type="text" name="deptNo" required></th>
			</tr>
			
			<tr>
				<th>부서명</th>
				<th><input type="text" name="deptName" required></th>
			</tr>
			
			<tr>
				<th>부서위치</th>
				<th><input type="text" name="deptLoc" required></th>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="부서추가">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>