package com.bookstore.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图书详细信息的实体类
 * 
 * @author xiaoxiong
 *
 */
public class BookDetailInfo {
	@JSONField(ordinal = 1)
	private Book book;
	
	@JSONField(ordinal = 2)
	private int degree;
	
	@JSONField(ordinal = 3)
	private double actualPrice;
	
	@JSONField(ordinal = 4)
	private int num;

	public BookDetailInfo() {
		super();
	}

	public BookDetailInfo(Book book, int degree, double actualPrice, int num) {
		super();
		this.book = book;
		this.degree = degree;
		this.actualPrice = actualPrice;
		this.num = num;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
