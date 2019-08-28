package com.sapient.ops.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sapient.ops.model.Product;




public interface IProductService {
	public List<Product> getAllProducts();
	public Product createProduct(Product product);
	public Product getProductById(Long productId);
	public Product updateProduct(Product product) ;
	public ResponseEntity<?> deleteProduct(Long productId);
}
