package com.sapient.order.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.sapient.order.dao.OrderDetailsRepository;
import com.sapient.order.model.OrderDetails;
import com.sapient.order.rest.CustomerDetailsFetchProxy;
import com.sapient.order.rest.ProductDetailsFetchProxy;

@Service
@RestController
public class FetchDetails {
	@Autowired
	private CustomerDetailsFetchProxy customerProxy;
	
	@Autowired
	private ProductDetailsFetchProxy productProxy;
	
	@Autowired
    OrderDetailsRepository orderRepo;
	
	@Autowired
    private KafkaTemplate<String, OrderDetails> kafkaTemplate;

    @Value("${app.topic.example}")
    private String topic;
    
    
	@GetMapping("/fetch-order-details/customer_id/{cust_id}/product_id/{prod_id}")
	  public OrderDetails convertCurrencyFeign(@PathVariable String cust_id, @PathVariable String prod_id) {
		
		OrderDetails customer = customerProxy.retrieveCustomerDetails(cust_id);
		OrderDetails product = productProxy.retrieveProductDetails(prod_id);

//	    OrderDetails myOrder = new OrderDetails(customer.getCustomer_id(),product.getProduct_id(),1,230.66);
		OrderDetails myOrder = new OrderDetails(1L,1L,1,230.66);
//	    orderRepo.save(myOrder);
	    
	    //send to kafka
	    Message<OrderDetails> message = MessageBuilder
                .withPayload(myOrder)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
	    return myOrder;
	  }
}
