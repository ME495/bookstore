package com.bookstore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.entity.Trolley;
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
        ).andExpect(status().isOk()).andReturn();
		MvcResult result = getMockMvc().perform(post("/user/trolley_check.do")
                .session(getMockHttpSession())
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
//		System.out.println("testSelectTrolley said: " + st);
        JSONObject jsonObject = JSON.parseObject(st);
        ArrayList<Trolley> trolleyList = new ArrayList<Trolley>();
        trolleyList = (ArrayList<Trolley>) JSONObject.parseArray(jsonObject.getString("message"), Trolley.class);
        int post = 0;
        for(int i = 0; i < trolleyList.size(); i++) {
//        	System.out.println(trolleyList.get(i).getIsbn());
        	if(trolleyList.get(i).getIsbn().equals("9787108061119")) {
        		post = i;
        		break;
        	}
        }
//        System.out.println("post=" + post + ", title=" + trolleyList.get(post).getTitle());
        assertEquals(trolleyList.get(post).getTitle(), "南极洲");
	}
	
	@Test
	public void testGetPrice2Pay() throws Exception {
		userLogin("jinqi", "123456");
		MvcResult result = getMockMvc().perform(post("/user/get_price_to_pay.do")
				.param("trolleyMsg", "[{\"isbn\":\"9787108061119\", degree:0, num:2}, {\"isbn\":\"9787100155724\", degree:2, num:3}]")
				.session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
//		System.out.println("testGetPrice2Pay: " + st);
	}
	
	@Ignore
	@Test
	public void testDoPayment() throws Exception {
		userLogin("jinqi", "123456");
		MvcResult result = getMockMvc().perform(post("/user/payment.do")
				.param("trolleyMsg", "[{\"isbn\":\"9787108061119\", degree:0, num:2}, {\"isbn\":\"9787100155724\", degree:2, num:3}]")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
		String st = result.getResponse().getContentAsString();
//		System.out.println("testDoPayment said: " + st);
	}

}
