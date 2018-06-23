package com.bookstrore.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class AdminLogAspect {
	private HttpSession session;
	private String operator;
	private String mes;
	private Logger log = Logger.getLogger(AdminLogAspect.class);

	@After(value = "execution(* com.bookstore.service.AdminService.*Book*(..)) && args(isbn,..)", argNames = "isbn")
	public void bookLog(JoinPoint point, String isbn) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		session = request.getSession();
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
		log.info("");
	}

	@Pointcut(value = "execution(* com.bookstore.service.AdminService.*User*(..)) &&args(userName,..)", argNames = "userName")
	public void userPoint(String userName) {
	}
	
	@After("userPoint(userName)")
	public void userLog(JoinPoint point, String userName) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		session = request.getSession();
		String methodName = point.getSignature().getName();
		// 进行操作的管理员名称
		operator = (String) session.getAttribute("name");
		MDC.put("operator", operator);
		if (methodName.equals("deleteUser")) {
			mes = "删除了用户其账号为：" + userName;
		} else {
			mes = "修改了用户其账号为：" + userName;
		}
		MDC.put("mes", mes);
		log.info("");
	}
}
