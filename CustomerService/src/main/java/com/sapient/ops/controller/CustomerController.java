package com.sapient.ops.controller ;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sapient.ops.exception.CustomerException;
import com.sapient.ops.model.Customer;
import com.sapient.ops.service.CustomerService;
import com.sapient.ops.utility.AppConfig;

@RestController
@RequestMapping("/capi")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	private Logger clientLogger= Logger.getLogger(CustomerController.class);
		
//	
//	@RequestMapping(value="prereg" ,method=RequestMethod.GET)
//	public ModelAndView customerForm() {
//		Customer customer=new Customer();
//		return new ModelAndView("cust_reg","customer",customer);
//	}

//	
//	@RequestMapping(value = "/v1/processfiles/", method = RequestMethod.POST)
//	public ResponseEntity addProcessFiles(@RequestBody List<ProcessFiles> processFiles) {
//	try {
//	    processFilesDao.save(processFiles);
//	    return new ResponseEntity<>("Success", HttpStatus.OK);
//	} catch (Exception e) {
//	    return new ResponseEntity<>("Failure occured at server side", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	
	@PostMapping(value="/register")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
		//System.out.println(customer.getCustomerName());
		try {
			String status=customerService.addCustomer(customer);
			clientLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.SUCCESS"));
			return new ResponseEntity<>(status, HttpStatus.OK);
		} catch (CustomerException e) {	
			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.FAIL"), e);
			return new ResponseEntity<>(AppConfig.PROPERTIES.getProperty("CUSTOMER_INSERT.FAIL"), HttpStatus.NOT_FOUND);
		}		
	}
	
//	@RequestMapping(value="preget" ,method=RequestMethod.GET)
//	public String getCustomerIdForm() {		
//		return"get_customer_id";
//	}
//	
	@GetMapping("/byID/{cust_id}")
	public ResponseEntity getCustomerById(@PathVariable(value = "cust_id") String id) {
		try {
			Integer ID = Integer.parseInt(id) ;
			Customer customer = customerService.getCustomerById(ID) ;
			clientLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.SUCCESS"));
			return new ResponseEntity<Customer>(customer, HttpStatus.OK) ;
		} catch (CustomerException e) {
			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.FAIL"), e);
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.NOT_FOUND) ;
		}
	}
//	@RequestMapping(value = "/getc", method =RequestMethod.GET)
//	public ModelAndView getCustomerById(@RequestParam(value = "customerid", required = true,defaultValue ="1" ) String id) {
//		try {
//			Integer ID= Integer.parseInt(id);
//			Customer customer=customerService.getCustomerById(ID);
//			clientLogger.info(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.SUCCESS"));
//			return new ModelAndView("customer_details","customer",customer);
//		} catch (CustomerException e) {	
//			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_READ.FAIL"), e);
//			return new ModelAndView("status","message",e.getMessage());
//		}	
//	}
//	
	@GetMapping("/allc")
//	@RequestMapping(value="/allc", method=RequestMethod.GET)
	public ResponseEntity getAllCustomerDetails() {
		try {
			List<Customer> customerList=customerService.getAllCustomerDetails();
			if(customerList.size()!=0) {				
				return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK ) ;
			}else {
				throw new CustomerException("Customer List Empty") ;
			}
		}catch(CustomerException e) {
			e.printStackTrace();
			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_READALL.FAIL"), e);
			return new ResponseEntity(e.getMessage(), HttpStatus.OK ) ;
		}
	}
//	
//	@RequestMapping(value="/preeditc", method=RequestMethod.GET)
//	public ModelAndView preUpdateCustomer(@RequestParam(value="customerid") String customerid) {
//		try {
//			int id=Integer.parseInt(customerid);
//			Customer customer=customerService.getCustomerById(id);
//			return new ModelAndView("update_customer","customer",customer);
//		}catch(CustomerException e) {
//			return new ModelAndView("status","message",e.getMessage());
//		}catch(Exception e) {
//			return new ModelAndView("status","message",e.getMessage());
//		}
//	}
//	
//	@RequestMapping(value="/posteditc", method=RequestMethod.POST)
//	public ModelAndView postUpdateCustomer(@ModelAttribute(value="customer") Customer customer) {
//		try {
//			//System.out.println(customer.getCustomerName());
//			int n=customerService.updateCustomer(customer);
//			if(n>0) {
//				return new ModelAndView("cust_status","message",AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.SUCCESS"));
//			}else {
//				return new ModelAndView("cust_status","message",AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.FAIL"));
//			}
//		}catch(CustomerException e) {
//			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_UPDATE.FAIL"), e);
//			return new ModelAndView("status","message",e.getMessage());
//		}
//	}
//	
	@DeleteMapping("/deletec/{cust_id}")
	public ResponseEntity deleteCustomer(@PathVariable(value = "cust_id") String id){
		try {
			Integer ID = Integer.parseInt(id) ;
			Integer status = customerService.deleteCustomer(ID) ;
			return new ResponseEntity<>(status, HttpStatus.OK) ;
		}
		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.OK ) ;
		}
	}
//	public ModelAndView deleteCustomer(@RequestParam(value="customerid") String customerid) {
//		try {
//			int id=Integer.parseInt(customerid);
//			int n= customerService.deleteCustomer(id);
//			if(n>0) {
//				return new ModelAndView("cust_status","message",AppConfig.PROPERTIES.getProperty("CUSTOMER_DELETE.SUCCESS"));
//			}else {
//				return new ModelAndView("cust_status","message",AppConfig.PROPERTIES.getProperty("CUSTOMER_DELETE.FAIL"));
//			}
//		}catch(CustomerException e) {
//			clientLogger.error(AppConfig.PROPERTIES.getProperty("CUSTOMER_DELETE.FAIL"), e);
//			return new ModelAndView("status","message",e.getMessage());
//		}
//	}
//	
//	
//	@RequestMapping(value="/cmenu")
//	public String getCustomerMenu() {
//		return "customer_menu";
//	}
//	
//	@RequestMapping("/mmenu")
//	public String goToMainMenu() {
//		return "main_menu";
//	}
}