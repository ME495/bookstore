package com.bookstore.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Book;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.CommonService;
import com.bookstore.utils.BaseJUnit;

public class CommonServiceTest extends BaseJUnit{
	@Autowired
	private CommonService commonService;

	/**
	 * 获得已录入书籍的信息
	 */
	@Ignore
	@Test
	public void testGetBookByIsbnAndDegree() {
		ResponseMes responseMes = commonService.getBookByIsbnAndDegree("9787100155724", 1);
		assertEquals("success", responseMes.getStatus());
	}
	
	/**
	 * 获得未录入书籍的新旧程度信息
	 */
	@Ignore
	@Test
	public void testGetBookByIsbnAndDegree1() {
		ResponseMes responseMes = commonService.getBookByIsbnAndDegree("9787506391542", 1);
		assertEquals("fail", responseMes.getStatus());
	}
	
	/**
	 * 测试已录入书籍的新旧程度信息
	 */
	@Ignore
	@Test
	public void testGetBookDegreesByIsbn() {
		ResponseMes responseMes = commonService.getBookDegreesByIsbn("9787100155724");
//		System.out.println(responseMes.getMessage());
		assertEquals(3, ((ArrayList<Integer>)responseMes.getMessage()).size());
	}
	
	@Ignore
	@Test
	public void testGetBooks() {
		ResponseMes responseMes = commonService.getBooks(23, 8);
		assertEquals("success", responseMes.getStatus());
	}
	
	@Test
	public void testGetBooksByKeyWord() {
		ResponseMes responseMes = commonService.getBooksByKeyWord("记",1, 8);
//		System.out.println(responseMes.getStatus());
		assertEquals("success", responseMes.getStatus());
	}
}
