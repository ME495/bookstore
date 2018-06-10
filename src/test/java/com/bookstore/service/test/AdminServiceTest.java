package com.bookstore.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.AdminService;
import com.bookstore.utils.BaseJUnit;

public class AdminServiceTest extends BaseJUnit {
	@Autowired
	AdminService adminService;
	@Autowired
	UserMapper userMapper;

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		userMapper.insertUser(user);
		assertEquals("success",adminService.deleteUser("xiaoxiong").getStatus());
	}
	
	@Test
	public void testModifyUserPwd() {
		adminService.modifyUserPwd("xiyou", "xiyou");
		assertEquals("success", adminService.modifyUserPwd("xiyou", "xiyou").getStatus());
	}
}
