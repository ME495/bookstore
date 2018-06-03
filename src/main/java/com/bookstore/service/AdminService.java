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
	private ResponseMes responseMes;

	public ResponseMes insertAdmin(String adminName, String password) {
		responseMes = new ResponseMes();
		if (adminName.matches("[a-zA-Z][a-zA-Z0-9_]{0,15}") && password.matches("[a-zA-Z][a-zA-Z0-9_]{0,15}")) {
			admin.setAdminName(adminName);
			admin.setPassword(password);
			// 判断账号，密码格式是否正确
			if (adminMapper.getAdminByName(adminName) == null) { // 若账户不存在，则添加,否则，返回错误信息
				System.out.println(adminMapper.getAdminByName(adminName).getAdminName());
				adminMapper.insertAdmin(admin);
			} else {
				System.out.println("账号已经存在");
			}
		} else {
		}
		return responseMes;
	}
}
