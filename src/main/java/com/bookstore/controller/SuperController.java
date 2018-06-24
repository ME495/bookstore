package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookstore.service.SuperService;

@Controller
@RequestMapping(value="/super", produces = "application/json;charset=utf-8")
public class SuperController {
	@Autowired
	private SuperService superService;

	@ResponseBody
	@RequestMapping(value = "/add_admin.do")
	public String insertAdmin(@RequestParam("admin_name") String adminName, @RequestParam("password") String password) {
		return JSON.toJSONString(superService.insertAdmin(adminName, password));
	}

	@ResponseBody
	@RequestMapping(value = "/delete_admin.do")
	public String deleteAdmin(@RequestParam("admin_name") String adminName) {
		return JSON.toJSONString(superService.deleteAdmin(adminName));
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin_list.do")
	public String listAdmins() {
		return JSON.toJSONString(superService.listAdmins());
	}
}
