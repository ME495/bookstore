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
	public void testUserLogin() {
		assertEquals(true, loginService.checkUser("chengjian", "123456"));
		assertEquals(false, loginService.checkUser("chengjian", "111111"));
		assertEquals(false, loginService.checkUser("falesjo4_912", "111111"));
	}
	
	@Test
	public void testAdminLogin() {
		assertEquals(true, loginService.checkAdmin("chengjian", "123456"));
		assertEquals(false, loginService.checkAdmin("chengjian", "111111"));
		assertEquals(false, loginService.checkAdmin("falesjo4_912", "111111"));
	}
}
