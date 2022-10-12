<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
				type: "post",
				url: "data/book.xml",
				datatype: "xml",
				success: function(data) {
					$(data).find("book").each(function(){ // book.xml의 book의 태그를 찾는다. 
										// each() >> 반복문
						let title = $("title", this).text();
						let author = $("author", this).text();
						let price = $("price", this).text();
						
						let txt = "<li>책 제목: " + title + "</li>"
								+ "<li>책 저자: " + author + "</li>"
								+ "<li>책 가격: " + price + "</li><hr>";
						
						$("body").append(txt);
					});
				},
				error: function(data) {
					alert("오류")
				}
		});
	});
</script>
</head>
<body>

</body>
</html>