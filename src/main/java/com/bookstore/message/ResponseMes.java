package com.bookstore.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 返回消息类.
 * 定义了返回的 json 的格式.
 * @author ME495
 *
 */
public class ResponseMes {
	public final static String SUCCESS = "success";
	public final static String FAIL = "fail";
	private String status;
	private Object message;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public ResponseMes(String status, Object message) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseMes() {
		
	}
	
	public String toJsonString() {
		return JSON.toJSONString(this);
	}
}