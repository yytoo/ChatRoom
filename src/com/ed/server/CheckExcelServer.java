package com.ed.server;

import java.util.Set;

import com.ed.dao.CheckExcelDAO;

public class CheckExcelServer {
	public Set<String> getDsProduct(){
		CheckExcelDAO checkExcelDAO = new CheckExcelDAO();
		Set<String> dsProduct = checkExcelDAO.getDsProduct();
		return dsProduct;
	}
}
