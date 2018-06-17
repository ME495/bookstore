package com.bookstore.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.Paypal.PaypalPayment;
import com.bookstore.entity.Order;
import com.bookstore.entity.Trolley4Pay;
import com.bookstore.entity.User;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.OrderManagerService;
import com.bookstore.service.TrolleyService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping(value="/user", produces = "application/json;charset=utf-8")
public class TrolleyController {
	
	public final static int PAYMENT_APPROVED = 0;
	public final static int PAYMENT_CANCELLED = 1;
	public final static int PAYMENT_ERROR = 2;
	
	@Autowired
	private TrolleyService trolleyService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderManagerService orderManagerService;
	
	@ResponseBody
	@RequestMapping(value="/trolley_add.do")
	public String insertTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			@RequestParam("num")int num, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.insertTrolley(userName, isbn, degree, num));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_delete.do")
	public String deleteTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.deleteTrolley(userName, isbn, degree));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_update.do")
	public String updateTrolley(@RequestParam("isbn")String isbn, @RequestParam("degree")int degree, 
			@RequestParam("num")int num, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.updateTrolley(userName, isbn, degree, num));
	}
	
	@ResponseBody
	@RequestMapping(value="/trolley_check.do")
	public String selectTrolley(HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		return JSON.toJSONString(trolleyService.selectTrolley(userName));
	}
	
	@ResponseBody
	@RequestMapping(value="/get_price_to_pay.do")
	public double getPrice2pay(String trolleyMsg) {
		return trolleyService.getPrice2Pay(trolleyMsg);
	}
	
	/*
	@RequestMapping(value="/return_payment_status.do")
	public void recvPaymentStatus(String paymentMsg, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		JSONObject paymentMsgObj = JSON.parseObject(paymentMsg);
		if(paymentMsgObj.get("status").equals("success")) {
			trolleyService.recvPaymentStatus(userName, PAYMENT_APPROVED);
		} else {
			if(paymentMsgObj.get("msg").equals("paymentCancelled")) {
				trolleyService.recvPaymentStatus(userName, PAYMENT_CANCELLED);
			} else {
				trolleyService.recvPaymentStatus(userName, PAYMENT_ERROR);
			}
		}
	}
	*/
	
	@RequestMapping(value="/payment.do")
	@ResponseBody
	public String doPayment(@RequestParam("trolleyMsg") String trolleyMsg, HttpSession httpSession) {
		double totalMoney = getPrice2pay(trolleyMsg);
		String total = ""+totalMoney;
		Payment payment;
		String userName = (String) httpSession.getAttribute("name");

		try {
			payment = PaypalPayment.createPayment(total);
			for(Links link : payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					User user = userMapper.getUser(userName);
					String address = user.getAddress();
					Date date = new Date(); // 获得系统时间
			        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date); // 将时间格式转换成符合Timestamp要求的格式
			        Timestamp orderDatetime = Timestamp.valueOf(nowTime);
					Order order = new Order(userName, address, orderDatetime, -1, totalMoney);
					trolleyService.insertOrder(order); // 插入待支付状态订单
					List<Trolley4Pay> troMsg = JSONObject.parseArray(trolleyMsg, Trolley4Pay.class);
					int orderId = Integer.parseInt(order.getOrderId()); // TODO 如果Order类中的orderId类型改了这里就要改
					String paymentId = payment.getId();
					for(int i = 0; i < troMsg.size(); i++) { // 插入订单详情
						Trolley4Pay tro4pay = troMsg.get(i);
						double unitPrice = trolleyService.getActualPrice(tro4pay.getIsbn(), tro4pay.getDegree());
						trolleyService.insertOrderBook(orderId, tro4pay.getDegree(), tro4pay.getIsbn(),
								unitPrice, tro4pay.getNum());
					}
					trolleyService.insertOrderPayment(orderId, paymentId); // 插入订单ID-支付ID映射
					return link.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		
		return "system/pay/paypal/failedUrl"; // TODO
	}
	
	/*
	@RequestMapping(value="/testpayment.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String doPaymentTest() throws PayPalRESTException {
		String total = "1.88";
		Payment payment = PaypalPayment.createPayment(total);
		for(Links link : payment.getLinks()) {
			if(link.getRel().equals("approval_url")) {
				return link.getHref();
			}
		}
		return "system/pay/paypal/failedUrl";
	}
	*/
	
	@RequestMapping(value="/paypalCancel.do")
    public String cancelPay() {
		return "system/pay/paypal/cancelUrl"; // TODO
    }
	
	/**客户登陆付款后paypal返回路径参数示例
	*http://域名/user/paypalReturn.do?paymentId=PAY-339981922W118522HLJLQF3A&token=EC-9K664484GE997692K&PayerID=LEBMCXS5RQ7AU
	*/
	@RequestMapping(value="/paypalReturn.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String successPay(@RequestParam("paymentId") String paymentId, 
			@RequestParam("PayerID") String payerId, HttpSession httpSession) {
		String userName = (String) httpSession.getAttribute("name");
		try {
			Payment payment = PaypalPayment.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")) {
	        	int orderId = trolleyService.getOrderId(paymentId);
	        	orderManagerService.approveOrder(orderId); // 将对应订单状态设为0（已支付）
	        	ArrayList<Trolley4Pay> orderBooks = trolleyService.getOrderBook(orderId); // 获取订单详情
	        	for(int i = 0; i < orderBooks.size(); i++) { // 根据订单详情逐条删除用户购物车中相关条目
	        		Trolley4Pay orderBook = orderBooks.get(i);
	        		trolleyService.deleteTrolley(userName, orderBook.getIsbn(), orderBook.getDegree());
	        	}
	        	return "paymentSuccess";
			}
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return "system/pay/paypal/failed";
	}

}
