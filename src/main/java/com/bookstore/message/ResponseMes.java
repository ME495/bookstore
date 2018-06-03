package com.bookstore.message;

import com.alibaba.fastjson.JSONObject;

public class ResponseMes {
	private String status;
	private JSONObject jsonObject;

	public ResponseMes() {
		super();
	}

	public ResponseMes(String status, JSONObject jsonObject) {
		super();
		this.status = status;
		this.jsonObject = jsonObject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
