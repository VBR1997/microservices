package com.example.demo.productservice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.productservice.enity.Product;
import com.example.demo.productservice.exception.ProductServiceCustomException;
import com.example.demo.productservice.model.ProductRequest;
import com.example.demo.productservice.model.ProductResponse;
import com.example.demo.productservice.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("ProductServiceImpl - addProduct -start");
		Product product=Product.builder()
				.productName(productRequest.getName())
				.price(productRequest.getPrice())
				.quanity(productRequest.getQuanity())
				.build();
		
		productRepository.save(product);
		log.info("ProductServiceImpl - addProduct -end");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(Long productId) {
		log.info("ProductServiceImpl - getProductById -end");
		Product product=productRepository
							.findById(productId)
								.orElseThrow(()->new ProductServiceCustomException("Prodcut with given id is not avialable","PRODUCT_NOT_FOUND"));
		ProductResponse productResponse=new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		
		log.info("ProductServiceImpl - getProductById -end");
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("ProductServiceImpl - reduceQuantity -start");
		log.info("Reduce Quantity {} for product ID {}", quantity,productId);
		Product product=productRepository.findById(productId)
				.orElseThrow(()->new ProductServiceCustomException("Prodcut with given id is not avialable","PRODUCT_NOT_FOUND"));;
	
		if(product.getQuanity() <quantity)
		{
			throw new ProductServiceCustomException("PRoduct does not sufficient quantity","INSUFFICIENT_QUANITY");
		}

		//update the quantity .. into database 
		long reduceQuantity=product.getQuanity()-quantity;
		product.setQuanity(reduceQuantity);
		
		productRepository.save(product);
		log.info("Product quantity updated successfully.....");
		log.info("ProductServiceImpl - reduceQuantity -end");
	}
	
	
	
}
