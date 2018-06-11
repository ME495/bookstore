package com.bookstore.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.AdminService;
import com.bookstore.utils.BaseJUnit;

public class AdminServiceTest extends BaseJUnit {
	@Autowired
	AdminService adminService;
	@Autowired
	UserMapper userMapper;

	private String isbn = "9787506391542";
	private String title = "我喜欢生命本来的样子";
	private String author = "周国平";
	private String publisher = "作家出版社";
	private String summary = "测试一下";
	private String imgUrl = "https://img3.doubanio.com/view/subject/s/public/s29417905.jpg";
	
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

	
	@Test
	public void testModifyUserPwd() {
		adminService.modifyUserPwd("xiyou", "xiyou");
		assertEquals("success", adminService.modifyUserPwd("xiyou", "xiyou").getStatus());
	}

	/**
	 * 插入不存在的图书
	 */
	
	@Test
	public void testAddBook() {
		ResponseMes responseMes = adminService.addBook(isbn, title, author, publisher, summary, imgUrl, 45.0, 1, 88);
		assertEquals("success", responseMes.getStatus());
	}

	/**
	 * 插入已有图书
	 */
	@Test
	public void testAddBook2() {
		testAddBook();
		ResponseMes responseMes = adminService.addBook(isbn, title, author, publisher, summary, imgUrl, 45.0, 1, 88);
		assertEquals("success", responseMes.getStatus());
	}
	
	@Test
	public void testDeleteBook() {
		testAddBook();
		assertEquals("success", adminService.deleteBook(isbn, 1).getStatus());
	}
	
	@Test
	public void testUpdateBookInfo() {
		testAddBook();
		adminService.updateBookInfo(isbn, 1, 10, 16.0);
	}
}
