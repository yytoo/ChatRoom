package com.cr.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.cr.bean.User;
import com.cr.dao.LoginDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();

	public String Login(){
		String username=user.getUsername();
		String passwd=user.getPassword();
		System.out.println("loginaction:"+username+passwd);
			// TODO Auto-generated method stub
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=UTF-8");
		/*String username=req.getParameter("username");
		String passwd=req.getParameter("passwd");*/
		LoginDAO loginDAO = new LoginDAO();
		User user =loginDAO.selectUser(username, passwd);
		HttpSession session = req.getSession();
		if(user != null){
			req.setAttribute("username", user.getUsername());
			session.setAttribute("username", user.getUsername());
			return "success";
			}else{
			req.setAttribute("message", "username or passwd is wrong");
			return "default";
		}
	}
	
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
}
