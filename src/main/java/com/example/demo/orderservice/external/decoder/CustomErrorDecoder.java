package com.example.demo.orderservice.external.decoder;

import java.io.IOException;

import com.example.demo.orderservice.exception.CustomOrderException;
import com.example.demo.orderservice.external.response.ErrorResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{
	
	
	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper=new ObjectMapper();
		
		log.info(":::{} response request url::::",response.request().url());
		log.info(":::{} response request headers::::",response.request().headers());
		try {
			ErrorResponse errorResponse=objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new CustomOrderException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());
			
		} catch (Exception e) {
			
			return new CustomOrderException("INTERNAL SERVER ERROR", "INTERNAL_SERVER_ERROR", 500);
		}
		
		
	}

}
