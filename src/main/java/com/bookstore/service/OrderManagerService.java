package com.bookstore.service;

import com.bookstore.entity.OrderSelector;

public interface OrderManagerService {
	public String orderQuery(OrderSelector s, boolean isAsc);
	public String orderDetail(int orderId);
	public String allocateOrder(int orderId);
	public String confirmOrder(int orderId);
	public String approveOrder(int orderId);
}
