<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".submit").on("click", function() {
			let data = $("form").serialize();
			alert(data);
		});
	});
	
</script>
</head>
<body>
	<%--
		쿼리 스트링: 사용자가 웹 프로그램으로 입력한 데이터를 가장 단순하고 쉽게 전달하는 방법
	 --%>
	<div align="center">
		<form action="">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<td colspan="2" align="center">
						<h3>폼 데이터를 쿼리 스트링으로 변환</h3>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="name"></td>
				</tr>
				
				<tr>
					<th>나이</th>
					<td><input name="age"></td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td><input name="phone"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" class="submit" value="전송">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>