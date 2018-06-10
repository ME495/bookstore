package com.bookstore.utils.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bookstore.utils.ISBNUtil;

public class ISBNUtilTest {

	@Test
	public void testIsISBN1() {
		assertTrue(ISBNUtil.isISBN("978-7-302-41164-2"));
		
	}

}
