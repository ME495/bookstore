package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface AdminService {
	public ResponseMes insertAdmin(String adminName, String password);
}
