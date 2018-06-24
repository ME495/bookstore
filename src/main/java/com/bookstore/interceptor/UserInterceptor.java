package com.bookstore.interceptor;

import com.bookstore.entity.ErrorMessage;
import com.bookstore.message.ResponseMes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户拦截器
 * 拦截非用户对于 /user/*.do 的请求
 * @author ME495
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截并处理 /user/*.do 的请求
     * @param request
     * @param response
     * @param handler
     * @return 如果是user，则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        String role = (String) httpSession.getAttribute("role");
        if (role == null || !role.equals("user")) {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ResponseMes mes = new ResponseMes(ResponseMes.FAIL, new ErrorMessage("权限不够！"));
            response.getWriter().write(mes.toJsonString());
            return false;
        } else {
            return true;
        }
    }
}
