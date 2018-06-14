package com.bookstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookstore.service.TrolleyService;

@Controller
@RequestMapping(value="/user")
public class TrolleyController {
	
	@Autowired
	private TrolleyService trolleyService;
	
	@ResponseBody
	@RequestMapping(value="/trolley_add.do")
	public String insertTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			@RequestParam("num")int num, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.insertTrolley(userName, isbn, degree, num));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_delete.do")
	public String deleteTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.deleteTrolley(userName, isbn, degree));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_update.do")
	public String updateTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			@RequestParam("num")int num, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.updateTrolley(userName, isbn, degree, num));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_check.do", produces="application/json;charset=utf-8")
	public String selectTrolley(HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.selectTrolley(userName));
	}

}
