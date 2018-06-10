package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Admin;
import com.bookstore.mapper.SuperMapper;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
public class SuperMapperTest {
	@Autowired
	SuperMapper superMapper;

	@Test
	public void testGetAdminByName() {
		assertEquals("chengjian",superMapper.getAdminByName("chengjian").getAdminName() );
	}

	@Test
	public void testInsertAdmin() {
		Admin admin = new Admin("hzx", "123456");
		assertEquals(1,superMapper.insertAdmin(admin));;
	}

	@Test
	public void deleteAdmin() {
		testInsertAdmin();
		assertEquals(1, superMapper.deleteAdmin("hzx"));
	}

	
}
