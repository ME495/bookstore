package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.bookstore.utils.MockMvcJUnit;

public class CommonControllerTest extends MockMvcJUnit {

	@Ignore
	@Test
	public void testGetDegreesByIsbn() throws Exception{
		String responseStr = getMockMvc().perform(
					post("/book_degrees.do")
					.param("isbn", "9787100155724")
				).andReturn().getResponse().getContentAsString();
//		System.out.println(responseStr);
		assertEquals(3, JSON.parseObject(responseStr).get("status"));
	}
	
	@Ignore
	@Test
	public void testGetBooks() throws Exception{
		String responseStr = getMockMvc().perform(
					post("/browse_book.do")
					.param("index", "4")
					.param("size","8")
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
}
