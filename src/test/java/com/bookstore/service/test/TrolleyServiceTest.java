package com.bookstore.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.message.ResponseMes;
import com.bookstore.service.TrolleyService;
import com.bookstore.utils.BaseJUnit;

public class TrolleyServiceTest extends BaseJUnit {
	
	@Autowired
	private TrolleyService trolleyService;
	
	@Test
	public void testInsertTrolley() {
		ResponseMes response = trolleyService.insertTrolley("jinqi", "9787100155724", 0, 2);
		assertEquals(ResponseMes.SUCCESS, response.getStatus());
	}
	
	@Test
	public void testDeleteTrolley() {
		ResponseMes response = trolleyService.deleteTrolley("jinqi", "9787100155724", 1);
		assertEquals(ResponseMes.SUCCESS, response.getStatus());
	}
	
	@Test
	public void testUpdateTrolley() {
		ResponseMes response = trolleyService.updateTrolley("jinqi", "9787100155724", 1, 2);
		assertEquals(ResponseMes.SUCCESS, response.getStatus());
	}
	
	@Test
	public void testGetPrice2Pay() {
		assertEquals(79.2, trolleyService.getPrice2Pay(
				"[{\"isbn\":\"9787108061119\", degree:0, num:2}, {\"isbn\":\"9787100155724\", degree:2, num:3}]"), 0.01);
	}

}
