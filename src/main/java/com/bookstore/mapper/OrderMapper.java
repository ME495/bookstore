package com.bookstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Order;
import com.bookstore.entity.OrderSelector;

@Repository
public interface OrderMapper {
	public List<Order> query(@Param("s") OrderSelector s, @Param("is_asc") boolean isAsc);
	public int setOrderStatus(@Param("order_id") int order_id, @Param("status") int status);
	public Order getOrder(@Param("orderId") int orderId);
}
