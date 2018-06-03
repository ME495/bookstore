package com.bookstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.message.ResponseMes;

/**
 * 注销控制器
 * @author ME495
 *
 */
@Controller
public class LogoutController {
	
	/**
	 * 注销
	 * @param httpSession
	 * @return
	 * 如果注销成功，则返回 {"status":"success"}
	 * 否则，返回 {"status":"fail"}
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public String logout(HttpSession httpSession) {
		String role = (String) httpSession.getAttribute("role");
		if (role != null) {
			httpSession.invalidate();
			return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}
}
