package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LoginJUnit;

public class TrolleyControllerTest extends LoginJUnit {

	@Test
	public void testInsertTrolley() throws Exception {
		userLogin("jinqi", "123456");
		MvcResult result = getMockMvc().perform(post("/user/trolley_add.do")
				.param("isbn", "9787100155724")
                .param("degree", "0")
                .param("num", "1")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
	}
	
	@Test
	public void testDeleteTrolley() throws Exception {
		userLogin("jinqi", "123456");
		getMockMvc().perform(post("/user/trolley_add.do")
				.param("isbn", "9787100155724")
                .param("degree", "0")
                .param("num", "1")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		MvcResult result = getMockMvc().perform(post("/user/trolley_delete.do")
				.param("isbn", "9787100155724")
                .param("degree", "0")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
	}
	
	@Test
	public void testUpdateTrolley() throws Exception {
		userLogin("jinqi", "123456");
		getMockMvc().perform(post("/user/trolley_add.do")
				.param("isbn", "9787100155724")
                .param("degree", "0")
                .param("num", "1")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		MvcResult result = getMockMvc().perform(post("/user/trolley_update.do")
				.param("isbn", "9787100155724")
                .param("degree", "0")
                .param("num", "37")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
	}
	
	@Test
	public void testSelectTrolley() throws Exception {
		userLogin("jinqi", "123456");
		getMockMvc().perform(post("/user/trolley_add.do")
				.param("isbn", "9787108061119")
                .param("degree", "0")
                .param("num", "1")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		MvcResult result = getMockMvc().perform(post("/user/trolley_check.do")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        System.out.println(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
	}

}
