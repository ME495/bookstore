package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping("/user_login")
	@ResponseBody
	public String userLogin(@RequestParam("user_name") String userName, 
			@RequestParam("password") String password) {
		System.out.println("userLogin");
		return "ok";
	}
	
}
