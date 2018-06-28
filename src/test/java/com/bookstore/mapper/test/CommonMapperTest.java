package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookDetailInfo;
import com.bookstore.entity.User;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.utils.BaseJUnit;

public class CommonMapperTest extends BaseJUnit {
	@Autowired
	private CommonMapper commonMapper;

	@Test
	public void testGetBookByIsbn() {
		assertNotNull(commonMapper.getBookByIsbn("9787100155724"));
	}

	@Test
	public void testGetBookByIsbnAndDegree() {
		BookDetailInfo bookDetailInfo = commonMapper.getBookByIsbnAndDegree("9787100155724", 1);
//		System.out.println(bookDetailInfo.getBook().getAuthor());
//		assertNotNull(bookDetailInfo);
	}

	@Test
	public void testGetBookDegreesByIsbn() {
		assertEquals(3, commonMapper.getBookDegreesByIsbn("9787100155724").size());
	}

	@Test
	public void testGetBooks() {
		ArrayList<Book> books = commonMapper.getBooks(1, 8);
		assertEquals(8, books.size());
	}
	
	@Test
	public void testGetBooksByKeyword() {
		ArrayList<Book> books = commonMapper.getBooksByKeyWord("记", 1, 8);
//		System.out.println(books.size());
	}
	
	   @Test
	    public void testInsertUser() {
	        User user = new User();
	        user.setUserName("xiaoxiong");
	        user.setPassword("123456");
	        user.setPhone("18880207329");
	        user.setRealName("张三");
	        user.setAddress("湘潭大学琴湖18栋");
	        assertEquals(1, commonMapper.insertUser(user));
	    }
}
