package com.bookstore.entity;

public class Trolley4Pay {

	private String isbn;
	private int degree;
	private int num;
	
	public Trolley4Pay(String isbn, int degree, int num) {
		super();
		this.isbn = isbn;
		this.degree = degree;
		this.num = num;
	}
	
	public Trolley4Pay() {
		super();
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
	
}
