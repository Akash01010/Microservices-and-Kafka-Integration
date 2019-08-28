package com.sapient.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sapient.kafka.model.OrderDetails;

@Service
public class OrderConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);
	
	
    @KafkaListener(topics = "${app.topic.example}")
    public void receive(@Payload OrderDetails data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);

        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
    }

}
