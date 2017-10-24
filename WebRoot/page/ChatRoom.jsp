<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ChatRoom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/ChatRoom.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
  </head>
  
  <body>
	<p hidden="hidden" name="username" id="username">${requestScope.username }</p>
  	<div class="roomDiv">
	    <div style="width: 800px; height: 240px; overflow-y: auto; border: 1px solid #333;" id="show">
	        <div class="showChatMessage"></div>   
	    </div>
	    <div class="sendDiv">
		        <input class="sendinput" type="text" size="80" id="msg" name="msg" placeholder="输入聊天内容" />
		        <input class="senButtom" type="button" value="发送" id="sendBn" name="sendBn" onclick="send()">
	      </div>
    </div>
    <script type="text/javascript">
    	var username =$("#username").text();
  
    	/*
    	 *监听的三种状态变化，js会回调
    	 */
    	var ws = new WebSocket("ws://127.0.0.1:8080/ChatRoom/webSocket");   
    	ws.onopen=function(message){
    	
    	};
    	ws.onclose=function(message){
    	};
    	ws.onmessage = function(message){   
		  showMessage(message.data);
		};
		window.onbeforeunload=function(){   //监听窗口关闭事件，连接还没断开就关窗口，服务器端会报错
		   ws.close();
		};		    	
		 function closeWebSocket(){
		    ws.close();
		  }
    	function send(){
    		var text = $("#msg").val();   		
    		ws.send(text);
    		$("#msg").val("");
    	}
    	function showMessage(message){
    		$(".showChatMessage").append("<br>"+message+"</br>");
    		
    	}
    </script>
  </body>
</html>
