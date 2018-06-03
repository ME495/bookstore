package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-cfg.xml", 
	"classpath:mybatis-cfg.xml", 
	"classpath:dispatcher-servlet.xml"})
@WebAppConfiguration
public class OrderManagerControllerTest {

	@Autowired
	private WebApplicationContext wac; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testOrderQuery() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/admin/order_query.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("status0", "true")
				.param("status1", "true")
				.param("status2", "true")
				.param("index", "0")
				.param("page", "20")
				)
			.andExpect(status().isOk())
			.andReturn();
		String st = result.getResponse().getContentAsString();
		System.out.println(st);
	}

}
