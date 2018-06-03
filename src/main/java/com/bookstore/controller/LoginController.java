package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.service.LoginService;

/**
 * 登陆控制器
 * @author ME495
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 用户登陆，验证用户名和密码，并返回json格式的组字符串，验证通过则创建session
	 * @param userName
	 * @param password
	 * @return
	 * 验证通过时返回
	 */
	@RequestMapping("/user_login")
	@ResponseBody
	public String userLogin(@RequestParam("user_name") String userName, 
			@RequestParam("password") String password) {
		if (loginService.checkUser(userName, password)) {
			return "ok";
		} else {
			return "no";
		}
	}
	
}
