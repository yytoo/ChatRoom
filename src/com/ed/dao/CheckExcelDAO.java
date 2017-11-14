package com.ed.dao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.cr.util.HibernateSessionFactory;

public class CheckExcelDAO {
	public Set<String> getDsProduct(){
		Set<String> ds_product = new HashSet<String>();
		Session session = HibernateSessionFactory.getSession();
		String sql="select prod_name from ds_product";
		List<String> list = session.createSQLQuery(sql).list();
		for(int i=0; i<list.size(); i++){
			ds_product.add(list.get(i));
		}		
		return ds_product;
	}
}
