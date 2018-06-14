package com.bookstore.mapper;

import org.springframework.stereotype.Repository;

import com.bookstore.entity.User;

@Repository
public interface UserMapper {
	public User getUser(String userName);
	
	/**
	 * 根据用户账号,修改用户信息
	 * @param user 用户实体
	 * @return 成功返回1,失败返回0
	 */
	public int updateUserInfo(User user);
}
