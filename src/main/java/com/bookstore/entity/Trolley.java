package com.bookstore.entity;

/**
 * 购物车类
 * @author JinQi
 */
public class Trolley {
	
	private String userName;
	private String isbn;
	private String title;
	private String imgUrl;
	private int degree;
	private int num;
	private double actualPrice;
	
	public Trolley(String userName, String isbn, String title, String imgUrl, int degree, int num,
			double actualPrice) {
		super();
		this.userName = userName;
		this.isbn = isbn;
		this.title = title;
		this.imgUrl = imgUrl;
		this.degree = degree;
		this.num = num;
		this.actualPrice = actualPrice;
	}
	
	public Trolley() {
		super();
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public void setUnitPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

}
