package com.bookstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
	public int deleteUser(String userName);

	public int modifyUserPwd(@Param("userName") String userName, @Param("password") String password);
	
	public int addBook();
}
