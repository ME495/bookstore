package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.mapper.AdminMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseMes deleteUser(String userName) {
		ResponseMes responseMes;
		if(userMapper.getUser(userName) == null) {//用户不存在
			responseMes = new ResponseMes(ResponseMes.FAIL,"该用户不存在");
		}else {
			adminMapper.deleteUser(userName);
			responseMes = new ResponseMes(ResponseMes.SUCCESS,"删除成功");
		}
		return responseMes;
	}

	@Override
	public ResponseMes modifyUserPwd(String userName, String password) {
		ResponseMes responseMes;
		if(userMapper.getUser(userName) == null) {//用户不存在
			responseMes = new ResponseMes(ResponseMes.FAIL,"该用户不存在");
		}else {
			adminMapper.modifyUserPwd(userName, password);
			responseMes = new ResponseMes(ResponseMes.SUCCESS,"修改成功");
		}
		return responseMes;
	}
}
