<%@page import="com.emp.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	EmpDTO contentList = (EmpDTO)request.getAttribute("content");
	List<String> joblist = (List<String>)request.getAttribute("job");
	List<EmpDTO> mgrlist = (List<EmpDTO>)request.getAttribute("mgr");
	List<DeptDTO> deptlist = (List<DeptDTO>)request.getAttribute("dept");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	onpopstate = function(event) {
		//history.back();
		location.href="select.do";
		//location.reload();
	}
	
</script>
</head>
<body>
	<div align="center">
		<hr width="50%" color="yellow">
			<h2>상세 정보</h2>
		<hr width="50%" color="yellow">
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
						<th>사원No</th>
						<td><input type="text" name="no" value="<%=contentList.getEmpno() %>" readonly></td>
					</tr>
					
					<tr>
						<th>사원명</th>
						<td><input type="text" name="t" value="<%=contentList.getEname() %>"></td>
					</tr>
					
					<tr>
						<th>담당업무</th>
						<td>
							<select name="t">
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
											} // if
										} //for
									} //if
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th>관리자No</th>
						<td>
							<select name="t">
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
													왕
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
						<td><input type="text" name="t" value="<%=contentList.getSal() %>" ></td> 
					</tr>
					<tr>
						<th>보 너 스</th>
						<td><input type="text" name="t" value="<%=contentList.getComm() %>"></td>
					</tr>
					<tr>
						<th>부 서</th>
						<td>
							<select name="t">
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
							&nbsp;&nbsp;
							<input type="button" value="삭 제" onclick="location.href='<%=request.getContextPath()%>/delete.do?no=<%=contentList.getEmpno()%>'">
						</td>
					</tr>
			</table>
		</form>
	</div>
</body>
</html>