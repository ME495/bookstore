package com.bookstore.aspect;

import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.bookstore.message.ResponseMes;

@Component
@Aspect
@PropertySource(value = "classpath:methodMap.properties", encoding = "utf-8")
public class LogAspect {
  private Logger log = Logger.getLogger(AdminAspect.class);
  private HttpSession session;
  private String operator;// 操作者
  private String operation;// 操作
  private String mes;// 返回信息
  @Autowired
  private Environment env;

  /**
   * 将日志写入数据库
   * 
   * @param methodName
   *          目标方法的名字
   * @param operator
   *          操作者
   * @param operation
   *          操作简介
   * @param mes
   *          返回消息
   */
  public void writeLog(String methodName, String operator, String operation, String mes) {
    MDC.put("methodName", methodName);
    MDC.put("operator", operator);
    MDC.put("operation", operation);
    MDC.put("mes", mes);
    log.info("");
  }

  public ResponseMes formatJson(String jsonStr) {
    return JSON.parseObject(jsonStr, ResponseMes.class);
  }

  /**
   * 登出的日志
   * 
   * @param jp
   */
  @Before(value = "execution(* com.bookstore.controller.LogoutController.*(..))")
  public void logoutLog(JoinPoint jp) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    session = request.getSession();
    // 获取方法名
    String methodName = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
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
    writeLog(methodName, operator, operation, mes);
  }

  /**
   * 记录大部分的日志
   * 
   * @param jp
   * @param jsonStr
   *          返回消息
   */
  @AfterReturning(value = "execution(* com.bookstore.controller.*.*(..)) && !execution(* com.bookstore.controller.LogoutController.*(..))", returning = "jsonStr")
  public void mainLog(JoinPoint jp, String jsonStr) {
    // 获取方法名
    String methodName = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
    // 获取参数名/值
    HashMap<String, Object> map = new HashMap<String, Object>();
    String[] paramNames = ((MethodSignature) jp.getSignature()).getParameterNames();
    Object[] paramValues = jp.getArgs();
    for (int i = 0; i < paramNames.length; i++) {
      map.put(paramNames[i], paramValues[i]);
    }
    // 格式化返回信息
    ResponseMes response = formatJson(jsonStr);
    buildOperator(methodName, map);
    buildOperation(methodName, map);
    buildMes(response);
    writeLog(methodName, operator, operation, mes);
  }

  /**
   * 获得操作者
   * 
   * @param methodName
   *          目标方法的名字
   * @param map
   *          参数的键值对
   */
  private void buildOperator(String methodName, HashMap<String, Object> map) {
    if (methodName.endsWith("insertUser")) {// 用户注册的操作者在参数列表里
      operator = (String) map.get("userName");
    } else if (methodName.contains("CommonController")) {// 未注册用户
      operator = "游客";
    } else {// 其余的操作者在session中
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
          .getRequest();
      session = request.getSession();
      operator = (String) session.getAttribute("name");
    }
  }

  /**
   * 获取功能简介
   * 
   * @param methodName
   *          目标方法的名字
   * @param map
   *          参数的键值对
   */
  private void buildOperation(String methodName, HashMap<String, Object> map) {
    // 通过配置文件获得方法对应的简介
    StringBuilder sb = new StringBuilder(env.getProperty(methodName) + "(");
    // 方法的参数
    Set<String> keys = map.keySet();
    for (String key : keys) {
      if (!key.toUpperCase().contains("SESSION") && !key.contains("password"))
        sb.append(key + ":" + map.get(key) + ",");
    }
    if (sb.charAt(sb.length() - 1) == '(')
      sb.deleteCharAt(sb.length() - 1);
    else
      sb.setCharAt(sb.length() - 1, ')');
    operation = sb.toString();
    // System.out.println(operation);
  }

  /**
   * 获取方法的执行情况（成功或失败）
   * 
   * @param response
   *          响应消息
   */
  public void buildMes(ResponseMes response) {
    if (response.getStatus().equals(ResponseMes.SUCCESS)) {
      mes = "成功";
    } else {
      mes = "失败(" + response.getMessage() + ")";
    }
  }

}
