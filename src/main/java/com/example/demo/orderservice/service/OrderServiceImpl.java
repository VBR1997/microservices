package com.example.demo.orderservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.orderservice.entity.Order;
import com.example.demo.orderservice.exception.CustomOrderException;
import com.example.demo.orderservice.external.client.PaymentService;
import com.example.demo.orderservice.external.client.ProductService;
import com.example.demo.orderservice.external.request.PaymentRequest;
import com.example.demo.orderservice.external.response.PaymentResponse;
import com.example.demo.orderservice.external.response.ProductResponse;
import com.example.demo.orderservice.model.OrderRequest;
import com.example.demo.orderservice.model.OrderResponse;
import com.example.demo.orderservice.repository.OrderRepository;

import brave.messaging.ProducerResponse;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentService paymentService;

	@Autowired
	RestTemplate restTemplate;
	@Override
	public long getPlaceOrder(OrderRequest orderRequest) {
		/* 
		 step 1 : Order place ==>data save order created
		 ste 2 :PRdocut service==>product decrease/reduce the quantity
		 step 3 : payment service==>payment success ,failure (order cancel)	
		 */
	log.info("Placing order request {}", orderRequest);
	productService.reduceQuantity(orderRequest.getProductid(), orderRequest.getQuantity());
	
	
		Order order=Order.builder()
					.amount(orderRequest.getTotalAmount())
					.orderStatus("CREATED")
					.productid(orderRequest.getProductid())
					.orderDate(Instant.now())
					.quantity(orderRequest.getQuantity())
					.build();
		orderRepository.save(order);
		log.info("calling payment serice to complete the payment transaction...");
		
		PaymentRequest paymentRequest=PaymentRequest.builder()
				.orderid(order.getId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getTotalAmount())
				
				.build();
				log.info("order service::::::::{}",paymentRequest);
		String orderStatus=null;
		//success ==failure 
		try
		{
			paymentService.doPayment(paymentRequest);
			log.info("Payment done successfully....");
			orderStatus="PLACED";
		}
		catch (Exception e) {
			log.info("error occured in payment....PAYMENT_FAILED");
			orderStatus="PAYMENT_FAILED";
			
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
	log.info("Order placed successfully with orderr id :{}",order.getId());
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderid) {
		log.info("Get ORder details based on order id:{}",orderid);	
		Order order=orderRepository.findById(orderid)
					.orElseThrow(()->new CustomOrderException("order not found for perticular order id :"+orderid,
							"NOT_FOUND", 404));
	OrderResponse orderResponse =OrderResponse.builder()
				.orderid(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.build();
	log.info("invoking PRoduct service to fetch the PRoduct details by using ProductID::"+order.getProductid());
	
	PaymentResponse paymentResponse
	=restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(),
			PaymentResponse.class);
	orderResponse.setPaymentResponse(paymentResponse);
	
	ProductResponse productResponse=	restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductid(), ProductResponse.class);
//	OrderResponse productOrderDetails=OrderResponse.builder()
//			.productId(order.getProductid())
//			.ProductName(productResponse.getProductName())
//					.build();
	orderResponse.setProductResponse(productResponse);
	
	
		return orderResponse;
	}

}
