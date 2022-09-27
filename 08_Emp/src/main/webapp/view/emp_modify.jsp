<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<String> joblist = (List<String>)request.getAttribute("jList");
	List<EmpDTO> mgrlist = (List<EmpDTO>)request.getAttribute("mList");
	List<DeptDTO> deptlist = (List<DeptDTO>)request.getAttribute("dList");
	EmpDTO contentList = (EmpDTO)request.getAttribute("modify");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lightgray">
			<h3>EMP 테이블 사원 정보 수정 폼 페이지</h3>
		<hr width="50%" color="lightgray">
		<br> <br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
						<th>사원No</th>
						<td><input type="text" name="no" value="<%=contentList.getEmpno() %>" readonly></td>
					</tr>
					
					<tr>
						<th>사원명</th>
						<td><input type="text" name="name" value="<%=contentList.getEname() %>"></td>
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
											if(contentList.getJob().toString().equals(str)) {
								%>
												<option value="<%=contentList.getJob().toString() %>" selected>
													<%=contentList.getJob().toString() %>
												</option>
								<%											
											} else {
								%>
												<option value="<%=str %>">
													<%=str %>
												</option>
								<%
											}
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
											if(contentList.getMgr() == 0) {
								%>
												<option value="<%=contentList.getMgr() %>">
													최고관리자	
												</option>
												
								<%			
												break;
											}
											else if(contentList.getMgr() == str.getEmpno()) { // 현재 가지고온 mgr  < > 
								%>
												<option value="<%=contentList.getMgr() %>" selected>
													<%=str.getEname() %> [<%=str.getEmpno() %>]	
												</option>
								<%											
											} else {
								%>
												<option value="<%=str.getEmpno() %>">
													<%=str.getEname() %>
													[<%=str.getEmpno() %>]	
												</option>
								<%	
											}
										} //for
									} //if
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th>급 여</th>
						<td><input type="text" name="sal" value="<%=contentList.getSal() %>" ></td> 
					</tr>
					<tr>
						<th>보 너 스</th>
						<td><input type="text" name="comm" value="<%=contentList.getComm() %>"></td>
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
											if(contentList.getDeptno() == str.getDeptno()) {
								%>
												<option value="<%=str.getDeptno() %>" selected>
													<%=str.getDname() %>
													[<%=str.getLoc() %>]	
												</option>
								<%
											} else {
								%>
												<option value="<%=str.getDeptno() %>">
													<%=str.getDname() %>
													[<%=str.getLoc() %>]	
												</option>
								<%
											}
								%>
								<%
										} //for
									} //if
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="수 정">
							&nbsp;&nbsp;
							<input type="button" value="초기화" onclick="location.reload()">
						</td>
					</tr>
			</table>
		</form>
	</div>
		
</body>
</html>