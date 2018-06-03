package com.bookstore.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Admin;
import com.bookstore.entity.User;
import com.bookstore.mapper.AdminMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public boolean checkUser(String userName, String password) {
		User user = userMapper.getUser(userName);
		if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAdmin(String adminName, String password) {
		Admin admin = adminMapper.getAdminByName(adminName);
		if (admin != null && admin.getPassword().equals(DigestUtils.md5Hex(password))) {
			return true;
		} else {
			return false;
		}
	}

	
}
