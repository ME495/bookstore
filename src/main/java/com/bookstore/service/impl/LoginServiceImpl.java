package com.bookstore.service.impl;

import com.bookstore.mapper.AdminMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Admin;
import com.bookstore.entity.User;
import com.bookstore.mapper.SuperMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.LoginService;

import java.io.*;
import java.util.Properties;

/**
 * 
 * @author ME495
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static String superPassword = null;
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	private String getSuperPassword() {
		if (superPassword == null) {
			String dir = this.getClass().getResource("/").getPath();
			String path = dir + "/super_password.properties";
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				Properties properties = new Properties();
				properties.load(bufferedReader);
				superPassword = properties.getProperty("super_password");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return superPassword;
	}
	
	@Override
	public boolean checkUser(String userName, String password) {
		User user = userMapper.getUser(userName);
		if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAdmin(String adminName, String password) {
		Admin admin = adminMapper.getAdminByName(adminName);
		if (admin != null && admin.getPassword().equals(DigestUtils.md5Hex(password))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkSuper(String password) {
		if (password != null && password.equals(getSuperPassword())) {
			return true;
		} else {
			return false;
		}
	}

	
}
