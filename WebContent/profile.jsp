<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	
</script>
<style>
button {
	 margin: 5px;  
	 padding: 7px 15px;  
	border: 1px solid rgba(0, 0, 0, 0.4);
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(100, 200, 200, 0.3), inset 0 10px 10px
		rgba(100, 255, 255, 0.1);
	border-radius: 0.3em;
	background: #0184ff;
	color: white;
	align-content: center;
	font-weight: bold;
	cursor: pointer;
	font-size: 15px;
}

#portfolios {
	width: 100%;
	height: 95%;
	text-align: center;
	float: left;
	background: #333 url(/images/classy_fabric.png);
}

html {
	background: #333 url(/images/classy_fabric.png);
	color: white;
	font-weight : bold;
}

#img1 {
	width: 350px;
	height: 150px;
}

div.port {
	width: 300px;
	height: 200px;
	padding: 0px;
	margin: 10px;
}

#header {
	background-color: black;
}

#profile {
	width: 1500px;
	height: 95%;
}

#basicinfo {
	width: 500px;
	height: 210px;
	float: left;
}

#etcinfo {
	width: 992px;
	height: 210px;
	float: right;
	overflow: scroll;
}

#modify {
	float: left;
}

#success {
	float: : right;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>
	<hr>
	<div id="profile">
		<div id="basicinfo">
			<h2>
				이름 : <span id="name">이름</span>
			</h2>
			<br>성별 : <span id="sex">성별</span>
			<br> 번호 : <span id="phone">번호</span><br> 이메일 : <span
				id="email">이메일</span><br> 
		</div>
		<div id="etcinfo">
			<button id="modify" onclick="modify()">수정</button>
			<form action="profileetc" method="post" style="width: 800px;">
				<button id="success" onclick="success()" type="submit">완료</button>
				<span id="etctitle" style="font-size: 20px;">기타 정보</span> <br>
				<textarea rows="12" cols="139" readonly="readonly" id="etc"
					name="etc" style="font-size: 20px; width: 969px;"></textarea>
			</form>

		</div>
		<div id="portfolios">
		</div>
	</div>
</body>
<script>
	//수정,완료 버튼 세션 아이디가 해당 페이지 프로필 아이디와 같을때 버튼 생성

	var profile = ${profile};
	var profileid = profile.n_Id;
	$("#name").html(profile.n_Name);
	$("#phone").html(profile.n_Phone);
	$("#email").html(profile.n_Email);
	$("#sex").html(profile.n_sex);
	$("#etc").html(profileid);

	if (!'${id}' == profileid) {
		$("#success").hide();
		$("#modify").hide();
	}

	function modify() {
		$("#etc").removeAttr("readonly");
	};
	function success() {
		$("#etc").attr("readonly", "readonly");
	};

	$(document).ready(function() {
		$.ajax({
			url : 'profilePortfolio',
			dataType : "html",
			success : function(data) {
				$("#portfolios").html(data);
			}
		});//ajax end 
		$.ajax({
			url : 'profiletc',
			dataType : 'json',
			data : {profileid : profileid},
			success : function(datas) {
				$("#etc").html(datas);
			}
		});
	}) //도큐멘트레디end
</script>	
</html>