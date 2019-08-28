package com.sapient.ops.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomerException extends Exception {
	private String message;
	
	public CustomerException() {
		
	}

	public CustomerException(String message) {
		super(message);		
	}
	
	public CustomerException(String message,Exception e) {
		super(message,e);
	}
	

	@Override
	public String getMessage() {
		return this.message+"\n"+super.getMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
