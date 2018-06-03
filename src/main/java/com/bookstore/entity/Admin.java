package com.bookstore.entity;

import org.springframework.stereotype.Component;

@Component
public class Admin {
	private String adminName;
	private String password;

	public Admin() {
		super();
	}

	public Admin(String adminName, String password) {
		super();
		this.adminName = adminName;
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}