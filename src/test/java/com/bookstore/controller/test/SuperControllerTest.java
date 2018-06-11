package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bookstore.utils.LoginJUnit;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SuperControllerTest extends LoginJUnit {
	@Ignore
	@Test
	public void testInsertAdmin() throws Exception {
		superLogin("Bookstore!");
		String responseString = getMockMvc().perform(post("/super/add.do").param("admin_name", "a2145")
				.param("password", "123456").session(getMockHttpSession())).andReturn().getResponse()
				.getContentAsString();
		assertTrue(responseString.contains("创建成功"));
	}

	@Ignore
	@Test
	public void testDeleteAdmin() throws Exception {
		testInsertAdmin();
		String responseString = getMockMvc()
				.perform(post("/super/delete.do").param("admin_name", "a2145").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertTrue(responseString.contains("删除成功"));
	}

	@Test
	public void testListAdmins() throws Exception {
		superLogin("Bookstore!");
		String responseStr = getMockMvc().perform(post("/super/admin_list.do").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
}
