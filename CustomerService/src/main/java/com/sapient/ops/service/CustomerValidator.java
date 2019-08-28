package com.sapient.ops.service;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sapient.ops.model.Customer;
import com.sapient.ops.utility.AppConfig;

@Component
public class CustomerValidator {
	/**
	 * 
	 * @param customerName
	 * @return true if customer name is of only alphabets 
	 * and/or space(s) else returns false
	 */
	public Boolean isValidCustomerName(String customerName){
		String regex="^[a-zA-Z ]+$";
		if(customerName.matches(regex)){
			//System.out.println("Valid name");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * @param email
	 * @return true if email is a valid emailId else return false
	 */
	public Boolean isValidEmail(String email){
		String regex="^[a-zA-Z0-9._]+@{1}[a-z]{2,}.{1}[a-z]{2,3}[.]?[a-z]{2,3}$";
		if(email.matches(regex)){
			//System.out.println("Valid email");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * @param mobile
	 * @return false if all the digits are same(typically service provider's number)  else r				returns true
	 */
	public Boolean isValidMobile(Long mobile){
		String smobile=mobile.toString();
		Boolean flag=false;
		char firstDigit=smobile.charAt(0);
		for(int i=1;i<smobile.length();i++){
			if(firstDigit!=smobile.charAt(i)){
				//System.out.println("Valid mobile");
				flag=true;
				break;
			}
		}
		return flag;
	}


	/**
	 * 
	 * @param mobile
	 * @return false if birthdate is greater than current date else return true
	 */
	public Boolean isValidBirthdate(Date birthdate){
		if(birthdate.after(new Date())){
			//System.out.println("Valid birthdate");
			return false;
		}
		return true;


	}

	/**
	 * 
	 * @param customer
	 * @throws Exception
	 */
	public Boolean validate(Customer customer) {
		Logger myLogger=Logger.getLogger(CustomerValidator.class);
		String error_status=null;	

		if(!this.isValidCustomerName(customer.getCustomerName())){
			error_status="Validator.INVALID_USERNAME";
		}else if(!this.isValidEmail(customer.getEmail())){
			error_status="Validator.INVALID_EMAIL";
		}else if(!this.isValidMobile(customer.getMobile())){
			error_status="Validator.INVALID_MOBILE";
		}else if(!this.isValidBirthdate(customer.getBirthdate())){
			error_status="Validator.INVALID_BIRTHDATE";
		}
		System.out.println("Valid Customer object");
		if(error_status != null){
			String value=AppConfig.PROPERTIES.getProperty(error_status);
			System.out.println(value);
			myLogger.error(value);
			return false;
		}else {
			return true;
		}

	}
}
