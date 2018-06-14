package com.bookstore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Trolley;
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

}
