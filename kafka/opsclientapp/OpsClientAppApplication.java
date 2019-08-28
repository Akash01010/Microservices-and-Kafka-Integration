package com.sapient.opsclientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.ops.model.Order;
import com.sapient.ops.producer.OrderSender;

@SpringBootApplication
public class OpsClientAppApplication implements CommandLineRunner{

	@Autowired
	private OrderSender sender;

	public static void main(String[] args) {
		SpringApplication.run(OpsClientAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Order order = new Order();
        sender.send(order);
	}

}
