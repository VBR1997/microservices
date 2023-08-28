package com.example.demo.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.paymentservice.entity.TransactionDetails;
@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long>{

	
		TransactionDetails findByOrderid(long orderid);

	
}
