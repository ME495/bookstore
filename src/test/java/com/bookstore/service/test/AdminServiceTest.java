package com.bookstore.service.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.mapper.AdminMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.impl.AdminServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AdminServiceTest {
	@Autowired
	private AdminServiceImpl adminService;
	@Ignore
	@Test
	public void testInsertAdmin() {
		ResponseMes responseMes = adminService.insertAdmin("hzx","123456");
		System.out.println(responseMes.getMessage());
		responseMes = adminService.insertAdmin("abcd", "aajklasd");
		System.out.println(responseMes.getMessage());
		responseMes = adminService.insertAdmin("asbadfasdfasdfasdfasdf", "asdf");
		System.out.println(responseMes.getMessage());
	}
	
	@Test
	public void testDeleteAdmin() {
		adminService.insertAdmin("xiaoxiong", "123456");
		ResponseMes responseMes = adminService.deleteAdmin("xiaoxiong");
		assertEquals("删除成功", responseMes.getMessage());
	}
	
}
