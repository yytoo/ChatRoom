package com.cr.util;

import javax.servlet.http.HttpSession;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class GetHttpSessionConfiguration extends Configurator {  //该方法得到的session都是null，无法得到结果，如果添加监听，每次得到的session都不同，又不符合需求

	@Override
	public void modifyHandshake(ServerEndpointConfig sec,
			HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
		//super.modifyHandshake(sec, request, response);
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
	}
	
}
