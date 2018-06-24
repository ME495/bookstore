package com.bookstore.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Admin;
import com.bookstore.entity.ErrorMessage;
import com.bookstore.entity.OrderDetail;
import com.bookstore.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Order;
import com.bookstore.entity.OrderSelector;
import com.bookstore.entity.User;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.OrderManagerService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author ME495
 *
 */
@Service
public class OrderManagerServiceImpl implements OrderManagerService {

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	OrderDetailMapper orderDetailMapper;
	
	@Override
	public String orderQuery(OrderSelector s, boolean isAsc) {
		List<Order> list = orderMapper.query(s, isAsc);
		return new ResponseMes(ResponseMes.SUCCESS, list).toJsonString();
	}

	@Override
	public String orderDetail(int orderId) {
		List<OrderDetail> list = orderDetailMapper.getOrderDetail(orderId);
		ResponseMes mes = new ResponseMes(ResponseMes.SUCCESS, list);
		return mes.toJsonString();
	}

	@Override
	public String allocateOrder(int orderId) {
		Order order = orderMapper.getOrder(orderId);
		if (order.getStatus() == 0) {
			if (1 == orderMapper.setOrderStatus(orderId, 1)) {
				return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
			} else {
				return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
			}
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}

	@Override
	public String confirmOrder(int orderId) {
		Order order = orderMapper.getOrder(orderId);
		if (order.getStatus() == 1) {
			if (1 == orderMapper.setOrderStatus(orderId, 2)) {
				return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
			} else {
				return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
			}
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}

	@Override
	public String approveOrder(int orderId) {
		Order order = orderMapper.getOrder(orderId);
		if (order.getStatus() == -1) {
			if (1 == orderMapper.setOrderStatus(orderId, 0)) {
				return new ResponseMes(ResponseMes.SUCCESS, null).toJsonString();
			} else {
				return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
			}
		} else {
			return new ResponseMes(ResponseMes.FAIL, null).toJsonString();
		}
	}

	@Override
	public byte[] printOrderDetail(int orderId) {
		String dir = this.getClass().getResource("/").getPath();
		File file = new File(dir+"/Blank_A4.jasper");
		System.out.println(file.getPath());
		Map<String, Object> parameters = new HashMap<>();
		Order order = orderMapper.getOrder(orderId);
		User user = userMapper.getUser(order.getUserName());
		parameters.put("orderId", order.getOrderId());
		parameters.put("userName", order.getUserName());
		parameters.put("address", order.getAddress());
		parameters.put("phone", user.getPhone());
		List<OrderDetail> list = orderDetailMapper.getOrderDetail(Integer.valueOf(order.getOrderId()));
		for(OrderDetail o : list) {
			System.out.println(o.getDegreeName());
		}
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
		byte[] bytes = null;
		try {
			bytes = JasperRunManager.runReportToPdf(file.getPath(), parameters, jrDataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}


}
