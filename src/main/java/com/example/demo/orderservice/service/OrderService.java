package com.example.demo.orderservice.service;

import com.example.demo.orderservice.model.OrderRequest;
import com.example.demo.orderservice.model.OrderResponse;

public interface OrderService {

	long getPlaceOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderid);

}
