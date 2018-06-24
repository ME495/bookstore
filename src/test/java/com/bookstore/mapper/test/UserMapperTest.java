package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.User;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.utils.BaseJUnit;

public class UserMapperTest extends BaseJUnit {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private CommonMapper commonMapper;

  @Test
  public void test() {
    User user = userMapper.getUser("chengjian");
    assertEquals(user.getPassword(), DigestUtils.md5Hex("123456"));
    assertEquals(user.getPhone(), "15616381480");
  }

  @Test
  public void testUpdateUserInfo() {
    User user = new User();
    user.setUserName("xiaoxiong");
    user.setPassword("123456");
    user.setPhone("18880207329");
    user.setRealName("张三");
    user.setAddress("湘潭大学琴湖18栋");
    commonMapper.insertUser(user);
    user = new User("xiaoxiong", "654321", "12345644564", "你好", "湘潭大学琴湖18栋");
    assertEquals(1, userMapper.updateUserInfo(user));
  }
}
