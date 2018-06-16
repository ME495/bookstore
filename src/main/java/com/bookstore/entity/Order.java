package com.bookstore.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单类
 * @author ME495
 *
 */
public class Order {
	private String orderId, userName, address;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp orderTime;
	private int status;
	private double money;

	public Order() {
		super();
	}

	public Order(String userName, String address, Timestamp orderTime, int status, double money) {
		super();
		this.userName = userName;
		this.address = address;
		this.orderTime = orderTime;
		this.status = status;
		this.money = money;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
