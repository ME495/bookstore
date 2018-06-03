package com.bookstore.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bookstore.entity.Admin;
import com.bookstore.service.AdminService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;
	@Test
	public void testInsertAdmin() {
		adminService.insertAdmin("hzx","123456");
	}

}
