package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-cfg.xml"})
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void test() {
		User user = userMapper.getUser("chengjian");
		assertEquals(user.getPassword(), DigestUtils.md5Hex("123456"));
		assertEquals(user.getPhone(), "15616381480");
	}

}
