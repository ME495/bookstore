package com.bookstore.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bookstore.mapper.UserMapper;
import com.bookstore.message.ResponseMes;

@Aspect
public class AdminLogAspect {
	private HttpSession session;
	private String operator;
	private String mes;
	private Logger log = Logger.getLogger(AdminLogAspect.class);
	@Autowired
	private UserMapper userMapper;

	@After(value = "execution(* com.bookstore.service.AdminService.*Book*(..)) && args(isbn,..)", argNames = "isbn")
	public void bookLog(JoinPoint point, String isbn) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		session = request.getSession();
		//获得拦截的方法名,根据方法名记录不同的信息
		String methodName = point.getSignature().getName();
		// 进行操作的管理员名称
		operator = (String) session.getAttribute("name");
		MDC.put("operator", operator);
		if (methodName.equals("addBook")) {
			mes = "新增了图书其isbn为：" + isbn;
		} else if (methodName.equals("deleteBook")) {
			mes = "删除了图书其isbn为：" + isbn;
		} else {
			mes = "修改了图书其isbn为：" + isbn;
		}
		MDC.put("mes", mes);
		//打印日志
		log.info("");
	}

	@Pointcut(value = "execution(* com.bookstore.service.AdminService.*User*(..)) &&args(userName,..)", argNames = "userName")
	public void userPoint(String userName) {
	}

	@Around("userPoint(userName)")
	public ResponseMes checkUser(ProceedingJoinPoint pjp,String userName) throws Throwable {
		//将判断用户是否存在的逻辑,提取出来
		ResponseMes responseMes;
		if (userMapper.getUser(userName) == null) {// 用户不存在,则返回错误信息
			responseMes = new ResponseMes(ResponseMes.FAIL, "该用户不存在");
		} else {
			responseMes = (ResponseMes) pjp.proceed();
		}
		return responseMes;
	}

	@AfterReturning(value="userPoint(userName)",returning="responseMes")
	public void userLog(JoinPoint point, String userName,ResponseMes responseMes) {
		//操作成功则记录日志
		if(responseMes.getStatus().equals(ResponseMes.SUCCESS)) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			session = request.getSession();
			//获得拦截的方法名,根据方法名记录不同的信息
			String methodName = point.getSignature().getName();
			// 进行操作的管理员名称
			operator = (String) session.getAttribute("name");
			MDC.put("operator", operator);
			if (methodName.equals("deleteUser")) {
				mes = "删除了用户信息其账号为：" + userName;
			} else {
				mes = "修改了用户信息其账号为：" + userName;
			}
			MDC.put("mes", mes);
			log.info("");
		}
	}
}
