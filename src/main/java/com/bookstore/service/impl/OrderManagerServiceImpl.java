package com.bookstore.service.impl;

import java.util.List;

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
	
	@Override
	public String orderQuery(OrderSelector s) {
		List<Order> list = orderMapper.query(s);
		return new ResponseMes(ResponseMes.SUCCESS, list).toJsonString();
	}

}
