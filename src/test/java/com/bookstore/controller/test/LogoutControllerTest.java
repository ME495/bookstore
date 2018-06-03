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
public class LogoutControllerTest {
	
	@Autowired
	private WebApplicationContext wac; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
	 * 测试用户注销
	 * @throws Exception
	 */
	@Test
	public void testUserLogout() throws Exception {
		//模拟用户登陆
		MvcResult result = mockMvc.perform(
				post("/user_login.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("user_name", "chengjian")
				.param("password", "123456"))
			.andExpect(status().isOk())
			.andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals("user", role);
		
		//模拟用户注销
		result = mockMvc.perform(
				post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andReturn();
		jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}

	/**
	 * 测试管理员注销
	 * @throws Exception
	 */
	@Test
	public void testAdminLogout() throws Exception {
		//模拟管理员登陆
		MvcResult result = mockMvc.perform(
				post("/admin_login.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("admin_name", "chengjian")
				.param("password", "123456"))
			.andExpect(status().isOk())
			.andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals("admin", role);
		
		//模拟管理员注销
		result = mockMvc.perform(
				post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andReturn();
		jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}
	
	/**
	 * 测试超级管理员注销
	 * @throws Exception
	 */
	@Test
	public void testSuperLogout() throws Exception {
		//模拟用户登陆
		MvcResult result = mockMvc.perform(
				post("/super_login.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("password", "Bookstore!"))
			.andExpect(status().isOk())
			.andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals("super", role);
		
		//模拟超级管理员注销
		result = mockMvc.perform(
				post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andReturn();
		jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}
}
