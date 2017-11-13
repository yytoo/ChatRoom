package com.ed.service;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class CheckExcelAction extends ActionSupport {
	private Map<String,String> dsProduct;
	public void checkLevel(){
		 dsProduct = new HashMap<String,String>();
	}
}
