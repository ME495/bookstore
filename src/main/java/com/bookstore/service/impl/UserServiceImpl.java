package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.LoginService;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginService loginService;

	@Override
	public ResponseMes getUser(String userName) {
		ResponseMes responseMes;
		User user = userMapper.getUser(userName);
		if (user == null) {//账号不存在,则返回错误信息
			responseMes = new ResponseMes(ResponseMes.FAIL, "用户账号不存在");
		} else {//账号存在,则返回用户信息
			responseMes = new ResponseMes(ResponseMes.SUCCESS, user);
		}
		return responseMes;
	}

	@Override
	public ResponseMes updateUserInfo(String userName,String oldPassword,String newPassword,String phone,String realName,String address) {
		ResponseMes responseMes;
		if (!loginService.checkUser(userName, oldPassword)) {//原密码错误,则返回"密码输入错误"
			responseMes = new ResponseMes(ResponseMes.FAIL,"密码输入错误");
		}else {
			User user = new User(userName, newPassword, phone, realName, address);
			userMapper.updateUserInfo(user);
			responseMes = new ResponseMes(ResponseMes.SUCCESS,"成功修改个人信息");
		}
		return responseMes;
	}
}
