package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface AdminService {
	public ResponseMes deleteUser(String userName);

	public ResponseMes modifyUserPwd(String userName, String password);
}
