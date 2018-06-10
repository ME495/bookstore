package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.User;
import com.bookstore.mapper.AdminMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.utils.BaseJUnit;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AdminMapperTest extends BaseJUnit {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	UserMapper userMapper;

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		userMapper.insertUser(user);
		assertEquals(1, adminMapper.deleteUser("xiaoxiong"));
	}
	
	@Test
	public void testModifyUserPwd() {
		assertEquals(1, adminMapper.modifyUserPwd("xiyou", "123456"));
	}
}
