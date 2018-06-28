package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LoginJUnit;
import com.mysql.fabric.xmlrpc.base.Param;

public class UserControllerTest extends LoginJUnit{
	public void testInsertUser() throws Exception {
		String resposneStr = getMockMvc().perform(
					post("/signup.do")
					.param("user_name", "xiaoxiong")
					.param("password", "123456")
					.param("phone", "18890321949")
					.param("real_name", "黄趾雄")
					.param("address", "湘潭大学琴湖18栋")
				).andReturn().getResponse().getContentAsString();
//		System.out.println(resposneStr);
		assertTrue(resposneStr.contains("注册成功"));
	}

	/**
	 * 获得已存在用户
	 * @throws Exception
	 */
	@Test
	public void testGetUser() throws Exception {
		testInsertUser();
		userLogin("xiaoxiong", "123456");
		String responseStr = getMockMvc().perform(
					post("/user/get_user.do")
					.param("user_name", "xiaoxiong")
					.session(getMockHttpSession())
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
	
	@Test
	public void testModifyUserInfo() throws Exception{
		testInsertUser();
		userLogin("xiaoxiong", "123456");
		String resposneStr = getMockMvc().perform(
				post("/user/modify_info.do")
				.param("user_name", "xiaoxiong")
				.param("old_password", "123456")
				.param("new_password", "654321")
				.param("phone", "12345678910")
				.param("real_name", "呵呵")
				.param("address", "湘潭大学琴湖18栋")
				.session(getMockHttpSession())
			).andReturn().getResponse().getContentAsString();
//		System.out.println(resposneStr);
		assertEquals("success", JSON.parseObject(resposneStr).get("status"));
	}
}
