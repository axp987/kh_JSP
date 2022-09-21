<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim();
	
	// 기존에는 DB의 회원 관리 테이블에서 입력한 아이디와 비밀번호가 맞는지
	// 확인 하여 >> 회원이면 메인 페이지로 이동
	String dbId = "hong";
	String dbPwd = "1234";
	
	if(userId.equals(dbId)) {
		if(userPwd.equals(dbPwd)) {
			// 회원인 경우 메인 페이지로 이동 ==> 페이지 이동
			// 세션을 설정하여 정보를 전달하는 방법
			session.setAttribute("name", "홍길동"); // setAttribute("key값", "value");
			session.setAttribute("addr", "서울시 중랑구 면목로");
			
			RequestDispatcher rd = request.getRequestDispatcher("./ex02_02.jsp");
			rd.forward(request, response);
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>