<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html {
	background: #333 url(/images/classy_fabric.png);
}

button, input {
	margin: 10px;
	padding: 10px 20px;
	border: 2px double rgba(0, 0, 0, 0.4);
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(100, 200, 200, 0.3), inset 0 10px 10px
		rgba(100, 255, 255, 0.1);
	border-radius: 0.3em;
	background: #0184ff;
	color: white;
	align-content: center;
	font-weight: bold;
	cursor: pointer;
	font-size: 20px;
}

div {
	
	border: 5px groove white;
}

#frm {
	text-align:center;
	position:relative;
	top:120px;
	left:450px;
	width:600px;
	color: white;
}

#image {
	border-left: 0px solid;
	border-right: 0px solid;
}
</style>
</head>
<body>
	<form id="frm"  action="thumbnail" method="post" enctype="multipart/form-data">
		<div id="body">
			<h1>썸네일 이미지를 업로드 합니다</h1>
			<br>
			<br>
			<div id="image">
				<h2>이미지 선택</h2>
				<input type="file" name="p_file" id="p_file"><br> <span
					style="color: silver;">썸네일 이미지 최적지수 300px x 300px
					"jpg","png","bmp","gif" 만 가능합니다.</span>
			</div>
			<button>다음단계</button>
			<button type="reset">취소</button>
		</div>
	</form>
</body>
<script>
	
</script>
</html>