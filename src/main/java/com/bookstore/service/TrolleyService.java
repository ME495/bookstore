package com.bookstore.service;

import com.bookstore.message.ResponseMes;

/**
 * 购物车
 * @author JinQi
 *
 */
public interface TrolleyService {
	
	/**
	 * 向用户的购物车中添加商品
	 * @param userName
	 * @param isbn
	 * @param degree
	 * @param num
	 * @return ResponseMes
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes insertTrolley(String userName, String isbn, int degree, int num);
	
	/**
	 * 向用户的购物车中删除商品
	 * @param userName
	 * @param isbn
	 * @param degree
	 * @return ResponseMes
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes deleteTrolley(String userName, String isbn, int degree);
	
	/**
	 * 修改用户的购物车中商品的数量
	 * @param userName
	 * @param isbn
	 * @param degree
	 * @param num
	 * @return ResponseMes
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes updateTrolley(String userName, String isbn, int degree, int num);
	
	/**
	 * 查询用户的购物车信息
	 * @param userName
	 * @return ResponseMes
	 * 成功返回“{“status”: “success”, ArrayList<Trolley>}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes selectTrolley(String userName);

}
