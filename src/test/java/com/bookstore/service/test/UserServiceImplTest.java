package com.bookstore.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.message.ResponseMes;
import com.bookstore.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceImplTest {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	public void testInsetUser() {
		ResponseMes responseMes = userServiceImpl.insertUser("xiaoxiong", "123456", "18880191929");
		assertTrue(responseMes.getMessage().equals("注册成功"));
	}

}
