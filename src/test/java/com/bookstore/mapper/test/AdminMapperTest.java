package com.bookstore.mapper.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Admin;
import com.bookstore.mapper.AdminMapper;

@SuppressWarnings("deprecation")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
public class AdminMapperTest {
	@Autowired
	AdminMapper adminMapper;
	@Ignore
	@Test
	public void testGetAdminByName() {
		System.out.println(adminMapper.getAdminByName("chengjian").getPassword());
	}

	@Test
	public void testInsertAdmin() {
		Admin admin = new Admin("hzx","123456");
		adminMapper.insertAdmin(admin);
	}
}
