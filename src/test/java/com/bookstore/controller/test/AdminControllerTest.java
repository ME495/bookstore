package com.bookstore.controller.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-cfg.xml", "classpath:mybatis-cfg.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AdminControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Ignore
	@Test
	public void testInsertAdmin() throws Exception {
		String responseString = mockMvc
				.perform(post("/admin/add.do").param("admin_name", "a2145").param("password", "123456")).andReturn()
				.getResponse().getContentAsString();
		assertTrue(responseString.contains("创建成功"));
	}
	
	
	@Test
	public void testDeleteAdmin() throws Exception {
		testInsertAdmin();
		String responseString = mockMvc
				.perform(post("/admin/delete.do").param("admin_name", "a2145")).andReturn()
				.getResponse().getContentAsString();
		assertTrue(responseString.contains("删除成功"));
	}
}
