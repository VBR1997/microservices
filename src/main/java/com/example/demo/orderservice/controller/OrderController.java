package com.example.demo.orderservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderservice.model.OrderRequest;
import com.example.demo.orderservice.model.OrderResponse;
import com.example.demo.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
 @Autowired
 OrderService orderService;
 
 @PostMapping("/placeOrder")
 public ResponseEntity<Long> getPlaceOrder(@RequestBody  OrderRequest orderRequest)
 {
	 long orderId=orderService.getPlaceOrder(orderRequest);
	 
	 return new ResponseEntity<Long>(orderId,HttpStatus.CREATED);
 }
 
 
 @GetMapping("/{orderid}")   //path param
 public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderid)
 {
	 OrderResponse orderResponse=orderService.getOrderDetails(orderid);
	 return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
 }
 
 
}
