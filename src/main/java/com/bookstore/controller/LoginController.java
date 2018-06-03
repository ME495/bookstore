package com.bookstore.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.entity.ErrorMessage;
import com.bookstore.message.ResponseMes;
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
	@RequestMapping(value = "/user_login.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(@RequestParam("user_name") String userName, 
			@RequestParam("password") String password,
			HttpSession httpSession) {
		if (loginService.checkUser(userName, password)) {
			httpSession.setAttribute("role", "user");
			httpSession.setAttribute("name", userName);
			return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.FAIL, new ErrorMessage("用户名或密码错误！")).toJsonString();
		}
	}
	
	@RequestMapping(value = "/admin_login.do", method = RequestMethod.POST)
	@ResponseBody
	public String adminLogin(@RequestParam("admin_name") String adminName,
			@RequestParam("password") String password,
			HttpSession httpSession) {
		if (loginService.checkAdmin(adminName, password)) {
			httpSession.setAttribute("role", "admin");
			httpSession.setAttribute("name", adminName);
			return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}
}
