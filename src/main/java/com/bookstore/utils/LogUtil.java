package com.bookstore.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.alibaba.fastjson.JSON;
import com.bookstore.message.ResponseMes;
import com.bookstrore.aspect.AdminLogAspect;

public class LogUtil {
	private Logger log = Logger.getLogger(AdminLogAspect.class);
	// 将日志写入数据库
	public void writeLog(String operator,String operation,String mes) {
		MDC.put("operator", operator);
		MDC.put("operation", operation);
		MDC.put("mes", mes);
		log.info("");
	}

	public ResponseMes formatJson(String jsonStr) {
		return JSON.parseObject(jsonStr, ResponseMes.class);
	}

	public String buildMes(ResponseMes response) {
		if (response.getStatus().equals(ResponseMes.SUCCESS)) {
			return "成功";
		} else {
			return "失败("+response.getMessage()+")";
		}
	}
}
