package com.ed.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import com.ed.bean.Company;
import com.ed.bean.Member;
import com.ed.dao.CheckExcelDAO;

public class CheckExcelServer {
	public Set<String> getDsProduct(){
		String sCompile="[\\(（]";
		Pattern p = Pattern.compile(sCompile);
		Matcher m =null;
		CheckExcelDAO checkExcelDAO = new CheckExcelDAO();
		List<String> list = checkExcelDAO.getDsProduct();
		Set<String> dsProduct= new HashSet<String>();
		String sProduct="";
		int iIndex=-1;
		for(int i=0; i<list.size(); i++){   //使用正则匹配去除括号（中文和英文的括号都有）以及括号内的内容，仅保留前面的英文字符
			sProduct=list.get(i);
			m=p.matcher(sProduct);
			while(m.find()){
				iIndex=m.start();
			}
			if(iIndex>-1){
				sProduct=sProduct.substring(0,iIndex);
			}
			iIndex=-1;
			dsProduct.add(sProduct);
		}	
		return dsProduct;
	}
	
	public int checkMember(int aCompanyId, int aMemberId, Session session){
		int isExist=0;
		CheckExcelDAO checkExcelDAO = new CheckExcelDAO();
		isExist=checkExcelDAO.checkMember(aCompanyId, aMemberId, session);
		return isExist;  //0表示memberid和companyid不对应		
		//return isExist;
	}
	
	public Member getMember(String aPhoneNum,Session session){
		CheckExcelDAO checkExcelDAO = new CheckExcelDAO();
		Member member = checkExcelDAO.getMemberId(aPhoneNum, session);
		return member;
	}
	
	public Company getCompany(int aCompanyId, Session session){
		CheckExcelDAO checkExcelDAO = new CheckExcelDAO();
		Company company = checkExcelDAO.getCompany(aCompanyId, session);
		return company;
	}
	
}
