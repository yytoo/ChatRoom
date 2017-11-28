package com.ed.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;


import com.cr.util.HibernateSessionFactory;
import com.ed.bean.Company;
import com.ed.bean.Member;
import com.ed.server.CheckExcelServer;
import com.opensymphony.xwork2.ActionSupport;

public class CheckExcelAction extends ActionSupport {
	private Set<String> dsProduct;
	public void checkExcel(){
		CheckExcelAction checkExcelAction = new CheckExcelAction();
		checkExcelAction.checkLevel();
	}
	public void checkLevel(){
		File file = new File("C:/Users/Administrator/Desktop/test.xlsx");				 
		 //int iCount=0;
		 try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(is);			
			//XSSFCell cell = null;
			XSSFSheet st = wb.getSheetAt(0);
			for(int i=1; i<st.getLastRowNum()+1; i++){
				List<XSSFCell> cellList = new ArrayList<XSSFCell>(); 
				XSSFRow row = st.getRow(i);
				if(row.getCell(0)==null || "".equals(row.getCell(0))){
					continue;
				}				
				for(int j=0; j<16; j++){   //获取用户信息，0-5用户信息，6-15级别，15-25牌号	
					cellList.add(row.getCell(j));
					cellList.get(j).setCellType(XSSFCell.CELL_TYPE_STRING);
					//count++;	
				}	
			readCell(cellList,i);				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}	
	
	public List<List> readCell(List<XSSFCell> aCellList, int i){
		CheckExcelServer checkExcelServer = new CheckExcelServer();
		dsProduct = checkExcelServer.getDsProduct();  //获取ds_product表中的product_name字段值
		int iLevelFlag=0;
		int iIsMemberExist=0;
		List<String> warm = new ArrayList<String>();
		List<String> error =new ArrayList<String>();
		int iCompanyId=0;
		String sPhoneNum="";
		String sCompanyName="";
		String sShortName="";
		String sUserName="";
		Session session = HibernateSessionFactory.getSession();
		iCompanyId=Integer.parseInt(aCellList.get(0).getStringCellValue());
		sPhoneNum=aCellList.get(5).getStringCellValue();
		Member member = checkExcelServer.getMember(sPhoneNum, session);
		if(member==null){               //未找到该username的用户则跳出循环
			error.add("第"+i+"行，"+iCompanyId+","+sPhoneNum+"客户不存在");
			System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+"客户不存在");
			return null;
		}
		
		iIsMemberExist=checkExcelServer.checkMember(iCompanyId,member.getId(),session);
		if(iIsMemberExist==0){                  //不存在改公司下的该人员，插入error并,并不对当前记录进行操作
			error.add("第"+i+"行，"+iCompanyId+","+sPhoneNum+"不存在");
			System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+"不存在");
			return null;
		}
		System.out.println(iIsMemberExist+"第"+i+"行，"+iCompanyId+","+sPhoneNum+"存在");
		
		
		Company company=checkExcelServer.getCompany(iCompanyId, session);				
		if(company==null){                       //检查公司这个id是否存在
			error.add("第"+i+"行，"+iCompanyId+","+sPhoneNum+"公司不存在");
			System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+"公司不存在");
			return null;
		}
		
		sCompanyName=aCellList.get(1).getStringCellValue();   //检测公司名，公司简称和用户名,存入数组，循环检查
		sShortName=aCellList.get(2).getStringCellValue();
		sUserName=aCellList.get(4).getStringCellValue();
		String[] companyInfor = {sCompanyName,sShortName,sUserName};
		for(int k=0; k<3; k++){
			String sCompanyInfor = companyInfor[k];
			if(sCompanyInfor!=null && (!"".equals(sCompanyInfor)) && sCompanyInfor.equals(company.getName())){
				System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+","+sCompanyInfor+"符合");
			}else{
				error.add("第"+i+"行，"+iCompanyId+","+sPhoneNum+"不符");
				System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+","+sCompanyInfor+"不符合");
			}
		}	
		
		for(int n=6; n<16; n++){     //判断级别是否在dsProduct中
			String sLevel = aCellList.get(n).getStringCellValue();
			for(String ds:dsProduct){
				if(ds.equals(sLevel)){
					iLevelFlag=0;
					break;
				}
				iLevelFlag=1;
			}
			if(iLevelFlag==1){
				error.add("第"+i+"行，"+iCompanyId+","+sPhoneNum+"级别"+aCellList.get(n)+"不存在");
				System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+"级别"+aCellList.get(n)+"不存在");
			}else{
				System.out.println("第"+i+"行，"+iCompanyId+","+sPhoneNum+"级别"+aCellList.get(n)+"存在");
			}
			
			
		}
		
		
		return null;
	}
}
