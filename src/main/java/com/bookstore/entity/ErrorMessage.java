package com.bookstore.entity;

/**
 * 错误信息实体类
 * @author ME495
 *
 */
public class ErrorMessage {
	private String error;
	
	public ErrorMessage(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
