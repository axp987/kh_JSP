<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 한글 깨짐 방지 설정 코드 작성
	request.setCharacterEncoding("UTF-8");

	//String memid = request.getParameter("memid").trim();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- MemberDTO member = new MemberDTO(); --%>
	<jsp:useBean id="member" class="khie.MemberDTO" />
	
	<%-- member.setMemid() --%>
	<%-- <jsp:setProperty property="memid" name="member"/>
	<jsp:setProperty property="memname" name="member"/>
	<jsp:setProperty property="pwd" name="member"/>
	<jsp:setProperty property="age" name="member"/>
	<jsp:setProperty property="mileage" name="member"/>
	<jsp:setProperty property="job" name="members"/>
	<jsp:setProperty property="addr" name="members"/> --%>
	
	<!-- form 태그의 name 속성에 있는 변수명과 빈 객체(DTO) 멤버변수 이름을 비교하여 이름이 동일시 아래 구문과 같이
		*(와일드카드)를 사용하여 해당 JavaBean의 멤벼변수에 자동으로 값을 설정 -->
	<jsp:setProperty property="*" name="member"/>
	
	<div align="center">
		<hr width="50%" color="red">
			<h3><jsp:getProperty property="memid" name="member"/></h3>
		<hr width="50%" color="red">
		<br>
		<table border="1" cellspacing="0">
			<tr>
				<th>회원 아이디</th>
				<td>
					<jsp:getProperty property="memid" name="member"/>
				</td>
			</tr>
			
			<tr>
				<th>이 름</th>
				<td>
					<jsp:getProperty property="memname" name="member"/>
				</td>
			</tr>
			
			<tr>
				<th>패스워드</th>
				<td>
					<jsp:getProperty property="pwd" name="member"/>
				</td>
			</tr>
			
			<tr>
				<th>나 이</th>
				<td>
					<jsp:getProperty property="age" name="member"/>
				</td>
			</tr>
			
			<tr>
				<th>마 일 리 지</th>
				<td>
					<jsp:getProperty property="mileage" name="member"/>
				</td>
			</tr>
			
			 <tr>
				<th>직 업</th>
				<td>
					<jsp:getProperty property="job" name="member"/>
				</td>
			</tr>
			
			<tr>
				<th>주 소</th>
				<td>
					<jsp:getProperty property="addr" name="member"/>
				</td>
			</tr>
			
		</table>	
	</div>
</body>
</html>