package com.bookstrore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LogUtil;

@Component
@Aspect
public class SuperAspect extends LogUtil {
	private String operator;// 操作者
	private String operation;// 操作
	private String mes;// 返回信息
	ResponseMes response;

	@AfterReturning(value = "execution(* com.bookstore.controller.SuperController.*(*,..)) && args(adminName,..)", returning = "jsonStr")
	public void superLog(JoinPoint jp, String adminName, String jsonStr) {
		response = formatJson(jsonStr);
		// 操作者
		operator = "super";
		// 目标方法名
		String methodName = jp.getSignature().getName();
		switch (methodName) {
		case "insertAdmin":
			operation = "新增管理员(adminName:" + adminName + ")";
			break;

		default:
			operation = "删除管理员(adminName:" + adminName + ")";
			break;
		}
		mes = buildMes(response);
		writeLog(operator, operation, mes);
	}
}
