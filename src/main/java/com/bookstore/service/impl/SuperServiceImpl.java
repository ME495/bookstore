package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Admin;
import com.bookstore.mapper.SuperMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.SuperService;

@Service
public class SuperServiceImpl implements SuperService {
	@Autowired
	private SuperMapper superMapper;
	@Autowired
	private Admin admin;

	public ResponseMes insertAdmin(String adminName, String password) {
		ResponseMes responseMes;
		// 账号4~16位,以字母开头,包含字母,数字,下划线,密码6~16位包含字母,数字,下划线
		// 判断账号，密码格式是否正确
		if (adminName.matches("[a-zA-Z][a-zA-Z0-9_]{3,15}") && password.matches("[a-zA-Z0-9_]{6,16}")) {
			admin.setAdminName(adminName);
			admin.setPassword(password);
			if (superMapper.getAdminByName(adminName) == null) { // 若账户不存在，则添加,否则，返回错误信息
				superMapper.insertAdmin(admin);
				responseMes = new ResponseMes("success", "创建成功");
			} else {
				responseMes = new ResponseMes("fail", "账号已存在");
			}
		} else {
			responseMes = new ResponseMes("fail", "账号或密码格式错误");
		}
		return responseMes;
	}
	
	@Override
	public ResponseMes deleteAdmin(String adminName) {
		superMapper.deleteAdmin(adminName);
		return new ResponseMes(ResponseMes.SUCCESS, "删除成功");
	}

	@Override
	public ResponseMes listAdmins() {
		ArrayList<Admin> adminList = superMapper.listAdmins();
		return new ResponseMes(ResponseMes.SUCCESS,adminList);
	}
	
	
}
