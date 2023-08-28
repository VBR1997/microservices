package com.example.demo.paymentservice.service;

import com.example.demo.paymentservice.model.PaymentRequest;
import com.example.demo.paymentservice.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(long orderId);

}
