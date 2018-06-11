package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.bookstore.entity.Trolley;
import com.bookstore.mapper.TrolleyMapper;
import com.bookstore.utils.BaseJUnit;

public class TrolleyMapperTest extends BaseJUnit {

	@Autowired
	TrolleyMapper trolleyMapper;
	
	@Test
	public void testInsertTrolley() {
		assertEquals(1, trolleyMapper.insertTrolley("jinqi", "9787100155724", 0, 1));
	}
	
	@Test
	public void testDeleteTrolley() {
		assertEquals(1, trolleyMapper.deleteTrolley("auybnwem", "9787533951665", 0));
	}
	
	@Test
	public void testUpdateTrolley() {
		assertEquals(1, trolleyMapper.updateTrolley("auybnwem", "9787533951665", 0, 88));
	}
	
	@Test
	public void testSelectTrolley() {
		List<Trolley> list = trolleyMapper.selectTrolley("auybnwem");
		System.out.println(JSON.toJSON(list));
		assertEquals(3, list.size());
		
	}

}
