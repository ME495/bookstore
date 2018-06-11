package com.bookstore.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.message.ResponseMes;
import com.bookstore.service.impl.SuperServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SuperServiceTest {
	@Autowired
	private SuperServiceImpl superService;
	@Ignore
	@Test
	public void testInsertAdmin() {
		ResponseMes responseMes = superService.insertAdmin("hzx","123456");
		System.out.println(responseMes.getMessage());
		responseMes = superService.insertAdmin("abcd", "aajklasd");
		System.out.println(responseMes.getMessage());
		responseMes = superService.insertAdmin("asbadfasdfasdfasdfasdf", "asdf");
		System.out.println(responseMes.getMessage());
	}
	@Ignore
	@Test
	public void testDeleteAdmin() {
		superService.insertAdmin("xiaoxiong", "123456");
		ResponseMes responseMes = superService.deleteAdmin("xiaoxiong");
		assertEquals("删除成功", responseMes.getMessage());
	}
	@Test
	public void testListAdmins() {
		ResponseMes responseStr = superService.listAdmins();
//		System.out.println(responseStr.getMessage());
		assertEquals("success",responseStr.getStatus());
	}
}
