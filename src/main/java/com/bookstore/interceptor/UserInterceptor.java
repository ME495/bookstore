package com.bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("ok");
		HttpSession httpSession = request.getSession();
		String role = (String) httpSession.getAttribute("role");
		if (role == null || !role.equals("user")) {
			response.sendRedirect("../user_login.html");
			return false;
		} else {
			return true;
		}
	}
}
