package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean checkUser(String userName, String password) {
		User user = userMapper.getUser(userName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}
