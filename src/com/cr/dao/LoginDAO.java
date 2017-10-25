package com.cr.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cr.bean.User;
import com.cr.util.HibernateSessionFactory;

public class LoginDAO {
	public User selectUser(String username, String passwd){
		User user= null;
		Session session = HibernateSessionFactory.getSession();
		String hql = "FROM User Where username=? AND password=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);	
		query.setParameter(1, passwd);	
		user=(User) query.uniqueResult();
		//session.close();
		//System.out.print("success:"+user.getId());
		return user;
	}
}
