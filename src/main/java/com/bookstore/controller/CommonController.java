package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookstore.service.CommonService;

@Controller
public class CommonController {
	@Autowired
	private CommonService commonService;

	@ResponseBody
	@RequestMapping(value = "/book_degrees.do",produces="application/json;charset=utf-8")
	public String getDegreesByIsbn(String isbn) {
		return JSON.toJSONString(commonService.getBookDegreesByIsbn(isbn));
	}
	
	@ResponseBody
	@RequestMapping(value="/browse_book.do",produces="application/json;charset=utf-8")
	public String getBooks(int index,int size) {
		return JSON.toJSONString(commonService.getBooks(index, size));
	}
	
	@ResponseBody
	@RequestMapping(value="/search_book.do",produces="application/json;charset=utf-8")
	public String getBooksByKeyWord(@RequestParam("key_word")String keyWord,int index,int size) {
		return JSON.toJSONString(commonService.getBooksByKeyWord(keyWord,index, size));
	}
}
