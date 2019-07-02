<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#header {
	background-color: black;
	font-weight:bold;
}

#mjm-main {
	position: relative;
	top: 100px;
}

body {
	text-align: center;
	/* background-color: rgba(0, 0, 0, 0.9);
	background: rgba(0, 0, 0, 0.9);
	color: rgba(0, 0, 0, 0.9); */
	background: #333 url(/images/classy_fabric.png);
}

.myButton {
	Font Size: 28px; bold italic Size Vertical Size : 32px;
	Horizontal Size: 76px; Border Border Radius : 9px;
	Border Size: 3px; Box Shadow/inset Vertical Position : 4px;
	Horizontal Position: 4px;
	Blur Radius: 7px;
	Spread Radius: 0px; Text Shadow Vertical Position : 3px;
	Horizontal Position: 1px;
	Blur Radius: 0px;
	moz-box-shadow: inset -4px 4px 7px 0px #29bbff;
	webkit-box-shadow: inset -4px 4px 7px 0px #29bbff;
	box-shadow: inset -4px 4px 7px 0px #29bbff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #43b1f0
		), color-stop(1, #0688fa));
	background: -moz-linear-gradient(top, #43b1f0 5%, #0688fa 100%);
	background: -webkit-linear-gradient(top, #43b1f0 5%, #0688fa 100%);
	background: -o-linear-gradient(top, #43b1f0 5%, #0688fa 100%);
	background: -ms-linear-gradient(top, #43b1f0 5%, #0688fa 100%);
	background: linear-gradient(to bottom, #43b1f0 5%, #0688fa 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#43b1f0',
		endColorstr='#0688fa', GradientType=0);
	background-color: #43b1f0;
	moz-border-radius: 9px;
	webkit-border-radius: 9px;
	border-radius: 9px;
	border: 3px solid #0b0e07;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 30px;
	font-weight: bold;
	padding: 32px 76px;
	text-decoration: none;
	text-shadow: 1px 3px 0px #263666;
	moz-box-shadow: inset -4px 4px 7px 0px #29bbff;
	Vertical Size: 32px;
	margin: 70px;
}

.myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #0688fa
		), color-stop(1, #43b1f0));
	background: -moz-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -webkit-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -o-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -ms-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: linear-gradient(to bottom, #0688fa 5%, #43b1f0 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0688fa',
		endColorstr='#43b1f0', GradientType=0);
	background-color: #0688fa;
}

.myButton:active {
	position: relative;
	top: 1px;
}
#img1{
	width: 350px;
	height: 150px;
}

/* #mjm-main{
	margin: 0px 0px 811px 0px;
} */
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="mjm-main">
		<!-- 	<span>회원가입</span><br> -->
		<!-- <div id="mjm-main-nomal"> -->

		<a id="mjm-main-namala" href="nomaljoin.jsp" class="myButton">일반회원</a>
		<a id="mjm-main-namala" href="comjoin.jsp" class="myButton">기업회원</a>
		<!-- </div> -->
		<!-- <div id="mjm-main-nomal" class="di1">
			
		</div> -->
	</div>
</body>
<script>
	
</script>
</html>