package com.example.demo.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
	
	private long productid;
	private long totalAmount;
	private long quantity;
	private PaymentMode paymentMode;

}
