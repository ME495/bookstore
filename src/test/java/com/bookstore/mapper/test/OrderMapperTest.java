package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.utils.BaseJUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderSelector;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.message.ResponseMes;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class OrderMapperTest extends BaseJUnit {

	@Autowired
	OrderMapper orderMapper;
	
	@Test
	public void testQuery() {
		OrderSelector s = new OrderSelector();
		s.setStatus0(true);
		s.setStatus1(true);
		s.setStatus2(true);
//		s.setUserName("rigzyxwl");
		List<Order> list = orderMapper.query(s, true);
		System.out.println(new ResponseMes(ResponseMes.SUCCESS, list).toJsonString());
	}

	@Test
	public void testSetOrderStatus() {
		assertEquals(1, orderMapper.setOrderStatus(103, 1));
	}

	@Test
	public void testGetOrder() {
		Order order = orderMapper.getOrder(103);
		System.out.println(JSONObject.toJSONString(order));
	}
	
}
