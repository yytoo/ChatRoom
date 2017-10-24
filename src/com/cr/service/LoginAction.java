package com.cr.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.cr.bean.User;
import com.cr.dao.LoginDAO;

public class LoginAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		String username=req.getParameter("username");
		String passwd=req.getParameter("passwd");
		LoginDAO loginDAO = new LoginDAO();
		User user =loginDAO.selectUser(username, passwd);
		HttpSession session = req.getSession();
		if(user != null){
			req.setAttribute("username", user.getUsername());
			session.setAttribute("username", user.getUsername());
			req.getRequestDispatcher("page/ChatRoom.jsp").forward(req, resp);
		}else{
			req.setAttribute("message", "username or passwd is wrong");
			req.getRequestDispatcher("MainPage.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
