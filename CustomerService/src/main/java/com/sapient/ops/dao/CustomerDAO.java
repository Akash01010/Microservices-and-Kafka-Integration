package com.sapient.ops.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import com.sapient.ops.entity.CustomerEntity;



public interface CustomerDAO {
	public abstract String addCustomer(CustomerEntity customer) throws PersistenceException;
	public abstract List<CustomerEntity> getAllCustomerDetails() throws PersistenceException;
	public abstract CustomerEntity getCustomerById(Integer ID) throws PersistenceException;
	public abstract Integer deleteCustomer(Integer ID) throws PersistenceException;
	public abstract Integer updateCustomer(CustomerEntity customer) throws PersistenceException;
}
