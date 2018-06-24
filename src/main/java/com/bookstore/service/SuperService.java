package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface SuperService {
	/**超级管理员新增管理员账户
	 * @param adminName 管理员账号
	 * @param password 管理员密码
	 * @return 成功或失败信息
	 */
	public ResponseMes insertAdmin(String adminName, String password);

	/**超级管理员根据管理员账号删除管理员
	 * @param adminName 管理员账号
	 * @return 成功或失败信息
	 */
	public ResponseMes deleteAdmin(String adminName);
	
	/** 获得所有管理员
	 * @return 成功或失败的信息,包含管理员列表
	 */
	public ResponseMes listAdmins();
}
