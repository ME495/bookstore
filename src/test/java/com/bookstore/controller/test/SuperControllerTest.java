package com.bookstore.controller.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.bookstore.utils.LoginJUnit;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SuperControllerTest extends LoginJUnit {

	@Ignore
	@Test
	public void testInsertAdmin() throws Exception {
		superLogin("Bookstore!");
		String responseString = getMockMvc()
				.perform(post("/super/add.do").param("admin_name", "a2145").param("password", "123456").session(getMockHttpSession())).andReturn()
				.getResponse().getContentAsString();
		assertTrue(responseString.contains("创建成功"));
	}
	
	
	@Test
	public void testDeleteAdmin() throws Exception {
		testInsertAdmin();
		String responseString = getMockMvc()
				.perform(post("/super/delete.do").param("admin_name", "a2145").session(getMockHttpSession())).andReturn()
				.getResponse().getContentAsString();
		assertTrue(responseString.contains("删除成功"));
	}
}
