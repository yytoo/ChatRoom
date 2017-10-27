package com.cr.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware{
	private static final long serialVersionUID = 1L;
	public PrintWriter out = null;
	
	/** Aware */
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected Map<String, Object> session = null;
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request= request;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void outPrint(Object obj) {
		if(obj instanceof JSONObject){
			((JSONObject)obj).put("timestamp", new Date().getTime()/1000);
		}
		System.out.println(obj);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
			out.print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
}
