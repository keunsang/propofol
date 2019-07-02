<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body{
	margin: 10% 0px 0px 0px;
	text-align: center;
	align-content: center;
}
div {
	border: 2px solid black;
	width: 500px;
	height: 250px;
	margin: auto;
}
#inp1{
	width: 200px;
	line-height: 50px;
	font-size: 30px;
	margin: 5px 0px 0px 0px;
}
#inp2{
	width: 55px;
	height: 55px;
	vertical-align: bottom;
	font-size: 15px;
}
#inp3{
	width: 100px;
	height: 55px;
	vertical-align: bottom;
	font-size: 30px;
	margin: 40px 0px 0px 0px;
}

</style>
<body>
	<h1>이메일인증</h1>
	<form action="emailCertification" method="post">
	<div>
		<br><span><b>이메일 인증번호를 입력해 주세요.</b></span><br><br>
		<input type="text" placeholder="인증번호" id="inp1">
		<input type="submit" value="확인" name="certification" id="certification" ><br>
		<input type="button" value="닫기" id="inp3" onclick="self.close()">
	</div>
	</form>
</body>
</html>