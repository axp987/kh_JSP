/**
 *  customer 테이블을 이용한 Ajax 실습
 */
 
 $(function() {
		// 여러 ajax에서 동일하게 사용되는 속성 설정
		$.ajaxSetup({
			ContentType: "application/x-www-form-urlencoded;charset=UTF-8", //한글처리
			type: "post"
		});
		
		// 모든 데이터를 가져오는 함수
		// customer 테이블에서 전체 데이터를 가져오는 함수
		function getData() {
			$.ajax({
				url: "/16_Ajax/select.do", // 서블릿 이동
				datatype: "xml", // 결과 데이터 타입
				success: function(data) {
					// 테이블 태그의 첫번째 행(타이틀(제목) 태그)를
					// 제외하고 나머지 모든 행을 제거하라는 의미
					$("#listTable tr:gt(0)").remove(); // gt(0) : 첫번째(제목) 빼고 나머지 모두를 의미
					
					let table = "";
					
					$(data).find("customer").each(function() { // each 반복문
						table += "<tr>";
							table += "<td>"; table += $(this).find("no").text(); table += "</td>";
							
							table += "<td>"; table += $(this).find("id").text(); table += "</td>";
							
							table += "<td>"; table += $(this).find("name").text(); table += "</td>";
		
							table += "<td>"; table += $(this).find("age").text(); table += "</td>";
							
							table += "<td>"; table += $(this).find("phone").text(); table += "</td>";
							
							table += "<td>"; table += $(this).find("addr").text(); table += "</td>";
							table += "<td>"; table += "</td>";
						table += "</tr>";
					});
					// 테이블의 첫번째 행의 아래에 table을 추가
					$("#listTable tr:eq(0)").after(table);
				},
				error: function() {
					
				}
			});
		} // getData()
		
		$("#id").keyup(function() {
			$.ajax({
				url: "/16_Ajax/idCheck.do",
				data: {id: $(this).val() },
				datatype: "text",
				success: function(data) {
					$("span").text(data);
				},
				error: function() {
					alert("통신 오류");
				}
			});
		});
		
		
		// 가입하기 버튼을 클릭 시 DB에 추가로 저장
		$("#btn").on("click", function() {
			$.ajax({
				url: "/16_Ajax/insert.do",
				data: $("#inForm").serialize(),
				datatype: "text",
				success: function(data) {
					if(data> 0) {
						alert("가입 완료");
						
						// 가입 완료 후에 다시 전체 레코드를 화면에 뿌려주기
						getData();
						
						// input 태그에 입력된 내용을 지우는 작업
						// input의 type이 text은 필드를 찾는다.
						$("input[type=text]").each(function() {
							$(this).val("");
						});
					} else {
						alert("가입 실패");
						
					}
				},
				error: function() {
					alert("통신 오류");
				}
			});
		});
		
		
	getData();
});
 