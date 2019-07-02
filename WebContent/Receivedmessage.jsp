<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<head>
<meta charset="UTF-8">
<title>Receivedmessage.jsp</title>
<style>
#portfolios {
	width: 100%;
	height: 95%;
	text-align: center;
	float: left;
	overflow: auto; /* 상품리스트가 많은 경우 스크롤 생성 */
	background: #333 url(/images/classy_fabric.png);
	
	
}
.login{
font-size:16px;
	font-weight : bold;
	border:none;}
#header{
border:none;}
#img1 {
	width: 350px;
	height: 150px;
}
#header {
	background-color: black;
}

div {
	border: 1px solid gray;
}
.delete{
float:right;
color:red;
}

#messageList, #messageview, #sendmessage {
	width: 498px;
	height: 700px;
	float: left;
	overflow: auto;
}

#list ,#viewbody{
	position:relative;
	left:7px;
	width: 479px;
	border: 0px;
	font-size: 20px;
}

button {
	float: right;
	 margin: 5px;  
	 padding: 10px 20px;  
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
	font-size: 10px;
	position: inherit;
	top:10px;
}
#cancle{
float: left;}
#m_s_id ,#m_title{
	width: 300px;
}

#m_content {
	width: 300px;
	height: 400px;
}


body {
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light",
		"Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
	color: white;
	font-size: 12px;
	background: #333 url(/images/classy_fabric.png);
}
.letter{
color: white;
text-decoration: none;
cursor:pointer;
}

</style>
<script>
	
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="message">
		<!-- 받은 편지함 start -->
		<div id="messageList">
			<h1>
				<a class="letter" id="receivedmessage">받은 편지함</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<button id="write">+작성</button>
				<a id="sendsmessage" class="letter">보낸 편지함</a>
			</h1>
			<!-- 이 부분에 ajax로 데이터베이스의 제목 가져오기(아이디로) -->
			<hr>
			<div id="list"></div>
			<div id="slist"></div>
		</div>
		<!-- 받은 편지함 end -->

		<!-- 받은 메시지 상세보기 start -->
		<div id="messageview">
			<h1>
				<span id="id">보낸사람</span>ㅡ <span id="sendid"></span> <button onclick="resends()" id="resendss">답장</button>
			</h1>
			<hr>
			<div id="viewbody"></div>
		</div>
		<!-- 받은 메시지 상세보기 end -->

		<!-- 보낼 메시지 start -->
		<div id="sendmessage">
			<h2>새 메시지</h2>
			<hr>
			<form action="InsertMessage" method="post">
			
				받는사람:<input type="text" id="m_s_id" size="20" name="m_s_id">
				<br> <br> 글 제목:<input type="text" id="m_title"
					class="message" size="100" name="m_title"> <br> <br>글
				내용:
				<textarea id="m_content" name="m_content"></textarea>
				<br>
				<!-- <input type="text" id="send" name="send" value="a" hidden=""> -->
				<button id="send">보내기</button>
				<button type="reset" id="cancel">취소</button>
			</form>
		</div>
		<!-- 보낼 메시지 end -->
	</div>
</body>
<script>
	//첫 화면 모두 숨기기
	$("#sendmessage").hide();
	$("#messageview").hide();
	//작성 클릭시 화면전환
	$("#write").click(function() {
	$("#sendmessage").show();
	$("#messageview").hide();
	});
	
	//receivedmessage 클릭 이벤트
 $("#receivedmessage").click(function(){
	 $("#id").html("보낸사람");
	 $("#resendss").show();
	 $("#messageview").hide();
	 $("#sendmessage").hide();
		var sid="";
		var rs="";
		var data = ${mlist};
		//rs에 DB의 편지내용 불러온 것 저장
		for (var i = 0; i < data.length; i++) {
			rs += "<a id=mtitle style='cursor:pointer'; class='title'>" + data[i].m_title + "<a href=delete?title="+ data[i].m_title +"&num="+data[i].m_num+" class=delete>삭제</a></a><hr>";
		};
		//list출력
		$("#list").html(rs); 
		//타이틀 이벤트
		$("a").click(function() {
			var a = $(this).html();
			$.ajax({
				type : 'get',//post
				url : 'mtitle',
				data : {m_title : a , kind:'a'},
				datatype : 'json',
				success : function(data) {
					var datas=JSON.parse(data);
					$("#sendmessage").hide();
					$("#messageview").show();
					sid=$("#sendid").html();
					$("#sendid").html(datas.m_s_id);
					$("#viewbody").html(datas.m_content+"<hr>보낸 시간 : "+datas.m_s_time);},
				error : function(error) {alert(error);}	});	});
		
 });
	
	
	//sendsmessage 클릭 이벤트
	$("#sendsmessage").click(function(){
		$("#resendss").hide();
		$("#id").html("받은사람");
		$("#messageview").hide();
		$("#sendmessage").hide();
		var sdata = ${smlist};
		var rs2 = "";	
		var sid2="";
		for (var i = 0; i < sdata.length;i++){
			rs2 += "<a id=smtitle style='cursor:pointer'; class='title'>" + sdata[i].m_title + "</a><hr>";
		}
		//list출력
		$("#list").html(rs2);
		
		//타이틀 이벤트
		$("a").click(function() {
			var a = $(this).html();
			$.ajax({
				type : 'get',//post
				url : 'mtitle',
				data : {m_title : a, kind:'b'},
				datatype : 'json',
				success : function(data) {
					var datass=JSON.parse(data);
					$("#sendmessage").hide();
					$("#messageview").show();
					$("#sendid").html(datass.m_r_id);
					sid2=$("#sendid").html();
					$("#viewbody").html(datass.m_content+"<hr>보낸 시간 : "+datass.m_s_time);},
				error : function(error) {alert(error);}	});	});
	});		
	
	//답장
	$("#resendss").click(function(){
		$("#messageview").hide();
		$("#sendmessage").show();
		$("#m_s_id").val($("#sendid").html());
		$("#m_title").val("");
		$("#m_content").val("");
	});

	
	//보내기
	$("#send").click(function(){
		var sendid=$("#m_s_id").val();
		$.ajax({
			type : 'get',//post
			url : 'getid',
			data : {sendid : sendid},
			datatype : 'json',
			success : function(data) {
				alert(data);
			},
			error : function(error) {alert("메시지 보내기 실패");}	});
	});
	
</script>
</html>