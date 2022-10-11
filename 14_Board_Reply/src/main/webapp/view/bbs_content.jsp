<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>JSP_BBS 테이블 답변형 게시판 상세정보 폼 페이지</h3>
		<hr width="50%" color="blue">
		<br>
		<c:set var="list" value="${List }" />
		
		<form method="post" action="<%=request.getContextPath()%>/bbs_write_ok.do">
			<table border="1" cellspacing="0" width="350">
				<c:if test="${!empty list }">
					<c:if test="${Res == -1}">
						<script>
							alert('패스워드를 확인해주세요.');
						</script>
					</c:if>
					<c:if test="${Res == 1}">
						<script>
							alert('수정 완료했습니다.');
						</script>
					</c:if>
					<tr>
						<th>작성자</th>
						<td>
							${list.getWriter() }
						</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td>
							${list.getTitle() }
						</td>
					</tr>
					<tr>
						<th>글내용</th>
						<td>
							<textarea rows="7" cols="25" name="all" readonly>${list.getCont() }</textarea>
						</td>
					</tr>
					<tr>
						<th>글 비밀번호</th>
						<td>
							<c:forEach begin="1" end="${list.getPwd().length() }">
								*
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<th>등록일</th>
						<td>
							${list.getDate() }
						</td>
					</tr>
					
					<tr>
						<th>수정일</th>
						<td>
							<c:if test="${empty list.getUpdate() }">
									${list.getDate()}
							</c:if>
							<c:if test="${!empty list.getUpdate() }">
								${list.getUpdate() }
							</c:if>
						</td>
					</tr>
					
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td>
							<h3>상세 정보를 출력할 수 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정" onclick="location.href='<%=request.getContextPath() %>/bbs_modify.do?num=${list.getNo() }'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="삭제" onclick="if(confirm('정말로 삭제할껀가요?')) { 
																		location.href='<%=request.getContextPath() %>/bbs_delete.do?num=${list.getNo() }'
																	} else { return }">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="답변" onclick="location.href='<%=request.getContextPath() %>/bbs_reply.do?num=${list.getNo() }'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="전체목록" onclick="location.href='bbs_list.do'">
					</td>
				</tr>
			</table>
		</form>
		<br>
		<form>
			<input type="text" name="txt">
			<input type="submit" value="작성">
			<br>
			<table>
			
			</table>
		</form>
	</div>
</body>
</html>