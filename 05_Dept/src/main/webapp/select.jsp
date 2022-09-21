<%@page import="com.khie.model.*"  %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");
	// request의 반환형은 Object
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
			<h3>DEPT 테이블 전체 리스트 목록</h3>
		<hr width="50%" color="red">
		<br> <br>
		<table border="1" cellspacing="0">
			<tr>
				<th>부서No.</th>
				<th>부서명</th>
				<th>부서위치</th>
				<th>부서수정</th>
				<th>부서삭제</th>
			</tr>
			<%
				if(dept.size() != 0) { // 데이터가 dept에 존재 시
					// 데이터 수만큼 반복해서 화면에 출력
					for(int i=0; i<dept.size(); i++) {
						DeptDTO dto = dept.get(i); // 점검
			%>
			<tr>
				<td><%=dto.getDeptno() %></td>
				<td><%=dto.getDname() %></td>
				<td><%=dto.getLOC() %></td>
				<td>
					<input type="button" value="부서수정" onclick="location.href='update.jsp?deptno=<%=dto.getDeptno() %>'">
					<!-- url이 노출된다는 단점이 존재함 -->
				</td>
				
				<td>
					<input type="button" value="부서삭제" onclick="location.href='delete?deptno=<%=dto.getDeptno() %>'">
					<!-- url이 노출된다는 단점이 존재함 -->
				</td>
				<td>	</td>
			</tr>
			<%			
					} // for문의 end
				} //if문
				else { %>
					<tr>
						<td colspan="5" align="center">
						<h3>검색된 데이터가 없습니다.</h3>
						</td>
					</tr>
			<%
				}
			%>
					<tr>
						<td colspan="5" align="right">
							<input type="button" value="부서추가" onclick="location.href='insert.jsp'">
						</td>
					</tr>
		</table>
	</div>
</body>
</html>