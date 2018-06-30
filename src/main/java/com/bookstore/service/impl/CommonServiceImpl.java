package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookDetailInfo;
import com.bookstore.entity.BookPrice;
import com.bookstore.entity.User;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private UserMapper userMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bookstore.service.CommonService#getBookByIsbnAndDegree(java.lang.String,
	 * int)
	 */
	@Override
	public ResponseMes getBookByIsbnAndDegree(String isbn, int degree) {
		BookDetailInfo classfiedBook = commonMapper.getBookByIsbnAndDegree(isbn, degree);
		ResponseMes responseMes;
		if (classfiedBook == null) {// 若书籍不存在,则返回"该图书不存在"
			responseMes = new ResponseMes(ResponseMes.FAIL, "该图书不存在");
		} else {// 若书籍已录入,则返回书籍信息
			responseMes = new ResponseMes(ResponseMes.SUCCESS, classfiedBook);
		}
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bookstore.service.CommonService#getBookDegreesByIsbn(java.lang.String)
	 */
	@Override
	public ResponseMes getBookDegreesByIsbn(String isbn) {
		ArrayList<Integer> degreeList = commonMapper.getBookDegreesByIsbn(isbn);
		ResponseMes responseMes;
		if (degreeList.size() == 0) {// 如果图书未录入,则返回图书不存在
			responseMes = new ResponseMes(ResponseMes.FAIL, "该图书不存在");
		} else {// 如果图书已录入,则返回它的新旧程度列表
			responseMes = new ResponseMes(ResponseMes.SUCCESS, degreeList);
		}
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.AdminService#getBooks(int, int)
	 */
	@Override
	public ResponseMes getBooks(int index, int size) {
		ArrayList<Book> books = commonMapper.getBooks(index, size);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, books);
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.CommonService#getBooksByKeyWord(java.lang.String,
	 * int, int)
	 */
	@Override
	public ResponseMes getBooksByKeyWord(String keyWord, int index, int size) {
		ArrayList<Book> books = commonMapper.getBooksByKeyWord(keyWord, index, size);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, books);
		return responseMes;
	}

	@Override
	public ResponseMes insertUser(String userName, String password, String phone, String realName, String address) {
		ResponseMes responseMes;
		// 如果手机格式不正确,则返回"手机号码格式错误"
		if (userName.matches("[a-zA-Z][a-zA-Z0-9_]{3,15}") && password.matches("[a-zA-Z0-9_]{6,16}")
				&& phone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")) {
			if (userMapper.getUser(userName) == null) {// 如果账号已存在,则返回"该账号已存在"
				User user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setPhone(phone);
				user.setRealName(realName);
				user.setAddress(address);
				commonMapper.insertUser(user);
				responseMes = new ResponseMes(ResponseMes.SUCCESS, "注册成功");
			} else {
				responseMes = new ResponseMes(ResponseMes.FAIL, "该账号已存在");
			}
		} else {
			responseMes = new ResponseMes(ResponseMes.FAIL, "格式错误");
		}
		return responseMes;
	}
}
