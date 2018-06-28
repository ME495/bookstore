package com.bookstrore.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LogUtil;
@Component
@Aspect
public class CommonAspect extends LogUtil {
	private HttpSession session;
	private String operator;// 操作者
	private String operation;// 操作
	private String mes;// 返回信息
	ResponseMes response;
	// 登录日志记录
	@AfterReturning(value = "execution(* com.bookstore.controller.LoginController.*(..)) &&args(account,..)", returning = "jsonStr")
	public void loginLog(JoinPoint jp, String account, String jsonStr) {
		response = formatJson(jsonStr);
		// 获得目标方法的名字
		String methodName = jp.getSignature().getName();
		// 获得账户
		operator = account.equals("Bookstore!") ? "super" : account;
		if (methodName.equals("userLogin")) {
			// 获得操作者
			operation = "用户登录";
		} else if (methodName.equals("adminLogin")) {
			// 获得操作者
			operation = "管理员登录";
		} else {
			operation = "超级管理员登录";
		}
		mes = buildMes(response);
		writeLog(operator, operation, mes);
	}

	// 登出日志
	@Before(value = "execution(* com.bookstore.controller.LogoutController.*(..))")
	public void logoutLog(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		session = request.getSession();
		// 获得用户名字
		operator = (String) session.getAttribute("name");
		String role = (String) session.getAttribute("role");
		switch (role) {
		case "user":
			operation = "用户登出系统";
			break;
		case "admin":
			operation = "管理员登出系统";
			break;
		default:
			operation = "超级管理员登出系统";
			break;
		}
		mes = "成功";
		writeLog(operator, operation, mes);
	}
}
