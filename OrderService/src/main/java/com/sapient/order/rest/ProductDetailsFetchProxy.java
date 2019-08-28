package com.sapient.order.rest;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.order.model.OrderDetails;

@FeignClient(name="product-service")
@RibbonClient(name="product-service")
public interface ProductDetailsFetchProxy {
	@GetMapping("/api/products/{id}")
	public OrderDetails retrieveProductDetails (@PathVariable("id") String product_id);
}
