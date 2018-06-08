package com.bookstore.service.impl;

import java.util.List;

import com.bookstore.entity.OrderDetail;
import com.bookstore.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Order;
import com.bookstore.entity.OrderSelector;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.OrderManagerService;

/**
 * 
 * @author ME495
 *
 */
@Service
public class OrderManagerServiceImpl implements OrderManagerService {

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OrderDetailMapper orderDetailMapper;
	
	@Override
	public String orderQuery(OrderSelector s, boolean isAsc) {
		List<Order> list = orderMapper.query(s, isAsc);
		return new ResponseMes(ResponseMes.SUCCESS, list).toJsonString();
	}

	@Override
	public String orderDetail(int orderId) {
		List<OrderDetail> list = orderDetailMapper.getOrderDetail(orderId);
		ResponseMes mes = new ResponseMes(ResponseMes.SUCCESS, list);
		return mes.toJsonString();
	}


}
