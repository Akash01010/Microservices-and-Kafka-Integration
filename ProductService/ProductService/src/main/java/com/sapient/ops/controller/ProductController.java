package com.sapient.ops.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.ops.model.Product;
import com.sapient.ops.service.IProductService;


@RestController 
@RequestMapping("/api") 
public class ProductController { 
	
	@Autowired 
	IProductService productService ;
	
	
	
	//Get All Notes 
	@GetMapping("/products") 
	public List<Product> getAllProducts() {
		return productService.getAllProducts(); 
	} 
	
	// Create a new Note @PostMapping("/notes") 
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) { 
		return productService.createProduct(product); 
	} 
	
	// Get a Single Note 
	@GetMapping("/products/{id}") 
	public Product getNoteById(@PathVariable(value = "id") Long productId) { 
		return productService.getProductById(productId);
	} 

	// Update a Note 
	@PutMapping("/products")
	public Product updateNote(@PathVariable @Valid @RequestBody Product product) { 
		return productService.updateProduct(product); 
	} 

	// Delete a Note 
	@DeleteMapping("/products/{id}") 
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) { 
		return productService.deleteProduct(productId); 
	} 
 } 