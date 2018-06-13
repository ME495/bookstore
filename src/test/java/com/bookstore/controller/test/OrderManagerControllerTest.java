package com.bookstore.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSON;
import com.bookstore.utils.LoginJUnit;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.message.ResponseMes;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * @Auther ME495
 */
public class OrderManagerControllerTest extends LoginJUnit {

    private final String ADMINAME = "chengjian";
    private final String PASSWORD = "123456";
    private final String USERNAME = "qedauzrf";

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
                .param("size", "20")
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
        adminLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/admin/order_query.do")
                .param("status0", "false")
                .param("status1", "true")
                .param("status2", "false")
                .param("begin_time", "2008-06-05 00:00:00")
                .param("index", "0")
                .param("size", "20")
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
        userLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/admin/order_query.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("size", "20")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 用户登陆后测试 /user/my_order.do
     * @throws Exception
     */
    @Test
    public void testMyOrder1() throws Exception {
        userLogin(USERNAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/my_order.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("size", "20")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 未登陆时测试 /user/my_order.do
     * @throws Exception
     */
    @Test
    public void testMyOrder2() throws Exception {
        MvcResult result = getMockMvc().perform(post("/user/my_order.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("size", "20")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 管理员登陆后测试 /user/my_order.do，此时管理员没有权限
     * @throws Exception
     */
    @Test
    public void testMyOrder3() throws Exception {
        adminLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/my_order.do")
                .param("status0", "true")
                .param("status1", "true")
                .param("status2", "true")
                .param("index", "0")
                .param("size", "20")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 管理员登陆后查看管理员订单详情
     * @throws Exception
     */
    @Test
    public void testAdminOrderDetail1() throws Exception {
        adminLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/admin/order_detail.do")
                .param("order_id", "457")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 未登陆查看管理员订单详情
     * @throws Exception
     */
    @Test
    public void testAdminOrderDetail2() throws Exception {
        MvcResult result = getMockMvc().perform(post("/admin/order_detail.do")
                .param("order_id", "457")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 用户登陆后查看自己的订单详情
     * @throws Exception
     */
    @Test
    public void testUserOrderDetail1() throws Exception {
        userLogin(USERNAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/order_detail.do")
                .param("order_id", "103")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 用户登陆后查看别人的订单详情
     * @throws Exception
     */
    @Test
    public void testUserOrderDetail2() throws Exception {
        userLogin(USERNAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/order_detail.do")
                .param("order_id", "101")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 未登录用户查看用户订单详情
     * @throws Exception
     */
    @Test
    public void testUserOrderDetail3() throws Exception {
        MvcResult result = getMockMvc().perform(post("/user/order_detail.do")
                .param("order_id", "103")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 管理员分配未分配订单
     */
    @Test
    public void testAllocateOrder1() throws Exception {
        adminLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/admin/allocate_order.do")
                .param("order_id", "103")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 管理员分配正在配送的订单或者确认收货的订单
     */
    @Test
    public void testAllocateOrder2() throws Exception {
        adminLogin(ADMINAME, PASSWORD);
        MvcResult result = getMockMvc().perform(post("/admin/allocate_order.do")
                .param("order_id", "101")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 用户确认订单收货
     */
    @Test
    public void testConfirmOrder1() throws Exception {
        userLogin("jctfloeu", PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/confirm_order.do")
                .param("order_id", "101")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    /**
     * 用户确认别人的订单收货
     */
    @Test
    public void testConfirmOrder2() throws Exception {
        userLogin("jctfloeu", PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/confirm_order.do")
                .param("order_id", "104")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    /**
     * 用户确认已收货的订单
     */
    @Test
    public void testConfirmOrder3() throws Exception {
        userLogin("vgeafrpb", PASSWORD);
        MvcResult result = getMockMvc().perform(post("/user/confirm_order.do")
                .param("order_id", "102")
                .session(getMockHttpSession())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();
        String st = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }
}