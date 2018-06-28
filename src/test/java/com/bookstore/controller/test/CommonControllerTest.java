package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.bookstore.utils.MockMvcJUnit;

public class CommonControllerTest extends MockMvcJUnit {
	
	@Test
	public void testGetDegreesByIsbn() throws Exception{
		String responseStr = getMockMvc().perform(
					post("/book_degrees.do")
					.param("isbn", "9787100155724")
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success", JSON.parseObject(responseStr).get("status"));
	}
	
	@Test
	public void testGetBooks() throws Exception{
		String responseStr = getMockMvc().perform(
					post("/browse_book.do")
					.param("index", "4")
					.param("size","3")
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success",JSON.parseObject(responseStr).get("status"));
	}
	
	
	@Test
	public void testGetBooksByKeyWord() throws Exception{
		String responseStr = getMockMvc().perform(
				post("/search_book.do")
				.param("key_word", "记")
				.param("index", "1")
				.param("size","8")
			).andReturn().getResponse().getContentAsString();
//	System.out.println(responseStr);
	assertEquals("success",JSON.parseObject(responseStr).get("status"));
	}
	
	@Test
	public void testGetBookByIsbnAndDegree() throws UnsupportedEncodingException, Exception {
		String responseStr = getMockMvc().perform(
					post("/book_detail.do")
					.param("isbn", "9787100155724")
					.param("degree", "1")
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals("success",JSON.parseObject(responseStr).get("status"));
	}
	
	/**
     * 用户注册测试
     * @throws Exception
     */
    @Test
    public void testInsertUser() throws Exception {
        String resposneStr = getMockMvc().perform(
                    post("/signup.do")
                    .param("user_name", "xiaoxiong")
                    .param("password", "123456")
                    .param("phone", "18890321949")
                    .param("real_name", "黄趾雄")
                    .param("address", "湘潭大学琴湖18栋")
                ).andReturn().getResponse().getContentAsString();
//      System.out.println(resposneStr);
        assertTrue(resposneStr.contains("注册成功"));
    }
}
