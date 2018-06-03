package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-cfg.xml"})
public class OrderMapperTest {

	@Autowired
	OrderMapper orderMapper;
	
	@Test
	public void testQuery() {
		OrderSelector s = new OrderSelector();
		s.setStatus0(true);
		s.setStatus1(true);
		s.setStatus2(true);
//		s.setUserName("rigzyxwl");
		List<Order> list = orderMapper.query(s);
		System.out.println(new ResponseMes(ResponseMes.SUCCESS, list).toJsonString());
	}

}
