package com.sapient.ops.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.ops.model.Product;



@Repository 
public interface ProductRepository extends JpaRepository<Product, Long> { 
	
} 