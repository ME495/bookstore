package com.bookstore.service.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.entity.OrderSelector;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.OrderManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author ME495
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-cfg.xml",
        "classpath:mybatis-cfg.xml",
        "classpath:dispatcher-servlet.xml"
})
public class OrderManagerServiceTest {

    @Autowired
    OrderManagerService orderManagerService;

    @Test
    public void orderQuery() {
        OrderSelector s = new OrderSelector();
        s.setStatus0(true);
        s.setStatus1(true);
        s.setStatus2(true);
        String st = orderManagerService.orderQuery(s);
        System.out.println(st);
        JSONObject jsonObject = JSONObject.parseObject(orderManagerService.orderQuery(s));
        assertEquals(ResponseMes.SUCCESS, jsonObject.getString("status"));
    }
}