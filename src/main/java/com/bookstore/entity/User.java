package com.bookstore.entity;

/**
 * 
 * @author ME495
 *
 */
public class User {
	private String userName;
	private String password;
	private String phone;
	private String realName;
	private String address;

	public User() {
		super();
	}

	public User(String userName, String password, String phone, String realName, String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.realName = realName;
		this.address = address;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
