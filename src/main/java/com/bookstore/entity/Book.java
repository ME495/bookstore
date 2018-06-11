package com.bookstore.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author ME495
 */
public class Book {
	@JSONField(ordinal = 1)
	private String isbn;
	
	@JSONField(ordinal = 2)
	private String title;
	
	@JSONField(ordinal = 3)
	private String author;
	
	@JSONField(ordinal = 4)
	private String publisher;
	
	@JSONField(ordinal = 5)
	private String summary;
	
	@JSONField(ordinal = 6)
	private String imgUrl;
	
	@JSONField(ordinal = 7)
	private double originalPrice;

	public Book() {
		super();
	}

	public Book(String isbn, String title, String author, String publisher, String summary, String imgUrl,
			double originalPrice) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.summary = summary;
		this.imgUrl = imgUrl;
		this.originalPrice = originalPrice;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

}
