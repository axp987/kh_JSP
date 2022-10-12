<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type: "post",
			url: "data/data.json",
			datatype: "json",
			success: function(data) {
				// json의 [{} {} {}] 객체 배열로 생각하면됨
				// index: data 객체 내의 인덱스를 의미함
				// item: data 객체 내의 이름과 값을 가지고 있는 객체를 말함
				$.each(data, function(index, item) {
					let txt = "<li>책제목: " + item.title + "</li>"
							+ "<li>저자: " + item.author + "</li>"
							+ "<li>가격: " + item.price + "</li><hr>";
							
					$("body").append(txt);
				});
			},
			error: function(data) {
				alert("오류");
			}
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>