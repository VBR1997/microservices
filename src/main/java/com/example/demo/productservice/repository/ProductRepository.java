package com.example.demo.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.productservice.enity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
