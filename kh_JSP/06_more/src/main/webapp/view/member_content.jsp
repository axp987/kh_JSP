<%@page import="com.member.model.vari"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% vari cont =  (vari)request.getAttribute("content"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<hr width="50%" color="tomato">
			<h3>MEMBER10 테이블 회원 상세 정보</h3>
		<hr width="50%" color="tomato">
		<br> <br>
		<table border="1" cellspacing="0" width="400">
			<%
				if(cont != null) { // 데이터가 있다면
			%>
				<tr>
					<th>회원No.</th>
					<td><%=cont.getNum() %></td>
				</tr>
				<tr>
					<th>회원ID</th>
					<td><%=cont.getId() %></td>
				</tr>
				
				<tr>
					<th>회원이름</th>
					<td><%=cont.getName() %></td>
				</tr>
				
				<tr>
					<th>회원패스워드</th>
					<td>
						<%
							if(cont.getPwd().length() != 0) {
								for(int i=1; i<=cont.getPwd().length(); i++) {
						%>
									*
						<%
								}
							}
						%>
					</td>
				</tr>
				<tr>
					<th>회원나이</th>
					<td><%=cont.getAge() %></td>
				</tr>
				<tr>
					<th>회원마일리지</th>
					<td><%=cont.getMileage() %></td>
				</tr>
				<tr>
					<th>회원직업</th>
					<td><%=cont.getJob() %></td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td><%=cont.getAddr() %></td>
				</tr>
				<tr>
					<th>회원가입일</th>
					<th><%=cont.getdate() %></th>
				</tr>
			<% 
				}else {
			%>
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 회원의 정보가 없습니다.</h3>
					</td>
				</tr>
			<%		
				} //else
			%>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="회원수정" onclick="location.href='update.do?num=<%=cont.getNum() %>'">
					&nbsp;&nbsp;
					<input type="button" value="회원삭제" onclick="if(confirm('정말로 탈퇴하시겠습니까?')) {
						location.href='delete.do?num=<%=cont.getNum() %>'				
						} else { return; }
					">
					&nbsp;&nbsp;
					<input type="button" value="회원목록" onclick="location.href='select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>