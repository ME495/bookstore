package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.*;
import com.bookstore.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.mapper.TrolleyMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.CommonService;
import com.bookstore.service.TrolleyService;

@Service
public class TrolleyServiceImpl implements TrolleyService {
	
	@Autowired
	private TrolleyMapper trolleyMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private CommonService commonService;

	@Override
	public ResponseMes insertTrolley(String userName, String isbn, int degree, int num) {
		try {
//			BookDetailInfo classfiedBook = (BookDetailInfo) commonService.getBookByIsbnAndDegree(isbn, degree)
//					.getMessage();
//			if(num > classfiedBook.getNum()) {
//				return new ResponseMes(ResponseMes.FAIL, "库存不够！");
//			}
			trolleyMapper.insertTrolley(userName, isbn, degree, num);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		}  catch(org.springframework.dao.DuplicateKeyException e1) {
			return new ResponseMes(ResponseMes.FAIL, "购物车中已存在该书本！");
		} catch(Exception e2) {
			return new ResponseMes(ResponseMes.FAIL, "添加购物车失败: "+e2.getMessage());
		}
	}

	@Override
	public ResponseMes deleteTrolley(String userName, String isbn, int degree) {
		try {
			trolleyMapper.deleteTrolley(userName, isbn, degree);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, e.getMessage());
		}
	}

	@Override
	public ResponseMes updateTrolley(String userName, String isbn, int degree, int num) {
		try {
			trolleyMapper.updateTrolley(userName, isbn, degree, num);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, e.getMessage());
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
	
	@SuppressWarnings("null")
	@Override
	public double getActualPrice(String isbn, int degree) {
		try {
			return trolleyMapper.getActualPrice(isbn, degree);
		} catch(Exception e) {
			e.printStackTrace();
			return (Double) null;
		}
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
		try {
			trolleyMapper.insertOrder(order);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, e.getMessage());
		}
	}

	@Override
	public ResponseMes insertOrderBook(int orderId, int degree, String isbn, double unitPrice, int num) {
		try {
			trolleyMapper.insertOrderBook(orderId, degree, isbn, unitPrice, num);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, e.getMessage());
		}
	}
	
	@Override
	public ArrayList<Trolley4Pay> getOrderBook(int orderId) {
		return(trolleyMapper.getOrderBook(orderId));
	}

	@Override
	public ResponseMes insertOrderPayment(int orderId, String paymentId) {
		try {
			trolleyMapper.insertOrderPayment(orderId, paymentId);
			return new ResponseMes(ResponseMes.SUCCESS, null);
		} catch(Exception e) {
			return new ResponseMes(ResponseMes.FAIL, e.getMessage());
		}
	}

	@Override
	public int getOrderId(String paymentId) {
		return trolleyMapper.getOrderId(paymentId);
	}

	@Override
	public String checkBookNum(String trolleyMsg) {
		List<Trolley4Pay> list = JSONObject.parseArray(trolleyMsg, Trolley4Pay.class);
		for (Trolley4Pay o : list) {
			BookDetailInfo bookDetailInfo = commonMapper.getBookByIsbnAndDegree(o.getIsbn(), o.getDegree());
			int num = bookDetailInfo.getNum();
			if (bookDetailInfo.getNum() < o.getNum()) {
				String title = bookDetailInfo.getBook().getTitle();
				return "《" + title + "》只剩" + num + "本";
			}
		}
		return null;
	}


}
