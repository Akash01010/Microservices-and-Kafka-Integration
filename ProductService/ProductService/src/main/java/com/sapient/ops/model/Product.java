package com.sapient.ops.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products_sapient") 
@JsonIgnoreProperties(value = {"manufactureDate"}, allowGetters = true) 
@EntityListeners(AuditingEntityListener.class) 
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id", nullable = false) 
	private Long productId; 
	
	@NotBlank 
	@Column(name = "product_name", nullable = false) 
	private String productName; 
	
	public Product() {
		super();
	}

	private String description ;
	
	@CreatedDate
	@Column(name = "manufacture_date", nullable = false) 
	private Date manufactureDate ;

	@Column(name = "buying_price", nullable = false) 
	private Double buyingPrice ;

	public Product(@NotBlank String productName, String description, Double buyingPrice) {
		super();
		this.productName = productName;
		this.description = description;
		this.buyingPrice = buyingPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}


	public Double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
//	description varchar, manufacture_date date, buying_price double 
	  
	
	
}
