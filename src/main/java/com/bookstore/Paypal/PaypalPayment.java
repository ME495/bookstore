package com.bookstore.Paypal;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalPayment {
	
	private static final String CLIENT_ID = "Ad_tvce_uS0eTjVEK7-Ztr6TIvG2wFL2p_YvZsBcmfH_6X7Qn33IvRVo14lSPJCBZv_-Griz-dcyLfDF"; // Sandbox
	private static final String CLIENT_SECRET = "EPj9Dmc0_HE7VmXfqpHh1lwHmRAhJ7vrxsDjVOMMCn9bizaiP7IELu3scY700Tnx0HZslXLMg01JvTqE"; //Sandbox
	private static final String MODE = "sandbox"; // or live
	private static final String CANCEL_URL = "http://localhost:8081/bookstore/user/paymentCancelled.html"; // TODO 你的真实取消地址
	private static final String RETURN_URL = "http://localhost:8081/bookstore/user/paypalState.html"; // TODO 你的paypal返回调用地址
	private static final String CURRENCY = "HKD";
	private static final String DESCRIPTION = "湘大旧书店订单";
	private static final String METHOD = "paypal";
	private static final String INTENT = "sale";
	private static APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	
	/**Create Payment Object
     * @param total--金额
     * @throws PayPalRESTException 
     */
	public static Payment createPayment(String total) throws PayPalRESTException {
		Payer payer = new Payer();
		payer.setPaymentMethod(METHOD);
		Amount amount = new Amount();
		amount.setTotal(total);
		amount.setCurrency(CURRENCY);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(DESCRIPTION);
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(CANCEL_URL);
		redirectUrls.setReturnUrl(RETURN_URL);
		
		Payment payment = new Payment();
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		payment.setRedirectUrls(redirectUrls);
		payment.setIntent(INTENT);
		System.out.println("PaypalPayment.java said: " + payment.create(apiContext).toString());
		
		return payment.create(apiContext);
	}
	
	public static Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        
        return payment.execute(apiContext, paymentExecute);
	}

}
