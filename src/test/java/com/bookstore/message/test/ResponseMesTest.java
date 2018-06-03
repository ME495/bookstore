package com.bookstore.message.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.ErrorMessage;
import com.bookstore.entity.User;
import com.bookstore.message.ResponseMes;

public class ResponseMesTest {

	@Test
	public void test() {
		User user = new User();
		user.setUserName("chengjian");
		user.setPassword("123456");
		user.setPhone("15616381480");
//		ResponseMes mes = new ResponseMes(ResponseMes.getSUCCESS(), new ErrorMessage("404"));
//		System.out.println(mes.toString());
	}

}
