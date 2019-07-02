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
* {
	box-sizing: border-box;
	padding: 0;
	margin: 0;
}

body {
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light",
		"Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
	color: white;
	font-size: 12px;
	background: #333 url(/images/classy_fabric.png);
}

form {
	background: #111;
	width: 300px;
	height: auto;
	margin: 30px auto;
	border-radius: 0.4em;
	border: 1px solid #191919;
	overflow: hidden;
	position: relative;
	box-shadow: 0 5px 10px 5px rgba(0, 0, 0, 0.2);
	margin: 30px auto;
	top:150px;
}

form:after {
	content: "";
	display: block;
	position: absolute;
	height: 1px;
	width: 100px;
	left: 20%;
	background: linear-gradient(left, #111, #444, #b6b6b8, #444, #111);
	top: 0;
}

form:before {
	content: "";
	display: block;
	position: absolute;
	width: 8px;
	height: 5px;
	border-radius: 50%;
	left: 34%;
	top: -7px;
	box-shadow: 0 0 6px 4px #fff;
}

.inset {
	padding: 20px;
	border-top: 1px solid #19191a;
}

form h1 {
	font-size: 18px;
	text-shadow: 0 1px 0 black;
	text-align: center;
	padding: 15px 0;
	border-bottom: 1px solid rgba(0, 0, 0, 1);
	position: relative;
}

form h1:after {
	content: "";
	display: block;
	width: 250px;
	height: 100px;
	position: absolute;
	top: 0;
	left: 50px;
	pointer-events: none;
	transform: rotate(70deg);
	background: linear-gradient(50deg, rgba(255, 255, 255, 0.15),
		rgba(0, 0, 0, 0));
}

label {
	color: #666;
	display: block;
	padding-bottom: 9px;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 8px 5px;
	background: linear-gradient(#1f2124, #27292c);
	border: 1px solid #222;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
	border-radius: 0.3em;
	margin-bottom: 15px;
}

label[for=remember] {
	color: white;
	display: inline-block;
	padding-bottom: 0;
	padding-top: 5px;
}

.p-container {
	padding: 0 20px 20px 20px;
}

.p-container:after {
	clear: both;
	display: table;
	content: "";
}

.p-container span {
	display: block;
	float: left;
	color: #0d93ff;
	padding-top: 8px;
}

#inp1 {
	padding: 5px 5px;
	border: 1px solid rgba(0, 0, 0, 0.4);
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	border-radius: 0.3em;
	background: #0184ff;
	color: white;
	align-content: center;
	font-weight: bold;
	cursor: pointer;
	font-size: 20px;
	text-align: center;
	margin-bottom: 5px;
}

#inp2 {
	padding: 5px 10px;
	border: 1px solid rgba(0, 0, 0, 0.4);
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(100, 200, 200, 0.3), inset 0 10px 10px
		rgba(100, 255, 255, 0.1);
	border-radius: 0.3em;
	background: #0184ff;
	color: white;
	align-content: center;
	float: none;
	font-weight: bold;
	cursor: pointer;
	font-size: 10px;
	margin-bottom: 15px;
}

#inp3 {
	margin-bottom: 15px; {
	color: blue;
}

}
#inp4 {
	text-align: center;
}


</style>

<body>
	<form>
		<h1>비밀번호 찾기</h1>
		<div id='inp4'>
			<div id='inp3'>
				<p>
					<input type="text" placeholder="아이디" id="id" style="color: white;">	
				</p>
				<p>
					<input type="text" placeholder="이메일 입력" id="email" style="color: white;">
				</p>
				<p>
					<input type="text" value="확인" id="inp1" onclick="check()">
				</p>
			</div>
		</div>
	</form>

	<!-- <h1>비밀 번호 찾기</h1>
	<div>	
		<input type="text" placeholder="아이디" id="id"><br>
		<input type="email" placeholder="이메일" id="email"><br>
		<input type="button" value='확인' id="inp3" onclick='check()'> 
	</div> -->
</body>
<script type="text/javascript">
	var userId = "";
	var userEmail = "";

	function check() {
		userId = $('#id').val();
		userEmail = $('#email').val();

		if (userId == "" || userEmail == "") {
			alert("아이디와 이메일을 입력해 주세요.");
		}

		$.ajax({
			type : 'POST',
			data : {
				userId : userId,
				userEmail : userEmail
			},
			url : 'finpw',
			dataType : 'json',
			success : function(data) {
				console.log("return data : " + data);
				if (data != "") {
					alert("이메일로 비밀번호를 발송하였습니다.")
				}
				/* 	alert("아이디는 : "+data["n_Pw"]+" 입니다."); */
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
</script>
</html>