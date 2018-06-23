package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.User;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LoginJUnit;
@TransactionConfiguration(defaultRollback=false)
public class AdminControllerTest extends LoginJUnit {
	@Autowired
	private CommonMapper commonMapper;
	/**
	 * 测试删除存在的用户
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testDeleteUser1() throws Exception {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		commonMapper.insertUser(user);
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
	 * 测试获取已存在用户信息
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testGetUser1() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc()
				.perform(post("/admin/get_user.do").param("user_name", "xiyou").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}

	/**
	 * 测试获取不存在的用户信息
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testGetUser2() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc()
				.perform(post("/admin/get_user.do").param("user_name", "testGetUser2").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		assertEquals("fail", JSON.parseObject(responseStr).get("status"));
	}

	/**
	 * 测试管理员修改用户密码
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testModifyUserPwd() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc().perform(post("/admin/pwdModify.do").param("user_name", "xiyou")
				.param("password", "xiyou").session(getMockHttpSession())).andReturn().getResponse()
				.getContentAsString();
//		 System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}

	/**
	 * 测试录入未录入的书籍
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testAddBook() throws Exception {
		adminLogin("chengjian", "123456");
		String responseStr = getMockMvc().perform(post("/admin/add_book.do")
				.param("isbn", "9787506391542")
				.param("title", "我喜欢生命本来的样子")
				.param("author", "周国平")
				.param("summary", "测试一下")
				.param("publisher", "作家出版社")
				.param("img_url", "https://img3.doubanio.com/view/subject/s/public/s29417905.jpg")
				.param("original_price", "45.0").param("degree", "2").param("num", "88").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
//		 System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}

	/**
	 * 测试录入的书籍,增加书籍数量
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testAddBook1() throws Exception {
		testAddBook();
		String responseStr = getMockMvc().perform(post("/admin/add_book.do").param("isbn", "9787506391542")
				.param("title", "我喜欢生命本来的样子").param("author", "周国平").param("summary", "测试一下")
				.param("publisher", "作家出版社")
				.param("img_url", "https://img3.doubanio.com/view/subject/s/public/s29417905.jpg")
				.param("original_price", "45.0").param("degree", "1").param("num", "10").session(getMockHttpSession()))
				.andReturn().getResponse().getContentAsString();
		// System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
	@Ignore
	@Test
	public void testDeleteBook() throws Exception {
		testAddBook();
		String responseStr = getMockMvc().perform(
					post("/admin/delete_book.do")
					.param("isbn", "9787506391542")
					.param("degree", "1")
					.session(getMockHttpSession())
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
	
	@Ignore
	@Test
	public void testUpdateBookInfo() throws Exception{
		testAddBook();
		String responseStr = getMockMvc().perform(
					post("/admin/modify_book.do")
					.param("isbn", "9787506391542")
					.param("degree", "1")
					.param("num", "15")
					.param("actual_price", "16.5")
					.session(getMockHttpSession())
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
}
