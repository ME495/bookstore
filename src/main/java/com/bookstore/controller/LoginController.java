package com.bookstore.controller;

import javax.servlet.http.HttpSession;

import com.bookstore.entity.Identity;
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
@RequestMapping(produces = "application/json;charset=utf-8")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 用户登陆
	 * @param userName
	 * @param password
	 * @param httpSession
	 * @return
	 * 如果登陆成功，则返回 {"status":"success"}
	 * 否则，返回 {"status":"fail"}
	 */
	@RequestMapping(value = "/user_login.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(@RequestParam("user_name") String userName, 
			@RequestParam("password") String password,
			HttpSession httpSession) {
		System.out.println("ok");
		if (loginService.checkUser(userName, password)) {
			httpSession.setAttribute("role", "user");
			httpSession.setAttribute("name", userName);
			System.out.println("login success!");
			return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.FAIL, "用户名或密码错误！").toJsonString();
		}
	}
	
	/**
	 * 管理员登陆
	 * @param adminName
	 * @param password
	 * @param httpSession
	 * @return
	 * 如果登陆成功，则返回 {"status":"success"}
	 * 否则，返回 {"status":"fail"}
	 */
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
			return new ResponseMes(ResponseMes.FAIL, "用户名或密码错误！").toJsonString();
		}
	}
	
	/**
	 * 超级管理员登陆
	 * @param password
	 * @param httpSession
	 * @return
	 * 如果登陆成功，则返回 {"status":"success"}
	 * 否则，返回 {"status":"fail"}
	 */
	@RequestMapping(value = "/super_login.do", method = RequestMethod.POST)
	@ResponseBody
	public String superLogin(@RequestParam("password") String password,
							 HttpSession httpSession) {
		if (loginService.checkSuper(password)) {
			httpSession.setAttribute("role", "super");
			httpSession.setAttribute("name", "super");
			return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.FAIL, "密码错误！").toJsonString();
		}
	}

	@RequestMapping(value = "/check_login.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(HttpSession httpSession) {
		String name = (String) httpSession.getAttribute("name");
		String role = (String) httpSession.getAttribute("role");
		if (name == null || role == null) {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.SUCCESS, new Identity(name, role)).toJsonString();
		}
	}
}
