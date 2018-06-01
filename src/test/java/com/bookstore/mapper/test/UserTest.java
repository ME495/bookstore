package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;

public class UserTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user = userMapper.getUser("chengjian");
		System.out.println(user.getPassword());
	}

}
