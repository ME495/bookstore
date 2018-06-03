package com.bookstore.service;

/**
 * 登陆
 * @author ME495
 *
 */
public interface LoginService {
	/**
	 * 检查用户的用户名和密码是否正确
	 * @param userName
	 * @param password
	 * @return
	 * 正确则返回 true
	 * 否则返回 false
	 */
	public boolean checkUser(String userName, String password);
	
	/**
	 * 检查管理员的用户名和密码是否正确
	 * @param adminName
	 * @param password
	 * @return
	 * 正确则返回 true
	 * 否则返回 false
	 */
	public boolean checkAdmin(String adminName, String password);
}
