package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.entity.Order;
import com.bookstore.entity.Trolley;
import com.bookstore.entity.Trolley4Pay;
import com.bookstore.mapper.TrolleyMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.TrolleyService;

@Service
public class TrolleyServiceImpl implements TrolleyService {
	
	@Autowired
	private TrolleyMapper trolleyMapper;

	@Override
	public ResponseMes insertTrolley(String userName, String isbn, int degree, int num) {
		if(trolleyMapper.insertTrolley(userName, isbn, degree, num) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}

	@Override
	public ResponseMes deleteTrolley(String userName, String isbn, int degree) {
		if(trolleyMapper.deleteTrolley(userName, isbn, degree) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}

	@Override
	public ResponseMes updateTrolley(String userName, String isbn, int degree, int num) {
		if(trolleyMapper.updateTrolley(userName, isbn, degree, num) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}

	@Override
	public ResponseMes selectTrolley(String userName) {
		try {
			ArrayList<Trolley> trolleyList = trolleyMapper.selectTrolley(userName);
			return new ResponseMes(ResponseMes.SUCCESS, trolleyList);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, null);
		}
	}
	
	@Override
	public double getActualPrice(String isbn, int degree) {
		return trolleyMapper.getActualPrice(isbn, degree);
	}
	
	@Override
	public ResponseMes getPrice2Pay(String trolleyMsg) {
		ArrayList<Trolley4Pay> trolleyMsgList = new ArrayList<Trolley4Pay>();
		try {
		    trolleyMsgList = (ArrayList<Trolley4Pay>) JSONObject.parseArray(trolleyMsg, Trolley4Pay.class);
		    double price = 0.0;
		    for(int i = 0; i < trolleyMsgList.size(); i++) {
		    	Trolley4Pay trolley4Pay = trolleyMsgList.get(i);
		    	price += trolleyMapper.getActualPrice(trolley4Pay.getIsbn(), trolley4Pay.getDegree())
		    			* trolley4Pay.getNum();
		    }
			return new ResponseMes(ResponseMes.SUCCESS, price);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, null);
		}
	}

	@Override
	public ResponseMes insertOrder(Order order) {
		if(trolleyMapper.insertOrder(order) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}

	@Override
	public ResponseMes insertOrderBook(int orderId, int degree, String isbn, double unitPrice, int num) {
		if(trolleyMapper.insertOrderBook(orderId, degree, isbn, unitPrice, num) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}
	
	@Override
	public ArrayList<Trolley4Pay> getOrderBook(int orderId) {
		return(trolleyMapper.getOrderBook(orderId));
	}

	@Override
	public ResponseMes insertOrderPayment(int orderId, String paymentId) {
		if(trolleyMapper.insertOrderPayment(orderId, paymentId) == 1) {
			return new ResponseMes(ResponseMes.SUCCESS, "");
		} else {
			return new ResponseMes(ResponseMes.FAIL, "");
		}
	}

	@Override
	public int getOrderId(String paymentId) {
		return trolleyMapper.getOrderId(paymentId);
	}

}
