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
	width: 500px;
	height: auto;
	margin: 30px auto;
	border-radius: 0.4em;
	border: 1px solid #191919;
	overflow: hidden;
	position: relative;
	box-shadow: 0 5px 10px 5px rgba(0, 0, 0, 0.2);
	margin: 30px auto;
	top: 50px;
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

input[type=text], input[type=password], input[type=email], input[type=tel] {
	width: 100%;
	padding: 8px 5px;
	background: linear-gradient(#1f2124, #27292c);
	border: 1px solid #222;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
	border-radius: 0.3em;
	margin-bottom: 15px;
	color: white;
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
#inp2 {
	padding: 5px 5px;
	border: 1px solid rgba(0, 0, 0, 0.4);
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
	/* box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3), inset 0 10px 10px
		rgba(255, 255, 255, 0.1); */
	border-radius: 0.3em;
	background: #0184ff;
	color: white;
	align-content: center;
	/* float:right;  */
	font-weight: bold;
	cursor: pointer;
	font-size: 15px;
	text-align: center;
	margin-bottom: 5px;
}

/* 
body{
	margin: 10% 0px 0px 0px;
	text-align: center;
	align-content: center;
}
div {
	border: 2px solid black;
	width: 580px;
	height: auto;
	margin: auto;
}
.inp1{
	width: auto;
	line-height: 35px;
	font-size: 20px;
	 margin: 5px 0px 0px -100px;
}*/
button{
	width: 100px;
	height: 40px;
	vertical-align: bottom;
	font-size: 13px;
}
/*
.inp2{
	width: 100px;
	height: 40px;
	vertical-align: bottom;
	font-size: 13px;
}
.btn2{
	width:100px;
}
.radio1{
	font-size:20px; 
	width:21px;
	height:23px;
	vertical-align: top;
}
 table, th, td, tr {
        border: 1px solid #bcbcbc; 
        font: bold;
      }


 */

</style>
<body>
	<!-- <h1>기업회원가입</h1> -->
	<form action="comjoin" method="post" onsubmit="return allCheck()">
	
	<div>
	<table>
		<tr>
			<td>아이디</td>
			<td><input class="inp1" type="text" name="id" id="id" placeholder="5~12자" maxlength="12"></td>
			<td><input type="button" id="inp2" onclick="idCheck()" value="중복체크"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input class="inp1" type="password" name="pw" id="pw" placeholder="8자이상"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input class="inp1" type="password" name="pw1" id="pw1"></td>
		</tr>
		<tr>
			<td>회사명</td>
			<td><input class="inp1" type="text" name="cpnName" id="cpnName"></td>
		</tr> 
		<tr>
			<td>사업자 등록번호</td>
			<td><input class="inp1" type="text" name="cpnNum" id="cpnNum"></td>
		</tr>
		<tr>
			<td>법인 등록번호</td>
			<td><input class="inp1" type="text" name="cpnNum1" id="cpnNum1"></td>
		</tr>
		<tr>
			<td>대표자명</td>
			<td><input class="inp1" type="text" name="cName" id="cName"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input class="inp1" type="email" name="cEmail" id="cEmail" placeholder="예)hong123@naver.com"></td>
			<td><input type="button"  id="inp2" onclick="emailCheck()" value="중복확인"></td>
			<td><input type="button"  id="inp2" onclick="rndSend()" value="인증번호 발송"></td>		</tr>
		<tr>
		<tr>
			<td>인증번호</td>
			<td><input class="inp1" type="text" name="rndNum" id="rndNum" placeholder="인증번호를 입력해 주세요."></td>
			<td><input type="button"  id="inp2" onclick="rndNumCheck()" id="rndNum" value="확인"></td>		</tr>
		<tr>
			<td>전화번호</td>
			<td><input class="inp1" type="tel" name="cTel" id="cTel" placeholder=" -를 제외하고 입력" maxlength="11"></td>
		</tr>
		
		<tr>
			<td></td>
			<td>
			<button  id="inp2" type="submit">완료</button>
			<button  id="inp2" type="reset">취소</button>
			</td>
			
		</tr>
		
	</table>

	</div>
	
	</form>
</body>
<script type="text/javascript">
var idck = 0;
var emailck = 0;
var rndNum = "";
var rndCheck = 0;
	function idCheck(){
		var userId = $('#id').val();
		console.log(userId);
		if($('#id').val()==""){
			alert("아이디를 입력해 주세요.")
			$('#id').focus();
			return false;
		}
		if(userId.length<5){
			alert("아이디는 최소 5자 이상입니다.");
			return false;
		}
		
		$.ajax({
			type : 'POST',
			data : {userId : userId},
			url : 'idcheck',
			dataType : 'json',
			success: function(data){
				console.log("return data : "+data);
				if(data > 0) {
					alert("아이디가 존재합니다. 다른 아이디를 입력해 주세요.");
					$('#id').focus();
				}else {
					alert("사용가능한 아이디 입니다.");
					$('#pw').focus();
					idck = 1;
				}
			},
			error : function(error) {
				alert("error : "+ error);
			}
		});
	}
	
	function emailCheck(){
		var userEmail = $('#cEmail').val();
		console.log(userEmail);
		if(userEmail==""){
			alert("이메일을 입력해 주세요.");
			return false;
		}
		
		$.ajax({
			type : 'POST',
			data : {userEmail : userEmail},
			url : 'emailcheck',
			dataType : 'json',
			success: function(data){
				console.log("return data : "+data);
				if(data > 0) {
					alert("이메일이 존재합니다. 다른 이메일을 입력해 주세요.");
					$('#cEmail').focus();
				}else {
					alert("사용가능한 이메일 입니다.");
					
					emailck = 1;
				}
			},
			error : function(error) {
				alert("error : "+ error);
			}
		});
		
	}
	
	function rndSend(){
		console.log(emailck);
		if(emailck == 0){
			alert("이메일 중복확인을 해주세요.")
			$('#email').focus();
			return false;
		}
		
		var userEmail = $('#cEmail').val();
		
		$.ajax({
			type : 'POST',
			data : {userEmail : userEmail},
			url : 'emailrndsend',
			dataType : 'json',
			success: function(data){
				console.log("return data : "+data);
				rndNum = data;
				alert("이메일 인증번호 발송");
				$('#rndNum').focus();
				
			},
			error : function(error) {
				alert("error : "+ error);
			}
		});
	}
	
	function rndNumCheck() {
		var inpRndNum = $('#rndNum').val();
		console.log("inpRndNum = "+inpRndNum);
		if(inpRndNum==""){
			alert("인증번호를 입력해 주세요.");
			return false;
		}
		if(inpRndNum==rndNum){
			alert("인증 확인");
			rndCheck = 1;
			$('#tel').focus();
		}else{
			alert("인증번호를 확인해 주세요.");
			rndCheck = 0;
			$('#rndNum').focus();
		}
			
		
	}
	function allCheck(){
		
		if($('#id').val()!=""){
			var idstr = $('#id').val();
			if(idstr.length<5){
				alert("아이디는 최소 5자 이상 입니다.");
				return false;
			}
			if(idck==0){
				alert("아이디 중복확인을 해주세요");
				return false;
			}
		}else{
			alert("아이디를 입력해 주세요.");
			$('#id').focus();
			return false;
		}
		
		if($('#pw').val()!="" && $('#pw1').val()!=""){
			var pwstr = $('#pw').val();
			if(pwstr.length<8){
				alert("비밀번호는 최소 8자 이상입니다.");
				return false;
			}
			if($('#pw').val()!=$('#pw1').val()){
				alert("비밀번호가 일치하지 않습니다.")
				$('#pw').focus();
				return false;
			}
		}else{
			alert("비밀번호를 입력해 주세요.");
			$('#pw').focus();
			return false;
		}
		
		if($('#cpnName').val()==""){
			alert("회사명을 입력해 주세요.")
			$('#cpnName').focus();
			return false;
		}
		if($('#cpnNum').val()=="" && $('#cpnNum1').val()==""){
			alert("사업자번호,법인등록번호 둘중 하나를 입력해 주세요.")
			$('#cpnNum').focus();
			return false;
		}
		if($('#cName').val()==""){
			alert("대표자명 입력해 주세요.");
			$('#cName').focus();
			return false;
		}
		
		if($('#cEmail').val()!=""){
			if(emailck==0){
				alert("이메일 중복확인을 해주세요.")
				return false;
			}
			
		}else{
			alert("이메일을 입력해 주세요.")
			$('#cEmail').focus();
			return false;
		}
		
		if(rndCheck==0){
			alert("인증번호 확인을 해주세요.");
			$('#rndNum').focus();
			return false;
		}
		if($('#tel').val()==""){
			alert("전화번호를 입력해 주세요.");
			$('#tel').focus();
			return false;
		}
		

		return true;
	}

</script>
</html>