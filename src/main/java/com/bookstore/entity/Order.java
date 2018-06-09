package com.bookstore.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单类
 * @author ME495
 *
 */
public class Order {
	private String orderId, userName, address;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
	private int status;
	private double money;

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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
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
