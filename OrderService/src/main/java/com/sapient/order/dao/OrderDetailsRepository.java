package com.sapient.order.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.order.model.OrderDetails;

@Repository
@Transactional
public interface OrderDetailsRepository  extends 
JpaRepository<OrderDetails, Long>{
}
