package com.bookstore.service;

public interface LoginService {
	public boolean checkUser(String userName, String password);
	public boolean checkAdmin(String adminName, String password);
}
