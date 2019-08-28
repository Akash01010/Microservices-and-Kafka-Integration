package com.sapient.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sapient.ops.dao.ProductRepository;
import com.sapient.ops.exception.ResourceNotFoundException;
import com.sapient.ops.model.Product;

@Service
public class ProductService implements IProductService{

	@Autowired
	ProductRepository productRepository ;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll() ;
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product) ;
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new com.sapient.ops.exception.ResourceNotFoundException("Product", "id", productId)); 
	}

	@Override
	public Product updateProduct(Product product) {
		Product product2 = productRepository.findById(product.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product", "id", product.getProductId())) ;
		product2.setProductName(product.getProductName());
		product2.setDescription(product.getDescription());
		product2.setBuyingPrice(product.getBuyingPrice());
		return productRepository.save(product2) ;
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId)) ;
		productRepository.delete(product);
		return ResponseEntity.ok().build(); 
	}

}
