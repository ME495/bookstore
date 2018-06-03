package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookstore.service.AdminService;

@Controller
@RequestMapping(value="/admin",produces= "text/html;charset=UTF-8")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@ResponseBody
	@RequestMapping("/add.do")
	public String insertAdmin(@RequestParam("admin_name") String adminName, @RequestParam("password") String password) {
		return JSON.toJSONString(adminService.insertAdmin(adminName, password));
	}

	@ResponseBody
	@RequestMapping("/delete.do")
	public String deleteAdmin(@RequestParam("admin_name") String adminName) {
		return JSON.toJSONString(adminService.deleteAdmin(adminName));
	}
}
