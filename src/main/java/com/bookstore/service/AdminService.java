package com.bookstore.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Admin;
import com.bookstore.mapper.AdminMapper;
import com.bookstore.message.ResponseMes;

@Service
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private Admin admin;

	public ResponseMes insertAdmin(String adminName, String password) {
		ResponseMes responseMes;
		//账号4~16位,以字母开头,包含字母,数字,下划线,密码6~16位包含字母,数字,下划线
		// 判断账号，密码格式是否正确
		if (adminName.matches("[a-zA-Z][a-zA-Z0-9_]{3,15}") && password.matches("[a-zA-Z0-9_]{6,16}")) {
			admin.setAdminName(adminName);
			admin.setPassword(password);
			if (adminMapper.getAdminByName(adminName) == null) { // 若账户不存在，则添加,否则，返回错误信息
				adminMapper.insertAdmin(admin);
				responseMes = new ResponseMes("success","创建成功");
			} else {
				responseMes = new ResponseMes("fail", "账号已存在");
			}
		} else {
			responseMes = new ResponseMes("fail", "账号或密码格式错误");
		}
		return responseMes;
	}
}
