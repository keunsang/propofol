<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.atag{
	color: white;
	text-decoration: none;
}
.atag:hover{
	color: gray;
}
#img1{
	text-align: center;
}
.logout {
	text-align: center;
}

.login {
	text-align: center;
}
</style>
</head>
<body>
		<div class="logout">
		<img id=img1 src="img/prologo.jpg" width="100px" height="50px"
			onclick="javascript:location.href='index.jsp'"
			style="cursor: pointer;">
			<br><hr>
			<a class="atag" id="login" href=login.jsp>로그인</a>&nbsp;&nbsp;
			<a class="atag"id="memberjoin" href=memberjoin>회원가입</a>&nbsp;&nbsp;
		</div>
		<div class="login">
				<img id=img1 src="img/prologo.jpg" width="100px" height="50px"
			onclick="javascript:location.href='index.jsp'"
			style="cursor: pointer;">
			<br><hr>
			<a class="atag" id="portfolio-made" href="portfolioMade">포트폴리오 제작</a>&nbsp;&nbsp;
			<a class="atag" id="profile" href="profile">프로필</a>&nbsp;&nbsp;
			<a class="atag" id="message" href=Receivedmessage>메세지</a>&nbsp;&nbsp;
			<a class="atag" id="logout" href=logout>로그아웃</a>
		</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>


$(document).ready(function(){
	if(${id==null })
		{
			$(".logout").show();
			$(".login").hide();
		}
	else
		{
			$(".login").show();
			$(".logout").hide();
		}
});
	
	
</script>
</html>