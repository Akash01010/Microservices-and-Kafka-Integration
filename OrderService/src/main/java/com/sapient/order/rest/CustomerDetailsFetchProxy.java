package com.sapient.order.rest;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.order.model.OrderDetails;

@FeignClient(name="customer-service")
@RibbonClient(name="customer-service")

public interface CustomerDetailsFetchProxy {
	@GetMapping("/capi/byID/{id}")
	public OrderDetails retrieveCustomerDetails (@PathVariable("id") String customer_id);
}
