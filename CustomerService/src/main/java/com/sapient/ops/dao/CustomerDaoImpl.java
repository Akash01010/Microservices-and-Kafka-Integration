package com.sapient.ops.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sapient.ops.entity.CustomerEntity;
import com.sapient.ops.utility.AppConfig;

@Repository
public class CustomerDaoImpl implements CustomerDAO{
	
	@PersistenceContext
	private EntityManager entityManager;	
	private Logger daoLogger=Logger.getLogger(CustomerDaoImpl.class);
	
	@Override
	public String addCustomer(CustomerEntity customer) throws PersistenceException {
		
		try {
			entityManager.persist(customer);
			daoLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.SUCCESS"));
			return AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.SUCCESS");
		}catch(PersistenceException e) {
			daoLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.FAIL"), e);
			throw e;
		}		
	}

	@Override
	public List<CustomerEntity> getAllCustomerDetails() throws PersistenceException {
		String sql="From CustomerEntity";
		//EntityTransaction transaction=entityManager.getTransaction();
		try {
			//transaction.begin();
			Query query=entityManager.createQuery(sql);
			List<CustomerEntity> customerList=query.getResultList();			
			//transaction.commit();
			daoLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_READALL.SUCCESS"));
			return customerList;
		}catch(PersistenceException e) {			
			daoLogger.error(e.getMessage(),e);		
			throw e;
		}catch(Exception e) {	
			daoLogger.error(e.getMessage(),e);		
			throw new PersistenceException(e);
		}
	}

	@Override
	public CustomerEntity getCustomerById(Integer ID) throws PersistenceException {
		
		try {
			 CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, ID);
			 daoLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.SUCCESS"));
			 return customerEntity;
		}catch(PersistenceException e) {			
			daoLogger.error(e.getMessage(),e);		
			throw e;
		}catch(Exception e) {	
			daoLogger.error(e.getMessage(),e);		
			throw new PersistenceException(e);
		}
	}

	@Override
	public Integer deleteCustomer(Integer ID) throws PersistenceException {
		
		try {
			CustomerEntity customer=entityManager.find(CustomerEntity.class, ID);
			entityManager.remove(customer);
			daoLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_DELETE.SUCCESS"));
			return 1;
		}catch(PersistenceException e) {			
			daoLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_DELETE.FAIL"),e);		
			throw e;
		}catch(Exception e) {	
			daoLogger.error(e.getMessage(),e);		
			throw new PersistenceException(e);
		}	
	}

	@Override
	public Integer updateCustomer(CustomerEntity customer) throws PersistenceException {
		
		try {
			entityManager.merge(customer);
			daoLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.SUCCESS"));
			return 1;
		}catch(PersistenceException e) {			
			daoLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.FAIL"),e);		
			throw e;
		}catch(Exception e) {	
			daoLogger.error(e.getMessage(),e);		
			throw new PersistenceException(e);
		}	
	}

}
