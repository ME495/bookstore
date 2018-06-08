package com.bookstore.message.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.ErrorMessage;
import com.bookstore.entity.User;
import com.bookstore.message.ResponseMes;

import java.util.ArrayList;
import java.util.List;

public class ResponseMesTest {

	@Test
	public void test() {
		User user = new User();
		user.setUserName("chengjian");
		user.setPassword("123456");
		user.setEmail("15616381480");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		ResponseMes mes = new ResponseMes(ResponseMes.SUCCESS, list);
		System.out.println(mes.toJsonString());
	}

}
