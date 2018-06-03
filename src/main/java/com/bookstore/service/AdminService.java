package com.bookstore.service;

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
	
	public ResponseMes insertAdmin(String adminName,String password) {
		admin.setAdminName(adminName);
		admin.setPassword(password);
		if(adminMapper.getAdminByName(adminName) == null) {
			adminMapper.insertAdmin(admin);
		}else {
			
		}
		return null;
	}
}
