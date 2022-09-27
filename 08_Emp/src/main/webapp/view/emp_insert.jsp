<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<String> joblist = (List<String>)request.getAttribute("job");
	List<EmpDTO> mgrlist = (List<EmpDTO>)request.getAttribute("mgr");
	List<DeptDTO> deptlist = (List<DeptDTO>)request.getAttribute("dept");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="yellow">
			<h2>사원 등록</h2>
		<hr width="50%" color="yellow">
		<br> <br>
		<form method="post" action="<%=request.getContextPath()%>/insert_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>사원No</th>
					<td><input type="text" name="num"></td>
				</tr>
				
				<tr>
					<th>사원명</th>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<th>담당업무</th>
					<td>
						<select name="job">
							<%
								if(joblist.size() == 0) {
							%>
									<option value="">:::담당업무가 없음:::</option>
							<%		
								} else {
									for(int i=0; i<joblist.size(); i++) {
										String str = joblist.get(i);
							%>
										<option value="<%=str %>"><%=str %></option>
							<%
									} //for
								} //if
							%>
						</select>
					</td>
				</tr>
				<tr>
					<th>관리자No</th>
					<td>
						<select name="mgr">
							<%
								if(mgrlist.size() == 0) {
							%>
									<option value="">:::관리자가 없음:::</option>
							<%		
								} else {
									for(int i=0; i<mgrlist.size(); i++) {
										EmpDTO str = mgrlist.get(i);
							%>
										<option value="<%=str.getEmpno() %>">
											<%=str.getEname() %>
											[<%=str.getEmpno() %>]	
										</option>
							<%
									} //for
								} //if
							%>
						</select>
					</td>
				</tr>
				<tr>
					<th>급 여</th>
					<td><input type="text" name="sal"></td> 
				</tr>
				<tr>
					<th>보 너 스</th>
					<td><input type="text" name="comm"></td>
				</tr>
				<tr>
					<th>부 서</th>
					<td>
						<select name="dept">
							<%
								if(deptlist.size() == 0) {
							%>
									<option value="">:::부서가 없음:::</option>
							<%		
								} else {
									for(int i=0; i<deptlist.size(); i++) {
										DeptDTO str = deptlist.get(i);
							%>
										<option value="<%=str.getDeptno() %>">
											<%=str.getDname() %>
											[<%=str.getLoc() %>]	
										</option>
							<%
									} //for
								} //if
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가 입">
						&nbsp;&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>