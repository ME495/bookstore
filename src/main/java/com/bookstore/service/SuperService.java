package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface SuperService {
	public ResponseMes insertAdmin(String adminName, String password);

	public ResponseMes deleteAdmin(String adminName);
}
