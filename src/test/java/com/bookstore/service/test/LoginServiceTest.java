package com.bookstore.service.test;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-cfg.xml", 
	"classpath:mybatis-cfg.xml", 
	"classpath:dispatcher-servlet.xml"
})
public class LoginServiceTest {

	@Autowired
	private LoginService loginService;
	
	@Test
	public void userLoginTest() {
		String userName = "chengjian";
		String password = DigestUtils.md5Hex("123456");
		assertEquals(loginService.checkUser(userName, password), true);
	}
}
