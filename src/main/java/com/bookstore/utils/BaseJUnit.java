package com.bookstore.utils;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * JUnit测试基类，提供spring环境
 * @Author ME495
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-cfg.xml",
        "classpath:mybatis-cfg.xml",
        "classpath:dispatcher-servlet.xml"})
public abstract class BaseJUnit {

}
