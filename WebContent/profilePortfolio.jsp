<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.proport {
	width: 23%;
	height: 250px;
	padding: 10px;
	float: left;
	color: white;
	cursor: pointer;
}
.proport:hover{
	filter: grayscale(80%);
}

.proport img {
	position: relative;
	top: 0;
	left: 0;
	width:300px;
	height:200px;
	align-items: center;
}

#detail {
	display: none;
	width: 30%;
	border: solid blue 1px;
}

#proportDetail {
	display: none;
}

#proportDetail.open {
	background-attachment: scroll;
	background-clip: border-box;
	background-color: rgb(6, 6, 6);
	background-origin: padding-box;
	padding-top: 20px;
	padding-left: 20px;
	padding-right: 20px; 
	width : 1000px;
	display: block;
	overflow: auto;
	position: relative;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -500px;
	margin-top: -300px;
	border: #000 solid 1px;
	color: white;
	z-index: 1;
	width: 1000px
}

#prooutport{
	color: white;
	align-content: center;
	/* float:right;  */
	font-weight: bold;
	cursor: pointer;
	font-size: 15px;
	text-align: center;
	float: right;
}
#prooutport:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #0688fa
		), color-stop(1, #43b1f0));
	background: -moz-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -webkit-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -o-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: -ms-linear-gradient(top, #0688fa 5%, #43b1f0 100%);
	background: linear-gradient(to bottom, #0688fa 5%, #43b1f0 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0688fa',
		endColorstr='#43b1f0', GradientType=0);
}
.like{
	cursor: pointer;
}
.like:hover{
	color: gray;
}
.portDelete{
	color: white;
	cursor: pointer;
	text-decoration: none;
}
.portDelete:hover{
	color: gray;
}
</style>
</head>
<body>
	<div id="detail"></div>
	<hr>
	<div id="proPortDiv"></div>
	<br>
	<div id="proportDetail"></div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
console.log(${profilePortfolio});
$(function name() {	
	var data=${profilePortfolio};
	var rs = "";
	for ( var i=0; i<data.length; i++) {
		rs+="<div class='proport' onclick=\"detail('" + data[i].p_Thumbnail + "')\">"
		+"<img src='upload/"+data[i].p_Thumbnail+"' width='200'>"
		+"<br>"
		+"<a class='portDelete' href='portDelete?Thumbnail="+data[i].p_Thumbnail+"&Number="+data[i].p_Number+"'>"
		+"포트폴리오 삭제</a>"
		+"</div>";
	}
	$("#proPortDiv").html(rs);
});
function detail(p_Thumbnail) {
	
	$('#proportDetail').addClass('open');
	//$('#detail').addClass('open');
	//$('#detail').toggle();/*show()<---->hide() */
	$('html').css('filter','blur(direction:90,strength:20)');
	
	$.ajax({
		url : 'ajaxDetail', //ajaxDetail?p_code='+pcode 쿼리스트링으로 파라미터 가져가기
		type : 'get',
		data : {
			p_Thumbnail : p_Thumbnail
		},
		dataType : "json",
		success : function(datas) {
			console.log(datas);
			var str="";
				str="<a id='prooutport' onclick='proout()'>&nbsp;x&nbsp;</a><br><br>"
				+"<a class='like' id='nlike' onclick=\"nomLike('"+datas.p_Number+"')\">일반좋아요</a>&nbsp;"
				+"<span class='lSpan' 'id='nomSpan'>"+datas.p_LikeCount+"</span><br><br>"
				+"<a class='like' id='clike' onclick=\"comLike('"+datas.p_Number+"')\">기업좋아요</a>&nbsp;"
				+"<span class='lSpan' id='comSpan'>"+datas.p_comLikeCount+"</span><br><br>"
				+"포트폴리오 작성자&nbsp;:&nbsp;"+datas.p_Id+"<br><br>"
				+"포트폴리오 작성일&nbsp;:&nbsp;"+datas.p_Date+"<br><br>"
				+"포트폴리오 타이틀&nbsp;:&nbsp;"+datas.p_Title+"<br><br>"
				+datas.p_Content+"<br>"
			$("#proportDetail").html(str);
		},
		error : function(error) {
			console.log(error);
		}
	});
}
function proout() {
	$('#proportDetail').removeClass('open');
}
</script>
</html>