package com.bookstore.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookstore.service.AdminService;
import com.bookstore.service.UserService;

@Controller
@RequestMapping(value = "/admin", produces = "application/json;charset=utf-8")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/delete_user.do")
	public String deleteUser(@RequestParam("user_name") String userName) {
		return JSON.toJSONString(adminService.deleteUser(userName));
	}

	@ResponseBody
	@RequestMapping(value = "/get_user.do")
	public String getUser(@RequestParam("user_name") String userName) {
		return JSON.toJSONString(userService.getUser(userName));
	}

	@ResponseBody
	@RequestMapping(value = "/pwdModify.do")
	public String modifyUserPwd(@RequestParam("user_name") String userName, String password) {
		return JSON.toJSONString(adminService.modifyUserPwd(userName, password));
	}

	@ResponseBody
	@RequestMapping(value = "/add_book.do")
	public String addBook(String isbn, String title, String author, String publisher, String summary,
			@RequestParam("img_url") String imgUrl, @RequestParam("original_price") double originalPrice, int degree,
			int num) {
		return JSON.toJSONString(
				adminService.addBook(isbn, title, author, publisher, summary, imgUrl, originalPrice, degree, num));
	}

	@ResponseBody
	@RequestMapping(value = "/delete_book.do")
	public String deleteBook(String isbn, int degree) {
		return JSON.toJSONString(adminService.deleteBook(isbn, degree));
	}
	
	@ResponseBody
	@RequestMapping(value="/modify_book.do")
	public String updateBookInfo(String isbn, int degree, int num, @Param("actual_price")Double actualPrice) {
		return JSON.toJSONString(adminService.updateBookInfo(isbn, degree, num, actualPrice));
	}
}
