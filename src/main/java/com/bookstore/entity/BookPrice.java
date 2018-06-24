package com.bookstore.entity;

public class BookPrice {
	private String isbn;
	private int degree;
	private int num;
	private double actualPrice;

	public BookPrice() {
		super();
	}

	public BookPrice(String isbn, int degree, int num, double actualPrice) {
		super();
		this.isbn = isbn;
		this.degree = degree;
		this.num = num;
		this.actualPrice = actualPrice;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}
}
