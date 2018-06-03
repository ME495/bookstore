package com.bookstore.mapper;

import com.bookstore.entity.Admin;

public interface AdminMapper {
	public Admin getAdminByName(String adminName);
	public int insertAdmin(Admin admin);
}
