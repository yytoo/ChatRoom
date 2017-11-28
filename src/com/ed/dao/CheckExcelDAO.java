package com.ed.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.cr.util.HibernateSessionFactory;
import com.ed.bean.Company;
import com.ed.bean.Member;

public class CheckExcelDAO {
	public List<String> getDsProduct(){
		Set<String> ds_product = new HashSet<String>();
		Session session = HibernateSessionFactory.getSession();
		String sql="select prod_name from ds_product";
		List<String> list = session.createSQLQuery(sql).list();
		session.close();
		return list;
	}
	
	public Member getMemberId(String aPhoneNum, Session session){
		//int iMemberId=0;
		//String sql = "select id from member where username=?";
		Member member = null;
		String hql="from Member where username=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, aPhoneNum);
		member=(Member) query.uniqueResult();
		/*SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, aPhoneNum);
		if(sqlQuery.list().size()!=0){
			iMemberId=(Integer) sqlQuery.list().get(0);
		}*/
		
		return member;		
	}
	
	public int checkMember(int aCompanyId, int aMmeberId, Session session){
		int iCount=0;
		String sql="select count(*) from company_role where member_id=? and company_id=? and status>-1";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, aMmeberId);
		sqlQuery.setParameter(1, aCompanyId);
		iCount=Integer.parseInt(sqlQuery.uniqueResult().toString());
		return iCount;
	}
	
	public Company getCompany(int aCompanyId, Session session){
		Company company=null;
		System.out.println(aCompanyId);
		String hql ="from Company where id=?";		
		Query query = session.createQuery(hql);
		query.setParameter(0, aCompanyId);
		company=(Company) query.uniqueResult();
		//System.out.println(query.list().get(0).toString());
		return company;
	}
		
}
