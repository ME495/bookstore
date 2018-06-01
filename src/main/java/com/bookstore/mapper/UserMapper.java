package com.bookstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.User;

@Repository
public interface UserMapper {
	public User getUser(String userName);
}
