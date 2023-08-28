package com.example.demo.productservice.excpetion.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.productservice.exception.ProductServiceCustomException;
import com.example.demo.productservice.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleProduceServiceExcepiton(ProductServiceCustomException productServiceCustomException)
	{
		ErrorResponse errorResponse =ErrorResponse.builder()
							.errorMessage(productServiceCustomException.getMessage())
							.errorCode(productServiceCustomException.getErrorCode())
							.build();
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
}
