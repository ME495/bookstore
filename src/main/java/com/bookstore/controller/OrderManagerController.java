package com.bookstore.controller;

import com.bookstore.entity.Order;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.entity.OrderSelector;
import com.bookstore.service.OrderManagerService;

import javax.servlet.http.HttpSession;

/**
 * 订单管理控制器
 * @author ME495
 *
 */
@Controller
@RequestMapping(produces = "application/json;charset=utf-8")
public class OrderManagerController {
	
	@Autowired
	private OrderManagerService orderManagerService;

	@Autowired
	OrderMapper orderMapper;
	
	@RequestMapping(value="/admin/order_query.do")
	@ResponseBody
	public String orderQuery(@RequestParam boolean status0,
			@RequestParam boolean status1,
			@RequestParam boolean status2,
			@RequestParam(value="order_id", required=false) Integer orderId,
			@RequestParam(value="user_name", required=false) String userName,
			@RequestParam(value="begin_time", required=false) String beginTime,
			@RequestParam(value="end_time", required=false) String endTime,
			@RequestParam int index,
			@RequestParam int size
			) {
		OrderSelector s = new OrderSelector();
		s.setStatus0(status0);
		s.setStatus1(status1);
		s.setStatus2(status2);
		s.setOrderId(orderId);
		s.setUserName(userName);
		s.setBeginTime(beginTime);
		s.setEndTime(endTime);
		s.setIndex(index);
		s.setSize(size);
		return orderManagerService.orderQuery(s, true);
	}

	@RequestMapping(value = "/user/my_order.do")
	@ResponseBody
	public String myOrder(@RequestParam boolean status0, @RequestParam boolean status1, @RequestParam boolean status2,
						  @RequestParam int index, @RequestParam int size, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		OrderSelector s = new OrderSelector();
		s.setStatus0(status0);
		s.setStatus1(status1);
		s.setStatus2(status2);
		s.setUserName(userName);
		s.setIndex(index);
		s.setSize(size);
		return orderManagerService.orderQuery(s, false);
	}

	@RequestMapping(value = "/admin/order_detail.do")
	@ResponseBody
	public String adminOrderDetail(@RequestParam("order_id") int orderId) {
		return orderManagerService.orderDetail(orderId);
	}

	@RequestMapping(value = "/user/order_detail.do")
	@ResponseBody
	public String userOrderDetail(@RequestParam("order_id") int orderId, HttpSession httpSession) {
		Order order = orderMapper.getOrder(orderId);
		String userName = (String) httpSession.getAttribute("name");
		if (!userName.equals(order.getUserName())) {
			return new ResponseMes(ResponseMes.FAIL, "你没有权限查看其他用户的订单").toJsonString();
		} else {
			return orderManagerService.orderDetail(orderId);
		}
	}

	@RequestMapping(value = "/admin/allocate_order.do")
	@ResponseBody
	public String allocateOrder(@RequestParam("order_id") int orderId) {
		return orderManagerService.allocateOrder(orderId);
	}

	@RequestMapping(value = "/user/confirm_order.do")
	@ResponseBody
	public String confirmOrder(@RequestParam("order_id") int orderId, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		Order order = orderMapper.getOrder(orderId);
		if (userName.equals(order.getUserName())) {
			return orderManagerService.confirmOrder(orderId);
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}
}
