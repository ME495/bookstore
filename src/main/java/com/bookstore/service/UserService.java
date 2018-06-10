package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface UserService {
	public ResponseMes getUser(String userName);

	public ResponseMes insertUser(String userName, String password, String phone, String realName, String address);
}
