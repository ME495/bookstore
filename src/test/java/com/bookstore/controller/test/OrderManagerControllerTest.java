package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSON;
import com.bookstore.utils.LoginJUnit;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;

/**
 * @Auther ME495
 */
public class OrderManagerControllerTest extends LoginJUnit {

    /**
     * 未登陆时测试 /admin/order_query.do
     * @throws Exception
     */
    @Test
    public void testOrderQuery1() throws Exception {
        MvcResult result = getMockMvc().perform(post("/admin/order_query.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("page", "20")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 管理员登陆后测试 /admin/order_query.do
     * @throws Exception
     */
    @Test
    public void testOrderQuery2() throws Exception {
        adminLogin("chengjian", "123456");
        MvcResult result = getMockMvc().perform(post("/admin/order_query.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("page", "20")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 用户登陆后测试 /admin/order_query.do
     * @throws Exception
     */
    @Test
    public void testOrderQuery3() throws Exception {
        userLogin("chengjian", "123456");
        MvcResult result = getMockMvc().perform(post("/admin/order_query.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("page", "20")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }
}