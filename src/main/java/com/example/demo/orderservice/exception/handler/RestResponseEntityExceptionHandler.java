package com.example.demo.orderservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.orderservice.exception.CustomOrderException;
import com.example.demo.orderservice.external.response.ErrorResponse;
@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CustomOrderException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceExcpeiton(CustomOrderException excpetion)
	{
		return new ResponseEntity<ErrorResponse>(

				new ErrorResponse().builder()
				.errorMessage(excpetion.getMessage())
				.errorCode(excpetion.getErrorCode())
				.build(),
				
				HttpStatus.valueOf(excpetion.getStatus())
				
				);
	}
}
