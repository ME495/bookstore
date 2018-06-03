package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface UserService {
	public ResponseMes insertUser(String userName, String password, String phone);
}
