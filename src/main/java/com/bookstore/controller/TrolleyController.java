package com.bookstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.service.TrolleyService;

@Controller
@RequestMapping(value="/user")
public class TrolleyController {
	
	public final static int PAYMENT_APPROVED = 0;
	public final static int PAYMENT_CANCELLED = 1;
	public final static int PAYMENT_ERROR = 2;
	
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
	
	@ResponseBody
	@RequestMapping(value="/get_price_to_pay.do")
	public double getPrice2pay(String trolleyMsg) {
		return trolleyService.getPrice2Pay(trolleyMsg);
	}
	
	@RequestMapping(value="/return_payment_status.do")
	public void recvPaymentStatus(String paymentMsg, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		JSONObject paymentMsgObj = JSON.parseObject(paymentMsg);
		if(paymentMsgObj.get("status").equals("success")) {
			trolleyService.recvPaymentStatus(userName, PAYMENT_APPROVED);
		} else {
			if(paymentMsgObj.get("msg").equals("paymentCancelled")) {
				trolleyService.recvPaymentStatus(userName, PAYMENT_CANCELLED);
			} else {
				trolleyService.recvPaymentStatus(userName, PAYMENT_ERROR);
			}
		}
	}

}
