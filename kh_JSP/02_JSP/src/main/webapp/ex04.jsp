<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>회원 가입 폼 페이지</h2>
		<form method="post" action="ex04_01.jsp">
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id"></td>
				</tr>
				
				<tr>
					<th>패스워드</th>
					<td><input type="text" name="pwd"></td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td><input type="text" name="phone"></td>
				</tr>
				
				<tr>
					<th>주 소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				
				<tr>
					<th>취 미</th>
					<td>
						<input type="checkbox" name="hobby" value="여행">여행&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="독서">독서&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="운동">운동&nbsp;&nbsp;<br>
						<input type="checkbox" name="hobby" value="게임">게임&nbsp;&nbsp;
						<input type="checkbox" name="hobby" value="잠자기">잠자기
					</td>
				</tr>
				<tr>
					<th>거주지</th>
					<td>
						<select name ="position">
							<option value="선택해주세요">선택해주세요.</option>
							<option value="서울">서울</option>
							<option value="대전">대전</option>
							<option value="인천">인천</option>
							<option value="대구">대구</option>
							<option value="제주도">제주도</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입">&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>