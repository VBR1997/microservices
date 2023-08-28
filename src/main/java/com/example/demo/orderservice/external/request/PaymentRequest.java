package com.example.demo.orderservice.external.request;

import com.example.demo.orderservice.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private long orderid;
	private long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;
}
