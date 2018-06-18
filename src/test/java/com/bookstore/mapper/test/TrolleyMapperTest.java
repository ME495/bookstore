package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Order;
import com.bookstore.entity.Trolley;
import com.bookstore.mapper.TrolleyMapper;
import com.bookstore.utils.BaseJUnit;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class TrolleyMapperTest extends BaseJUnit {

	@Autowired
	TrolleyMapper trolleyMapper;
	
	@Ignore
	@Test
	public void testInsertTrolley() throws Exception {
		assertEquals(1, trolleyMapper.insertTrolley("jinqi", "9787100155724", 0, 1));
	}
	@Ignore
	@Test
	public void testDeleteTrolley() {
		assertEquals(1, trolleyMapper.deleteTrolley("auybnwem", "9787533951665", 0));
	}
	@Ignore
	@Test
	public void testUpdateTrolley() {
		assertEquals(1, trolleyMapper.updateTrolley("auybnwem", "9787533951665", 0, 88));
	}
	@Ignore
	@Test
	public void testSelectTrolley() {
		List<Trolley> list = trolleyMapper.selectTrolley("auybnwem");
		assertEquals(1, list.size());
		
	}
	@Ignore
	@Test
	public void tesGetActualPrice() {
		assertEquals(16.8, trolleyMapper.getActualPrice("9787100155724", 1), 0.01);
	}
	
	
	@Test
	public void testInsertOrder() {
		Date date = new Date(); // 获得系统时间
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date); // 将时间格式转换成符合Timestamp要求的格式
        Timestamp orderDatetime = Timestamp.valueOf(nowTime);
		Order order = new Order("jinqi","琴湖18栋", orderDatetime, 1, 18.8);
		assertEquals(1, trolleyMapper.insertOrder(order));
		//System.out.println(order.getOrderTime());
	}
	
	@Test
	public void testInsertOrderBook() {
		double unitPrice = trolleyMapper.getActualPrice("9787533951665", 1);
		assertEquals(1, trolleyMapper.insertOrderBook(150, 1, "9787533951665", unitPrice, 2));
	}
	
	@Test
	public void testGetOrderBook() {
		assertEquals(5, trolleyMapper.getOrderBook(150).size());
	}
	
	@Test
	public void testInsertOrderBookPayment() {
		assertEquals(1, trolleyMapper.insertOrderPayment(149, "test-payment-id"));
	}
	
	@Test
	public void testGetOrderId() {
		trolleyMapper.insertOrderPayment(149, "test-payment-id2");
		assertEquals(149, trolleyMapper.getOrderId("test-payment-id2"));
	}
	
	@Test
	public void testGetPaymentId() {
		trolleyMapper.insertOrderPayment(149, "test-payment-id2");
		assertEquals("test-payment-id2", trolleyMapper.getPaymentId(149));
	}

}
