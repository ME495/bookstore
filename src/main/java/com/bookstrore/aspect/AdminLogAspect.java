package com.bookstrore.aspect;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;
import com.bookstore.utils.LogUtil;

@Component
@Aspect
public class AdminLogAspect extends LogUtil {
	private HttpSession session;
	private String operator;// 操作者
	private String operation;// 操作
	private String mes;// 返回信息
	private Logger log = Logger.getLogger(AdminLogAspect.class);
	@Autowired
	private UserMapper userMapper;
	ResponseMes response;

	@Around(value = "execution(* com.bookstore.service.AdminService.*User*(..)) &&args(userName,..)", argNames = "userName")
	public ResponseMes checkUser(ProceedingJoinPoint pjp, String userName) throws Throwable {
		// 将判断用户是否存在的逻辑,提取出来
		ResponseMes responseMes;
		if (userMapper.getUser(userName) == null) {// 用户不存在,则返回错误信息
			responseMes = new ResponseMes(ResponseMes.FAIL, "该用户不存在");
		} else {
			responseMes = (ResponseMes) pjp.proceed();
		}
		return responseMes;
	}
}
