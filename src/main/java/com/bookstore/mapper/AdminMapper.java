package com.bookstore.mapper;

import org.springframework.stereotype.Repository;

import com.bookstore.entity.Admin;

@Repository
public interface AdminMapper {
	public Admin getAdminByName(String adminName);
	public int insertAdmin(Admin admin);
}