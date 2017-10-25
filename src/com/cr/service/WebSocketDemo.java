package com.cr.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Param;

import com.cr.util.GetHttpSessionConfiguration;

@ServerEndpoint(value="/webSocket/{username}")    //指定一个URI，客户端可以通过这个URI来连接到WebSocket
public class WebSocketDemo {
	private static final AtomicInteger onlineCount = new AtomicInteger(0);  //记录当前在线连接数，AtomicInteger保证线程安全（相对Integer来说）
	private static CopyOnWriteArraySet<WebSocketDemo> webSocketSet = new CopyOnWriteArraySet<WebSocketDemo>(); //线程安全的Set，存放每个客户端对应的MyWebSocket对象，若要实现服务器和单一客户端的对话，可以用Map来存放，使用key标示客户
	private final String nickname;   //定义一个记录客户端的聊天昵称,在下面的构造函数中进行了初始化，所以这边不用初始化
	private Session session;    //与某个客户端的连接会话，需要通过她来给客户端发数据
	private String user;
	
	public WebSocketDemo(){
		nickname="访客"+onlineCount.getAndIncrement();
	}
	 
	@OnOpen    //当客户端连接成功后的回调，参数Session是可选参数，这个Session是WebSocket中的会话，表示一次会话，而不是HTTPSession
	public void onOpen(@PathParam(value="username") String username ,Session session){
		//HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		//user = (String) httpSession.getAttribute("username");
		user = username;
		this.session=session;
		webSocketSet.add(this);
		String message = String.format("%s:%s", user, "加入聊天室");
		broadcast(message);
	}
	
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		String message = String.format("%s:%s", user, "离开聊天室");
		broadcast(message);
	}
	
	@OnMessage  //客户端发送信息后的回调
	public void inMessage(String message, Session session){
		
		System.out.println(this.session==session);
		broadcast(String.format("%s:%s", user, filter(message)));
	}
	
	public void broadcast(String info){  //群发信息
		
		for(WebSocketDemo w:webSocketSet){
			synchronized (WebSocketDemo.class) {
				try {
					w.session.getBasicRemote().sendText(info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("向客户端"+user+"发送信息失败");
					webSocketSet.remove(w);
					try {
						w.session.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String message = String.format("%s:%s", user, "已经离开聊天室");
						broadcast(message);
					}
					
				}
			}
		}
	}
	
	public static String filter(String message){   //过滤用户发送的信息内容
		if(message == null){
			return null;
		}
		return message;	
	}
	
}
