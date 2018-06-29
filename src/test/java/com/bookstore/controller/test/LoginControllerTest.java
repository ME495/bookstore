package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LoginJUnit;

/**
 * 
 * @author ME495
 *
 */
public class LoginControllerTest extends LoginJUnit {
	@Ignore
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
	@Ignore
	@Test
	public void testSuperLogin() throws Exception {
		String st = superLogin("Bookstore!");
		JSONObject jsonObject = JSONObject.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		String role = (String) getMockHttpSession().getAttribute("role");
		assertEquals("super", role);
	}

	@Ignore
	@Test
	public void testLoginCheck1() throws Exception {
		userLogin("chengjian", "123456");
		MvcResult result = getMockMvc().perform(post("/check_login.do")
				.session(getMockHttpSession())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSON.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		JSONObject jsonObject2 = jsonObject.getJSONObject("message");
		assertEquals("chengjian", jsonObject2.getString("name"));
		assertEquals("user", jsonObject2.getString("role"));
	}
	@Ignore
	@Test
	public void testLoginCheck2() throws Exception {
		superLogin("Bookstore!");
		MvcResult result = getMockMvc().perform(post("/check_login.do")
				.session(getMockHttpSession())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
		JSONObject jsonObject = JSON.parseObject(st);
		assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
		JSONObject jsonObject2 = jsonObject.getJSONObject("message");
		assertEquals("super", jsonObject2.getString("name"));
		assertEquals("super", jsonObject2.getString("role"));
//		System.out.println(result.getResponse().getContentAsString());
	}
}
