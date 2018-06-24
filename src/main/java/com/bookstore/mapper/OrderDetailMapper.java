package com.bookstore.mapper;

import com.bookstore.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情映射器
 * @Author ME495
 */
@Repository
public interface OrderDetailMapper {
    public List<OrderDetail> getOrderDetail(@Param("orderId") int orderId);
}
