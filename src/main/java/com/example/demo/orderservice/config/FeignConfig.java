package com.example.demo.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.orderservice.external.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	@Bean
	public ErrorDecoder errorDecoder()
	{
		return new CustomErrorDecoder();
	}
}
