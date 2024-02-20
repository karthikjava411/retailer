package com.retailer.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.customer.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
}
