package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-cfg.xml", 
	"classpath:mybatis-cfg.xml", 
	"classpath:dispatcher-servlet.xml"})
@WebAppConfiguration
public class LoginControllerTest {

	@Autowired
	private WebApplicationContext wac; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void userLoginTest() throws UnsupportedEncodingException, Exception {
		String st = mockMvc.perform(
				post("/user_login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("user_name", "chengjian")
				.param("password", "123456"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
		System.out.println(st);
	}

}
