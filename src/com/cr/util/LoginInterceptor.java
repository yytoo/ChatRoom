package com.cr.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		System.out.println("Intercept");
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		String username = (String) session.get("username");
		if(username != null && "".equals(username)){
			return arg0.invoke();
		}else{
			return Action.LOGIN;
		}		
	}
}
