package com.cr.bean;

/**
 * CustomerInfor entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 325814427802311366L;
	// Fields

	private Integer id;
	private String username;
	private String password;
	
	public User(){

	}
	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	// Constructors
	
	

}