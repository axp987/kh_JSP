<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>

<style type="text/css">
		
	.line {
		border: thick;
	}
	
</style>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${contentList }" />
		<hr width="50%" color="tomato">
			<h3>${dto.writer } 님 게시판 상세 내역 페이지</h3>
		<hr width="50%" color="tomato">
		
		<form>
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>작성자</th>
					<td>${dto.writer }</td>
				</tr>
				
				<tr>
					<th>글제목</th>
					<td>${dto.title }</td>
				</tr>
				
				<tr>
					<th>글내용</th>
					<td>
						<textarea cols="25" rows="7" readonly>${dto.cont }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td>
						<c:if test="${!empty dto.pwd }">
							<c:forEach begin="1" end="${dto.pwd.length() }">
								*
							</c:forEach>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<th>작성일자</th>
					<td>
						<c:if test="${empty dto.regupdate }">
							${dto.regdate }
						</c:if>
						<c:if test="${!empty dto.regupdate }">
							${dto.regupdate }
						</c:if>
					</td>
				</tr>
			<c:if test="${empty dto }">
			 	<tr>
			 		<td colspan="2" align="center">
			 			<h3>조회된 게시물이 없습니다.</h3>
			 		</td>
			 	</tr>
			</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글수정" onclick="location.href='board_modify.do?num=${dto.bno }'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="글삭제" onclick="location.href='board_remove.do?num=${dto.bno }'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" value="전체목록" onclick="location.href='board_list.do'">
					</td>
				</tr>	
			</table>
		</form>
		<br>
		<hr width="50%" color="tomato">
		<br>
		<%-- 댓글 폼 영역 --%>
		<div>
			<table cellspacing="0" width="400">
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="re_writer" id="re_writer">
					</td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td>
						<input type="text" name="re_content" id="re_content" size="40">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="right">
						<input type="button" id="replyBtn" value="댓글작성">
					</td>
				</tr>
			</table>
		</div>
		<br>
		<h3>댓글 목록</h3>
		<div>
			<table class="list" cellspacing="0" width="400">
				<tr>	
					<td colspan="2">작성자</td>
				</tr>
				<tr class="line">
					<td>댓글내용</td>
					<td>작성일자</td>
				</tr>
			</table>
		</div> 
	</div>
	
<script type="text/javascript">
	$(function() {	
		//ajax에서 동일하게 사용되는 속성 결정
		$.ajaxSetup({
			// ajax에서 한글 깨짐 문제 해결
			ContentType: "application/x-www-form-urlencoded;charset=UTF-8",
			type: "post"
		});
		
		// TBL_REPLY 테이블의 모든 데이터를 가져오는 함수
		function getList() {
			$.ajax({
				url: "/18_Reply_Board/view/board_reply.jsp",
				data: {rno: ${dto.bno}},
				datatype: "xml", // 결과 데이터 타입
				success: function(data) {
					// 테이블 태그의 타이틀태거를(th) 제거
					$(".list tr:gt(1)").remove();
					
					let table = "";
					$(data).find("reply").each(function() {
						table += "<tr>";
						table += "<td colspan='2'>" + $(this).find("rewriter").text() + "</td>";
						table += "</tr>";
						
						table += "<tr>";
						table += "<td>" + $(this).find("recont").text() + "</td>";
						table += "<td>" + $(this).find("redate").text() + "</td>";
						table += "</tr>";
						
						table += "<tr>";
						table += "<td colspan='2'>&nbsp;</td>";
						table += "</tr>";
					});
					
					// 첫번째 태그 이후 두번째 인덱스
					$(".list tr:eq(1)").after(table);
					
				},
				error: function() {
					alert("데이터 통신 오류");
				}
			});
		} // getList() 함수 end
		
		// 댓글 작성 버튼을 클릭했을 때 DB에 추가로 저장.
		$("#replyBtn").on("click", function() {
			$.ajax({
				url: "/18_Reply_Board/board_insert_ok.do",
				datatype: "text",
				data: {
					writer: $("#re_writer").val(),
					content: $("#re_content").val(),
					bno: ${dto.bno}
				},
				success: function(data) {
					if(data > 0) {
						alert('댓글 작성 완료');
						
						// 댓글 작성 후 다시 전체 댓글 리스트를
						// 화면에 출력
						getList();
						
						// input 태그는 입력된 내용을 지원
					$("input[type=text]").each(function() {
						$(this).val(""); // 입력된 값 지위기
						}); 
					} else {
						alert('댓글 추가 실패 하였습니다.');
					}
				},
				error: function(data) {
					alert('데이터 통신 오류');
				}
			}); 
		}); // 댓글 등록하기 end
		
		getList(); // 전체리스트 함수 호출
	});
</script>
</body>
</html>