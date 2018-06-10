package com.bookstore.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookPrice;

/**
 * 管理员,用户都可以使用的功能,主要是指获取图书信息
 * 
 * @author xiaoxiong
 */
@Repository
public interface CommonMapper {
	public Book getBookByIsbn(String isbn);

	public BookPrice getBookByIsbnAndDegree(@Param("isbn") String isbn, @Param("degree") int degree);

	public ArrayList<Integer> getBookDegreesByIsbn(String isbn);

	public ArrayList<Book> getBooks(@Param("index") int index, @Param("size") int size);

	public ArrayList<Book> getBooksByKeyWord(@Param("keyWord") String keyWord, @Param("index") int index,
			@Param("size") int size);
	
	public int getBookCount();
	
}
