package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import com.bookstore.utils.LoginJUnit;
import com.bookstore.utils.MockMvcJUnit;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;

/**
 * 
 * @author ME495
 *
 */
public class LoginControllerTest extends LoginJUnit {
	
	@Test
	public void testUserLogin() throws UnsupportedEncodingException, Exception {
		String st = userLogin("chengjian", "123456");
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) getMockHttpSession().getAttribute("role");
		assertEquals("user", role);
	}
	
	@Test
	public void testAdminLogin() throws UnsupportedEncodingException, Exception {
		String st = adminLogin("chengjian", "123456");
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) getMockHttpSession().getAttribute("role");
		assertEquals("admin", role);
	}
	
	@Test
	public void testSuperLogin() throws Exception {
		String st = superLogin("Bookstore!");
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) getMockHttpSession().getAttribute("role");
		assertEquals("super", role);
	}
}
