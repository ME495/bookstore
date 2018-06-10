package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.utils.LoginJUnit;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AdminControllerTest extends LoginJUnit {
	@Autowired
	private UserMapper userMapper;

	
	/**
	 * 测试删除存在的用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUser1() throws Exception {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		userMapper.insertUser(user);
		adminLogin("chengjian", "123456");
		String resStr = getMockMvc()
				.perform(post("/admin/delete_user.do").param("user_name", "xiaoxiong").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertTrue(resStr.contains("success"));
	}

	/**
	 * 测试删除不存在的用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUser2() throws Exception {
		adminLogin("chengjian", "123456");
		String resStr = getMockMvc()
				.perform(post("/admin/delete_user.do").param("user_name", "xiaoxiong").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertTrue(resStr.contains("fail"));
	}

	/**
	 * 测试已存在用户
	 * 
	 * @throws Exception
	 */
	public void testGetUser1() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc()
				.perform(post("/admin/get_user.do").param("user_name", "xiyou").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}

	/**
	 * 测试不存在的用户
	 * 
	 * @throws Exception
	 */
	public void testGetUser2() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc()
				.perform(post("/admin/get_user.do").param("user_name", "testGetUser2").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertEquals("fail", JSON.parseObject(responseStr).get("status"));
	}

	@Test
	public void testModifyUserPwd() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc()
					.perform(post("/admin/pwdModify.do")
					.param("user_name", "xiyou")
					.param("password", "xiyou")
					.session(getMockHttpSession())
					).andReturn().getResponse().getContentAsString();
		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
}
