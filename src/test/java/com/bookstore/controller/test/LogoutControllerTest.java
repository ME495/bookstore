package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.utils.LoginJUnit;
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

public class LogoutControllerTest extends LoginJUnit {
	
	/**
	 * 测试用户注销
	 * @throws Exception
	 */
	@Test
	public void testUserLogout() throws Exception {
		userLogin("chengjian", "123456");

		MvcResult result = getMockMvc().perform(post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(getMockHttpSession())
			).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}

	/**
	 * 测试管理员注销
	 * @throws Exception
	 */
	@Test
	public void testAdminLogout() throws Exception {
		adminLogin("chengjian", "123456");

		MvcResult result = getMockMvc().perform(post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(getMockHttpSession())
			).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}
	
	/**
	 * 测试超级管理员注销
	 * @throws Exception
	 */
	@Test
	public void testSuperLogout() throws Exception {
		superLogin("Bookstore!");

		MvcResult result = getMockMvc().perform(post("/logout.do")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(getMockHttpSession())
			).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) result.getRequest().getSession().getAttribute("role");
		assertEquals(null, role);
	}
}
