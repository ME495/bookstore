package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import com.bookstore.utils.BaseJUnit;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;

public class UserMapperTest extends BaseJUnit {

	@Autowired
	private UserMapper userMapper;

	@Ignore
	@Test
	public void test() {
		User user = userMapper.getUser("chengjian");
		assertEquals(user.getPassword(), DigestUtils.md5Hex("123456"));
		assertEquals(user.getPhone(), "15616381480");
	}
	@Ignore
	@Test
	public void testInsertUser() {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		assertEquals(1, userMapper.insertUser(user));
	}
	
	@Test
	public void testUpdateUserInfo() {
		testInsertUser();
		User user = new User("xiaoxiong", "654321", "12345644564", "你好", "湘潭大学琴湖18栋");
		assertEquals(1, userMapper.updateUserInfo(user));
	}
}
