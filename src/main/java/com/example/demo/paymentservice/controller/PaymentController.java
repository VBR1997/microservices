package com.example.demo.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.paymentservice.model.PaymentRequest;
import com.example.demo.paymentservice.model.PaymentResponse;
import com.example.demo.paymentservice.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/payment")
@Log4j2
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Long>  doPayment(@RequestBody PaymentRequest paymentRequest)
	{
		log.info("payment detials:::{}",paymentRequest);
		
		long transactionid=paymentService.doPayment(paymentRequest);
		return new ResponseEntity<Long>(transactionid,HttpStatus.OK);
	}
	
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable long orderId)
	{
		PaymentResponse paymentResponse=paymentService.getPaymentDetailsByOrderId(orderId);
		return new ResponseEntity<PaymentResponse>(paymentResponse,HttpStatus.OK);
	}
}
