package com.bookstore.entity;

import java.util.Date;

/**
 * 订单选择器类
 * 
 * @author ME495
 *
 */
public class OrderSelector {
	private Integer orderId;
	private boolean status0, status1, status2;
	private String userName, adminName, beginTime, endTime;
	private int index, page;

	public OrderSelector() {
		this.status0 = this.status1 = this.status2 = false;
		this.index = 0;
		this.page = 20;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean getStatus0() {
		return status0;
	}

	public void setStatus0(boolean status0) {
		this.status0 = status0;
	}

	public boolean getStatus1() {
		return status1;
	}

	public void setStatus1(boolean status1) {
		this.status1 = status1;
	}

	public boolean getStatus2() {
		return status2;
	}

	public void setStatus2(boolean status2) {
		this.status2 = status2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
