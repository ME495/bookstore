package com.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.ErrorMessage;
import com.bookstore.message.ResponseMes;
import com.bookstore.service.LoginService;

/**
 * 用户过滤器.
 * 对于非用户访问 /admin 文件夹下的内容，重定向到管理员登陆页面.
 * @author ME495
 *
 */
@WebFilter("/user/*")
public class UserFilter implements Filter {

	@Autowired
	private LoginService loginService;
	
    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpServletRequest.getSession();
		String role = (String) httpSession.getAttribute("role");
		if (role == null || !role.equals("user")) {
			httpServletResponse.sendRedirect("../user_login.html");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
