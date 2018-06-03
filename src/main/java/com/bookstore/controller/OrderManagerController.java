package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.entity.OrderSelector;
import com.bookstore.service.OrderManagerService;

/**
 * 订单管理控制器
 * @author ME495
 *
 */
@Controller
public class OrderManagerController {
	
	@Autowired
	private OrderManagerService orderManagerService;
	
	@RequestMapping(value="/admin/order_query.do", produces="text/json;charset=UTF-8")
	@ResponseBody
	public String orderQuery(@RequestParam boolean status0,
			@RequestParam boolean status1,
			@RequestParam boolean status2,
			@RequestParam(value="order_id", required=false) Integer orderId,
			@RequestParam(value="user_name", required=false) String userName,
			@RequestParam(value="admin_name", required=false) String adminName,
			@RequestParam(value="begin_time", required=false) String beginTime,
			@RequestParam(value="end_time", required=false) String endTime,
			@RequestParam int index,
			@RequestParam int page
			) {
		OrderSelector s = new OrderSelector();
		s.setStatus0(status0);
		s.setStatus1(status1);
		s.setStatus2(status2);
		s.setOrderId(orderId);
		s.setUserName(userName);
		s.setAdminName(adminName);
		s.setBeginTime(beginTime);
		s.setEndTime(endTime);
		s.setIndex(index);
		s.setPage(page);
		return orderManagerService.orderQuery(s);
	}
}
