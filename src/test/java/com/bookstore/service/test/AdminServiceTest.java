package com.bookstore.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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

	@Ignore
	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		userMapper.insertUser(user);
		assertEquals("success", adminService.deleteUser("xiaoxiong").getStatus());
	}

	@Ignore
	@Test
	public void testModifyUserPwd() {
		adminService.modifyUserPwd("xiyou", "xiyou");
		assertEquals("success", adminService.modifyUserPwd("xiyou", "xiyou").getStatus());
	}

	/**
	 * 插入不存在的图书
	 */
	@Ignore
	@Test
	public void testAddBook() {
		adminService.addBook("9787506391542", "我喜欢生命本来的样子", "周国平", "作家出版社", "测试一下",
				"https://img3.doubanio.com/view/subject/s/public/s29417905.jpg", 45.0, 1, 88);
	}

	/**
	 * 插入已有图书
	 */
	@Ignore
	@Test
	public void testAddBook2() {
		adminService.addBook("9787506391542", "我喜欢生命本来的样子", "周国平", "作家出版社", "测试一下",
				"https://img3.doubanio.com/view/subject/s/public/s29417905.jpg", 45.0, 1, 88);
		adminService.addBook("9787506391542", "我喜欢生命本来的样子", "周国平", "作家出版社", "测试一下",
				"https://img3.doubanio.com/view/subject/s/public/s29417905.jpg", 45.0, 1, 88);
	}

	@Test
	public void testDeleteBook() {
		testAddBook();
		assertEquals("success", adminService.deleteBook("9787506391542", 1).getStatus());
	}
}
