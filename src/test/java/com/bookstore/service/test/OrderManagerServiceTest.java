package com.bookstore.service.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderSelector;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.OrderManagerService;
import com.bookstore.utils.BaseJUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author ME495
 */
public class OrderManagerServiceTest extends BaseJUnit {

    @Autowired
    OrderManagerService orderManagerService;

    @Test
    public void testOrderQuery() {
        OrderSelector s = new OrderSelector();
        s.setStatus0(true);
        s.setStatus1(true);
        s.setStatus2(true);
        String st = orderManagerService.orderQuery(s, true);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    @Test
    public void testOrderDetail() {
        String st = orderManagerService.orderDetail(103);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    @Test
    public void testAllocateOrder1() {
        String st = orderManagerService.allocateOrder(103);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    @Test
    public void testAllocateOrder2() {
        String st = orderManagerService.allocateOrder(102);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }

    @Test
    public void testConfirmOrder1() {
        String st = orderManagerService.confirmOrder(101);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }

    @Test
    public void testConfirmOrder22() {
        String st = orderManagerService.confirmOrder(102);
        JSONObject jsonObject = JSONObject.parseObject(st);
        assertEquals(ResponseMes.FAIL, jsonObject.getString("status"));
    }
}