<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#idcheck_btn").mouseover(function() {
			$("#idcheck").hide(); // span 태그 영역 숨기기
			
			let userId = $("#member_id").val();
			
			// 입력 길이 체크
			if($.trim($("#member_id").val()).length < 4) {
				let warningTxt = '<font color="red">아이디는 4자 이상이어야 합니다.</font>';
				$("#idcheck").text(''); // span 태그 초기화
				$("#idcheck").show();
				$("#idcheck").append(warningTxt);
				$("#member_id").val('').focus();
				return false;
			} else if($.trim($("#member_id").val()).length > 16) {
				let warningTxt = '<font color="red">아이디는 16자 이하이어야 합니다.</font>';
				$("#idcheck").text(''); // span 태그 초기화
				$("#idcheck").show();
				$("#idcheck").append(warningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			
			// 아이디 중복 여부 확인 - Ajax 기술
			$.ajax({
				type: "post",
				url: "data/idCheck.jsp", 
				data: {paramId: userId}, // let userId = $("#member_id").val();
				datatype: "jsp",
				success: function(data) {
					console.log(data)
					if(data == 1) { // 데이터베이스에 아이디가 존재하는 경우
						let warningTxt = '<font color="red">중복된 아이디입니다.</font>';
						$("#idcheck").text(''); // span 태그 초기화
						$("#idcheck").show();
						$("#idcheck").append(warningTxt);
						$("#member_id").val('').focus();
					} else {
						let warningTxt = '<font color="green">사용 가능한 아이디입니다.</font>';
						$("#idcheck").text(''); // span 태그 초기화
						$("#idcheck").show();
						$("#idcheck").append(warningTxt);
					}
				},
				error: function(data) {
					alert("데이터 통신 오류 입니다.");
				}
			});
		}); // mouseover 함수
		
	});
</script>
</head>
<body>
	<div align="center">
		<form method="post" action="https://www.naver.com">
			<table border="1" cellspacing="0" width="450">
				<tr>
					<th>회원 아이디</th>
					<td>
						<input name="member_id" id="member_id" size="20">
						<input type="button" value="아이디중복체크" id="idcheck_btn">
						
						<br>
						<span id="idcheck"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>