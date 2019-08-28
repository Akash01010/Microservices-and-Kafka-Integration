package com.sapient.ops.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;


@Component
public class Customer {
	private Integer customerId;
	private String customerName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date birthdate;
	private Long mobile;
	private String address;
	private String email;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	
	/*public String getCustomizedBirthdate() {
		SimpleDateFormat dateFormat=
						new SimpleDateFormat("dd-MM-yyyy");
		String sdate=dateFormat.format(this.birthdate);
		return sdate;
	}*/
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", birthdate=" + birthdate
				+ ", mobile=" + mobile + ", address=" + address + ", email=" + email + "]";
	}
	
	
	
	
}
