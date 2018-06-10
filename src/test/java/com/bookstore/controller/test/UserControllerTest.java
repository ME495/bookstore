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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-cfg.xml", "classpath:mybatis-cfg.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserControllerTest extends LoginJUnit{
	@Ignore
	@Test
	public void testInsertUser() throws Exception {
		String resposneStr = getMockMvc().perform(
					post("/add_user.do")
					.param("user_name", "xiaoxiong")
					.param("password", "asdf3456")
					.param("phone", "18890321949")
					.param("real_name", "黄趾雄")
					.param("address", "湘潭大学琴湖18栋")
				).andReturn().getResponse().getContentAsString();
		assertTrue(resposneStr.contains("注册成功"));
	}

	/**
	 * 获得已存在用户
	 * @throws Exception
	 */
	@Test
	public void testGetUser() throws Exception {
		userLogin("xiyou", "asdf3456");
		String responseStr = getMockMvc().perform(
					post("/user/get_user.do")
					.param("user_name", "xiyou")
					.session(getMockHttpSession())
				).andReturn().getResponse().getContentAsString();
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
}
