package com.cr.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class ExtendStrutsFilter extends StrutsPrepareAndExecuteFilter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.doFilter(arg0, arg1, arg2);
		HttpServletRequest request = (HttpServletRequest)arg0;
		if("/ChatRoom/webSocket/".equals(request.getRequestURI())){
			arg2.doFilter(arg0, arg1);
		}else{
			super.doFilter(arg0, arg1, arg2);
		}
	}
	
}
