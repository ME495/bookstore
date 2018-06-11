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
	public ResponseMes insertUser(String userName, String password, String phone, String realName, String address) {
		ResponseMes responseMes;
		// 如果手机格式不正确,则返回"手机号码格式错误"
		if (userName.matches("[a-zA-Z][a-zA-Z0-9_]{3,15}") && password.matches("[a-zA-Z0-9_]{6,16}")
				&& phone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")) {
			if (userMapper.getUser(userName) == null) {// 如果账号已存在,则返回"该账号已存在"
				User user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setPhone(phone);
				user.setRealName(realName);
				user.setAddress(address);
				userMapper.insertUser(user);
				responseMes = new ResponseMes(ResponseMes.SUCCESS, "注册成功");
			} else {
				responseMes = new ResponseMes(ResponseMes.FAIL, "该账号已存在");
			}
		} else {
			responseMes = new ResponseMes(ResponseMes.FAIL, "格式错误");
		}
		return responseMes;
	}

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
