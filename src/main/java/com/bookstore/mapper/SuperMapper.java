package com.bookstore.mapper;

import org.springframework.stereotype.Repository;

import com.bookstore.entity.Admin;
import com.bookstore.entity.User;

@Repository
public interface SuperMapper {
	// 根据管理员账号获得管理员
	public Admin getAdminByName(String adminName);

	// 插入管理员
	public int insertAdmin(Admin admin);

	// 根据管理员账号删除管理员
	public int deleteAdmin(String adminName);
	
}
