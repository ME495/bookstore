package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookPrice;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonMapper commonMapper;

	@Override
	public ResponseMes getBookByIsbnAndDegree(String isbn, int degree) {
		BookPrice classfiedBook = commonMapper.getBookByIsbnAndDegree(isbn, degree);
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
		int count = commonMapper.getBookCount();
		if (index > count) {//如果开始位置大于数据库的记录数,则翻到最后一页
			index = count - size;
		}
		ArrayList<Book> books = commonMapper.getBooks(index, size);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, books);
		return responseMes;
	}

	@Override
	public ResponseMes getBooksByKeyWord(String keyWord, int index, int size) {
		int count = commonMapper.getBookCount();
		if (index > count) {//如果开始位置大于数据库的记录数,则翻到最后一页
			index = count - size;
		}
		ArrayList<Book> books = commonMapper.getBooksByKeyWord(keyWord, index, size);
		ResponseMes responseMes = new ResponseMes(ResponseMes.SUCCESS, books);
		return responseMes;
	}
}
