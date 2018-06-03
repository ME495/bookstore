package com.bookstore.message;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class ResponseMes {
	final String SUCCESS = "success";
	final String FAIL = "fail";
	private String status;
	private Object message;

	public ResponseMes() {
		super();
	}

	public ResponseMes(String status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}
	
	public Object getMes() {
		return message;
	}
}