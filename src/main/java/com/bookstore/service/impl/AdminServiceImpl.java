package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookPrice;
import com.bookstore.mapper.AdminMapper;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.AdminService;
import com.bookstore.utils.ISBNUtil;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommonMapper commonMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.AdminService#deleteUser(java.lang.String)
	 */
	@Override
	public ResponseMes deleteUser(String userName) {
		ResponseMes responseMes;
		// 用户已存在,则删除
		adminMapper.deleteUser(userName);
		responseMes = new ResponseMes(ResponseMes.SUCCESS, "删除成功");
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.AdminService#modifyUserPwd(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResponseMes modifyUserPwd(String userName, String password) {
		ResponseMes responseMes;
		// 用户已存在,则修改密码
		adminMapper.modifyUserPwd(userName, password);
		responseMes = new ResponseMes(ResponseMes.SUCCESS, "修改成功");
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.AdminService#addBook(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, double, int, int)
	 */
	@Override
	public ResponseMes addBook(String isbn, String title, String author, String publisher, String summary,
			String imgUrl, double originalPrice, int degree, int num) {
		ResponseMes responseMes;
		Book book = commonMapper.getBookByIsbn(isbn);
		if (ISBNUtil.isISBN(isbn)) {
			if (book == null) {// 若图书不存在,则插入图书信息
				double actualPrice = 0.0;
				switch (degree) {
				case 0:
					actualPrice = originalPrice * 0.6;// 原价的6折
					break;
				case 1:
					actualPrice = originalPrice * 0.4;// 原价的4折
					break;
				default:
					actualPrice = originalPrice * 0.2;// 原价的2折
					break;
				}
				book = new Book(isbn, title, author, publisher, summary, imgUrl, originalPrice);
				BookPrice bookPrice = new BookPrice(isbn, degree, num, actualPrice);
				adminMapper.addBook(book, bookPrice);
			} else {// 若图书已存在,则增加图书数量
				adminMapper.updateBook(isbn, degree, num, null);
			}
			responseMes = new ResponseMes(ResponseMes.SUCCESS, "添加成功");
		} else {// 如ISBN号有误,则提示"ISBN号输入有误"
			responseMes = new ResponseMes(ResponseMes.FAIL, "ISBN号输入有误");
		}
		return responseMes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.AdminService#deleteBook(java.lang.String, int)
	 */
	@Override
	public ResponseMes deleteBook(String isbn, int degree) {
		adminMapper.deleteBook(isbn, degree);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, "删除成功");
		return responseMes;
	}

	@Override
	public ResponseMes updateBookInfo(String isbn, int degree, int num, Double actualPrice) {
		adminMapper.updateBook(isbn, degree, num, actualPrice);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, "成功修改图书信息");
		return responseMes;
	}

}
