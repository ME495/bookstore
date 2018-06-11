package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Book;
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
		assertNotNull(commonMapper.getBookByIsbnAndDegree("9787100155724", 1));
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
		System.out.println(books.size());
	}
}
