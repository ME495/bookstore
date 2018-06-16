package com.bookstore.service;

import java.util.ArrayList;

import com.bookstore.entity.Order;
import com.bookstore.entity.Trolley4Pay;
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
	
	/**
	 * 根据ISBN和新旧度获取图书价格
	 * @param isbn
	 * @param degree
	 * @return double
	 */
	public double getActualPrice(String isbn, int degree);
	
	/**
	 * 获取待支付总价
	 * @param trolleyMsg
	 * @return double
	 */
	public double getPrice2Pay(String trolleyMsg);
	
	/**
	 * 插入订单
	 * @param order
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes insertOrder(Order order);
	
	/**
	 * 插入订单详情
	 * @param orderId
	 * @param degree
	 * @param isbn
	 * @param unitPrice
	 * @param num
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes insertOrderBook(int orderId, int degree, String isbn, double unitPrice, int num);
	
	/**
	 * 获取订单详情
	 * @param orderId
	 */
	public ArrayList<Trolley4Pay> getOrderBook(int orderId);
	
	/**
	 * 插入订单ID_支付ID映射
	 * @param orderId
	 * @param payment_id
	 * 成功返回“{“status”: “success”}”
	 * 失败返回“{“status”: “fail”}”
	 */
	public ResponseMes insertOrderPayment(int orderId, String paymentId);
	
	/**
	 * 从order_payment表中获取order_id
	 * @param payment_id
	 */
	public int getOrderId(String paymentId);

}
