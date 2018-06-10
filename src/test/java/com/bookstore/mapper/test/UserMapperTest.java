package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import com.bookstore.utils.BaseJUnit;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserMapperTest extends BaseJUnit {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test() {
		User user = userMapper.getUser("chengjian");
		assertEquals(user.getPassword(), DigestUtils.md5Hex("123456"));
		assertEquals(user.getPhone(), "15616381480");
	}

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
}
