package com.sapient.ops.service;

import java.util.List;

import com.sapient.ops.exception.CustomerException;
import com.sapient.ops.model.Customer;


public interface CustomerService {
	public abstract String addCustomer(Customer customer) throws CustomerException;
	public abstract List<Customer> getAllCustomerDetails() throws CustomerException;
	public abstract Customer getCustomerById(Integer ID) throws CustomerException;
	public abstract Integer deleteCustomer(Integer ID) throws CustomerException;
	public abstract Integer updateCustomer(Customer customer) throws CustomerException;
}
