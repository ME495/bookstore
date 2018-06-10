package com.bookstore.controller;

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
	@RequestMapping(value = "/get_user.do", produces = "text/plain;charset=utf-8")
	public String getUser(@RequestParam("user_name") String userName) {
		return JSON.toJSONString(userService.getUser(userName));
	}

	@ResponseBody
	@RequestMapping(value = "/pwdModify.do", produces = "text/plain;charset=utf-8")
	public String modifyUserPwd(@RequestParam("user_name") String userName, String password) {
		return JSON.toJSONString(adminService.modifyUserPwd(userName, password));
	}
}
