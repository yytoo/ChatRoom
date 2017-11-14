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

import com.ed.server.CheckExcelServer;
import com.opensymphony.xwork2.ActionSupport;

public class CheckExcelAction extends ActionSupport {
	private Set<String> dsProduct;
	public void checkLevel(){
		File file = new File("C:/Users/Administrator/Desktop/test.xlsx");
		CheckExcelServer checkExcelServer = new CheckExcelServer();
		 dsProduct = checkExcelServer.getDsProduct();  //获取ds_product表中的product_name字段值
		 
		 int iCount=0;
		 try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			List<XSSFCell> cellList = new ArrayList<XSSFCell>(); 
			//XSSFCell cell = null;
			XSSFSheet st = wb.getSheetAt(0);
			for(int i=0; i<st.getLastRowNum(); i++){
				XSSFRow row = st.getRow(i);
				for(int j=7; j<9; j++){
					cellList.add(row.getCell(j));
					cellList.get(j).setCellType(XSSFCell.CELL_TYPE_STRING);
					
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
}
