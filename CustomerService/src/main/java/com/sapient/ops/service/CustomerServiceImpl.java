package com.sapient.ops.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.ops.dao.CustomerDAO;
import com.sapient.ops.entity.CustomerEntity;
import com.sapient.ops.exception.CustomerException;
import com.sapient.ops.model.Customer;
import com.sapient.ops.utility.AppConfig;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO customerDAO;


	@Override
	public String addCustomer(Customer customer) throws CustomerException {
		try {
			CustomerValidator validator=new CustomerValidator();
			if(validator.validate(customer)) {			
				CustomerEntity customerEntity=new CustomerEntity();
				populateCustomerEntity(customer,customerEntity);
				String  status=customerDAO.addCustomer(customerEntity);
				return status;
			}
			throw new CustomerException("Invalid Customer Details");
			
		}catch(PersistenceException e) {
				throw new CustomerException(e.getMessage(),e);
		}


	}

	private void populateCustomerEntity(Customer customer, CustomerEntity customerEntity) {
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setCustomerName(customer.getCustomerName());
		customerEntity.setMobile(customer.getMobile());
		customerEntity.setAddress(customer.getAddress());
		customerEntity.setEmail(customer.getEmail());
		customerEntity.setBirthdate(customer.getBirthdate());
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws CustomerException {
		try {
			List<CustomerEntity> customerEntityList=customerDAO.getAllCustomerDetails();
			//System.out.println(customerEntityList.size());

			if(customerEntityList !=null) {
				List<Customer> customerList=new ArrayList<>();				
				populateCustomerList(customerList,customerEntityList);
				return customerList;
			}
			throw new CustomerException("Customer List is empty");
		}catch(PersistenceException e) {
			throw new CustomerException(e.getMessage(),e);
	}

	}

	private void populateCustomerList(List<Customer> customerList, List<CustomerEntity> customerEntityList) {
		Iterator<CustomerEntity> iterator= customerEntityList.iterator();		
		while(iterator.hasNext()) {
			Customer customer=new Customer();
			populateCustomer(customer,iterator.next());
			customerList.add(customer);		
		}

	}

	private void populateCustomer(Customer customer, CustomerEntity next) {
		customer.setCustomerId(next.getCustomerId());
		customer.setCustomerName(next.getCustomerName());
		customer.setBirthdate(next.getBirthdate());
		customer.setAddress(next.getAddress());
		customer.setMobile(next.getMobile());
		customer.setEmail(next.getEmail());		
	}

	@Override
	public Customer getCustomerById(Integer ID) throws CustomerException {
		try {
			CustomerEntity customerEntity=customerDAO.getCustomerById(ID);
			if(customerEntity!=null) {
				Customer customer=new Customer();
				populateCustomer(customer,customerEntity);
				return customer;
			}
			throw new CustomerException(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.FAIL"));
		}catch(PersistenceException e) {
			throw new CustomerException(e.getMessage(),e);
	}
	}

	@Override
	public Integer deleteCustomer(Integer ID) throws CustomerException {
		try {	
				int n= customerDAO.deleteCustomer(ID);
				if(n>0) {
					return n;
				}
				throw new CustomerException(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.FAIL"));
		}catch(PersistenceException e) {
			throw new CustomerException(e.getMessage(),e);
		}
	}

	@Override
	public Integer updateCustomer(Customer customer) throws CustomerException {
		
		try {				
			CustomerEntity customerEntity=new CustomerEntity();
			populateCustomerEntity(customer,customerEntity);
			int n= customerDAO.updateCustomer(customerEntity);
			if(n>0) {
				return n;
			}
			throw new CustomerException(AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.FAIL"));
		}catch(PersistenceException e) {
			throw new CustomerException(e.getMessage(),e);
		}

		
	}



}
