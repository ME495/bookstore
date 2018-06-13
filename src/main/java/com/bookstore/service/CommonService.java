package com.bookstore.service;

import com.bookstore.message.ResponseMes;

/**
 * 管理员和用户都可以使用的功能,主要是查询图书的相关信息
 * 
 * @author xiaoxiong
 *
 */
public interface CommonService {
  /**
   * 根据ISBN和degree获得书籍的具体信息
   * 
   * @param isbn
   *          ISBN号
   * @param degree
   *          书籍的新旧程度
   * @return 成功或失败信息,包含具体的书籍信息
   */
  public ResponseMes getBookByIsbnAndDegree(String isbn, int degree);

  /**
   * 根据ISBN号,获得书籍新旧程度列表
   * 
   * @param isbn
   *          ISBN号
   * @return 成功或失败信息,包含书籍的新旧程度列表
   */
  public ResponseMes getBookDegreesByIsbn(String isbn);

  /**
   * 根据index和size获取书籍列表
   * 
   * @param index
   *          搜索记录里的开始位置
   * @param size
   *          每页显示的书籍数量
   * @return 成功或失败信息,包含书籍列表
   */
  public ResponseMes getBooks(int index, int size);

  /**
   * 根据关键字获取书籍列表
   * 
   * @param keyWord
   *          搜索关键字
   * @param index
   *          搜索记录里的开始位置
   * @param size
   *          每页显示的书籍数量
   * @return 成功或失败信息,包含书籍列表
   */
  public ResponseMes getBooksByKeyWord(String keyWord, int index, int size);

  /**
   * 用户注册
   * 
   * @param userName
   *          账号
   * @param password
   *          密码
   * @param phone
   *          联系方式
   * @param realName
   *          真实名字
   * @param address
   *          地址
   * @return 注册成功或失败信息
   */
  public ResponseMes insertUser(String userName, String password, String phone, String realName, String address);
}
