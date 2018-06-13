package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.controller.TrolleyController;
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
		ArrayList<Trolley> trolleyList = trolleyMapper.selectTrolley(userName);
		return new ResponseMes(ResponseMes.SUCCESS, trolleyList);
	}
	
	@Override
	public double getPrice2Pay(String trolleyMsg) {
		ArrayList<Trolley4Pay> trolleyMsgList = new ArrayList<Trolley4Pay>();
        trolleyMsgList = (ArrayList<Trolley4Pay>) JSONObject.parseArray(trolleyMsg, Trolley4Pay.class);
        double price = 0.0;
        for(int i = 0; i < trolleyMsgList.size(); i++) {
        	Trolley4Pay trolley4Pay = trolleyMsgList.get(i);
        	price += trolleyMapper.getActualPrice(trolley4Pay.getIsbn(), trolley4Pay.getDegree())
        			* trolley4Pay.getNum();
        }
		return price;
	}

	@Override
	public void recvPaymentStatus(String userName, int paymentStatus) {
		if(paymentStatus == TrolleyController.PAYMENT_APPROVED) {
			
		}
	}

}
