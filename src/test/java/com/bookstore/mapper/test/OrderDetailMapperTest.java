package com.bookstore.mapper.test;

import static org.junit.Assert.*;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderDetail;
import com.bookstore.mapper.BookMapper;
import com.bookstore.mapper.OrderDetailMapper;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.utils.BaseJUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ME495
 */
public class OrderDetailMapperTest extends BaseJUnit {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testGetOrderDetail() {
        int orderId = 457;
        List<OrderDetail> list = orderDetailMapper.getOrderDetail(orderId);
        Order order = orderMapper.getOrder(orderId);
        double price = 0;
        for(OrderDetail orderDetail : list) {
            Book book = bookMapper.getBook(orderDetail.getIsbn());
            price += book.getActualPrice() * orderDetail.getNum();
        }
        assertEquals(price, order.getPrice(), 1e-5);
    }
}
