package com.example.demo.orderservice.model;

import java.time.Instant;

import com.example.demo.orderservice.external.response.PaymentResponse;
import com.example.demo.orderservice.external.response.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

		private long orderid;
		private String orderStatus;
		private long amount;
		private Instant orderDate;
		private ProductResponse productResponse;
		private PaymentResponse paymentResponse;
		
		
}
