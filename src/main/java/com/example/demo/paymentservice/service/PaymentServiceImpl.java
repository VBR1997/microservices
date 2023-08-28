package com.example.demo.paymentservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.paymentservice.entity.TransactionDetails;
import com.example.demo.paymentservice.model.PaymentMode;
import com.example.demo.paymentservice.model.PaymentRequest;
import com.example.demo.paymentservice.model.PaymentResponse;
import com.example.demo.paymentservice.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	TransactionDetailsRepository transactionDetailsRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recoding payment details::{}",paymentRequest);
		
		TransactionDetails transactionDetails=TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCESS")
				.orderid(paymentRequest.getOrderid())
				.referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount())
				.build();
		
		transactionDetailsRepository.save(transactionDetails);
		
		return transactionDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
		
		TransactionDetails transactionDetails=transactionDetailsRepository.findByOrderid(orderId);
		
		PaymentResponse paymentResponse=PaymentResponse.builder()
				.amount(transactionDetails.getAmount())
				.orderId(orderId)
				.paymentDate(transactionDetails.getPaymentDate())
				.paymentid(transactionDetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.status(transactionDetails.getPaymentStatus())
				.build();
		return paymentResponse;
	}
}
