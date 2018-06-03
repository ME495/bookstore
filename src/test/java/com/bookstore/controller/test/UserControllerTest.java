package com.bookstore.controller.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;

import org.junit.Before;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-cfg.xml", "classpath:mybatis-cfg.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testInsertUser() throws Exception {
		String resposneStr = mockMvc.perform(
				post("/add_user.do").param("user_name", "xiaoxiong").param("password", "asdf3456").param("phone", "18890321949")
				).andReturn().getResponse()
				.getContentAsString();
		assertTrue(resposneStr.contains("注册成功"));
	}

}
