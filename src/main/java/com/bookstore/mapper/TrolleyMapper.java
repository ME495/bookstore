package com.bookstore.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Order;
import com.bookstore.entity.Trolley;
import com.bookstore.entity.Trolley4Pay;

@Repository
public interface TrolleyMapper {
	
	public int insertTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree, @Param("num")int num)
				throws Exception;
	
	public int deleteTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree);
	
	public int updateTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree, @Param("num")int num);
	
	public ArrayList<Trolley> selectTrolley(@Param("user_name")String userName);
	
	public double getActualPrice(@Param("isbn")String isbn, @Param("degree")int degree);
	
	public int insertOrder(Order order);
	
	public int insertOrderBook(@Param("order_id")int orderId, @Param("degree")int degree, 
			@Param("isbn")String isbn, @Param("unit_price")double unitPrice, @Param("num")int num);
	
	public ArrayList<Trolley4Pay> getOrderBook(@Param("order_id")int orderId);
	
	public int insertOrderPayment(@Param("order_id")int orderId, @Param("payment_id")String paymentId);
	
	public int getOrderId(@Param("payment_id")String paymentId);
	
	public String getPaymentId(@Param("order_id")int orderId);

}
