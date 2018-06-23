package com.bookstrore.aspects;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class AdminLogAspect {
  private HttpSession session;
  private String adminName;
  private Logger log = Logger.getLogger(AdminLogAspect.class);

  @After(value = "execution(* com.bookstore.service.AdminService.*Book*(..)) && args(isbn,..)", argNames = "isbn")
  public void bookLog(JoinPoint point, String isbn) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    session = request.getSession();
    String methodName = point.getSignature().getName();
    // 进行操作的管理员名称
    adminName = (String) session.getAttribute("name");
    if (methodName.equals("addBook")) {
      log.info(adminName + "\t新增了图书其isbn为：\t" + isbn);
    } else if (methodName.equals("deleteBook")) {
      log.info(adminName + "\t删除了图书其isbn为：\t" + isbn);
    } else {
      log.info(adminName + "\t修改了图书其isbn为：\t" + isbn);
    }
  }

  @After(value = "execution(* com.bookstore.service.AdminService.*User*(..)) &&args(userName,..)", argNames = "userName")
  public void userLog(JoinPoint point, String userName) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    session = request.getSession();
    String methodName = point.getSignature().getName();
    // 进行操作的管理员名称
    adminName = (String) session.getAttribute("name");
    if (methodName.equals("deleteUser")) {
      log.info(adminName + "\t删除了用户其账号为：\t" + userName);
    } else {
      log.info(adminName + "\t修改了用户其账号为：\t" + userName);
    }
  }
}
