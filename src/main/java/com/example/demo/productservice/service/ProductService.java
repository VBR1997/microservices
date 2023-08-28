package com.example.demo.productservice.service;

import com.example.demo.productservice.model.ProductRequest;
import com.example.demo.productservice.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(Long productId);

	void reduceQuantity(long productId, long quantity);

}
