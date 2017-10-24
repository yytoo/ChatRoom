<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MainPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/MainPage.css">
	
	<script type="text/javascript" src="<%=basePath %>js/stellar.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/MainPage.js"></script>

  </head>
  
  <body id="pg0">
	<div id="topbar" class="topbar">
	  <h1>Good Morning</h1> 	
	</div>  
	<div style="height: 700px;margin-top: 175px;" class="loginDiv" >		
		<h2 class="loginText" >登录聊天室</h2>
		<div class="inputDiv">
			<form action="login" method="POST" id="form">
				<div class="inputText"><span>用户名： </span><input type="text" name="username" id="username" class="username"></div>
				<div class="inputText"><span>密&nbsp;&nbsp;&nbsp;&nbsp;码： </span><input type="text" name="passwd" id="passwd" class="passwd"></div>
			</form>
			<input type="button" onclick="formSubmit()" value="提交">
		</div>
		
	</div> 
	<div class="content1">
		<!-- <div class="qw960">
			<h1>Hahahahaha</h1>
		</div> -->
		<div data-stellar-background-ratio="0.03" class="activebg fisrtbg" style="background-position: 50%"></div>
				<div class="qw960">
			<h1>Lalalalal</h1>
		</div>
	</div>
	<div class="content2">

		<div data-stellar-background-ratio="0.03" class="activebg secondbg" style="background-position: 50%"></div>
	</div>
	<div style="height: 819px;">
		
	</div> 
  </body>
  
  <script type="text/javascript">
  	$(function(){
	  	$.stellar({horizontalScrolling: false, verticalOffset: 40});
  	});
  	var firstHehight=778;
  	var topHeight=231;
  	var clientheight=$(window).height();
  	$(window).scroll(function(e){
  		var scrollTop= $(window).scrollTop();
  		if(scrollTop >= firstHehight){
  		
  			$("#topbar").addClass("topicfixed");
  		}else{
  			if($("#topbar").hasClass("topicfixed")){
  				$("#topbar").removeClass("topicfixed");
  			}
  		} 		
  	});
  	function formSubmit(){
  		$("#form").submit();
  	}
  	
  </script>
</html>
