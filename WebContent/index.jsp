<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#img1{
	width: 350px;
	height: 150px;
}
html {

	font-weight:bold;
	background: #333 url(/images/classy_fabric.png);
}

#header {
	background: black;
}

#main {
	width: 100%;
	height: 95%;
	text-align: center;
	float: left;
	overflow: auto; /* 상품리스트가 많은 경우 스크롤 생성 */
	background: #333 url(/images/classy_fabric.png);
}

#menu {
	width: 100%;
	background: #333 url(/images/classy_fabric.png);
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div><hr>
	<!--  <div id="header-under-img">
		<img id=img1 src="img/prologo1.png" width="100%" height="200px"> 
				<h1 id="header-under-img-title">
		</h1>
	</div> -->
	<div id="menu"></div>
	<div id="middleWrap">

		<div id="main"></div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
Aj("menu", "#menu");

if(${id!=null}||${id==null}){//로그인 되었을떄만 menu.jsp main
if(${page==null}){
	Aj("newPortFolio","#main");
}else
	{
	Aj("${page}","#main");
	}
} 

function Aj(url, position) {

	$.ajax({
		url : url,
		//data:
		dataType : "html",
		success : function(data) {
			//alert(data);
			$(position).html(data);
			//console.log(position);
		}
	});//ajax end
}//Aj end
</script>
</html>