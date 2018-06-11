package com.bookstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookPrice;

@Repository
public interface AdminMapper {
	public int deleteUser(String userName);

	public int modifyUserPwd(@Param("userName") String userName, @Param("password") String password);

	public int addBook(@Param("book") Book book, @Param("bookPrice") BookPrice bookPrice);

	public int updateBook(@Param("isbn") String isbn,@Param("degree")int degree, @Param("num") int num,@Param("actualPrice") Double actualPrice);
	
	public int deleteBook(@Param("isbn") String isbn,@Param("degree")int degree);
}
