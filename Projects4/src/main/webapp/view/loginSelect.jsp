<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="EUC-KR">
<title>login_btn</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#member_login").hide();
		
		$(".open_modal").click(function() {
			$("#member_login").css("display", "block");
		});
		
		$(".modal_close_btn").click(function() {
			$("#member_login").css("display", "none");
		});
	});
</script>

<style>
	
</style>
</head>
<body>
	<div id="modal">
		<img src="../image/login_image.jpg" width="150" height="150" class="open_modal">
	</div>
	
	
	<div id="member_login">
		   <form>
		     <div class="member_login_insert">
		        <input type="button" value="회원가입" onclick="<%=request.getContextPath()%>/login_insert.do">
		     </div>
		    <div class="member_login_input">
		       <input type="button" value="로그인">
		    </div>
					
			<hr>
					
		    <div class="member_home_hosting_admin">
		     <input type="button" value="숙소 호스팅하기">
		    </div>
		            
			<div class="member_home_hosting_experience">
		      <input type="button" value="체험 호스팅하기">
		    </div>
		         
		    <div class="member_help">
		       <input type="button" value="도움말">
			</div>
			
			<div id="naver_id_login"></div>
			<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
		<script type="text/javascript">
			var clientId = "zROYJp38WO0IxEpiDz0u";
			var callbackUrl = "http://localhost:8787/project4/view2/naverLoginAPI.jsp";
			var naver_id_login = new naver_id_login(clientId, callbackUrl);
			var state = naver_id_login.getUniqState();
			naver_id_login.setButton("white", 3, 40);
			naver_id_login.setDomain("http://localhost:8787/project4/view/login_btn.jsp");
			naver_id_login.setState(state);
			naver_id_login.setPopup();
			naver_id_login.init_naver_id_login();
		</script>
			
		
			<div>
				<input type="button" class="modal_close_btn" value="닫기">
			</div>
		</form>
	</div>
</body>
</html>