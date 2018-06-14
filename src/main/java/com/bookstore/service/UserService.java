package com.bookstore.service;

import com.bookstore.entity.User;
import com.bookstore.message.ResponseMes;

public interface UserService {
	/**用户获取自己的信息
	 * @param userName 用户的账号
	 * @return 成功或失败信息,包含用户的实体类
	 */
	public ResponseMes getUser(String userName);

	
	/**
	 * 修改用户个人信息
	 * @param userName 账号
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param phone  联系方式
	 * @param realName 真实名字
	 * @param address 地址
	 * @return 更改成功,或失败信息
	 */
	public ResponseMes updateUserInfo(String userName,String oldPassword,String newPassword,String phone,String realName,String address);
}
