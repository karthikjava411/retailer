package com.retailer.customer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.customer.persistence.entity.Customer;
import com.retailer.customer.service.CustomerService;
import com.retailer.exception.CustomerNotFoundException;
import com.retailer.exception.UserException;
import com.retailer.util.UtilConstants;

@RestController
@RequestMapping("customer")
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("getAllCustomers")
	public  List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("customerById/{customerId}")
	public  Customer getCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping("save")
	public Customer saveItemDetails(@RequestBody Customer customer) throws UserException {
		customer.setCreatedDate(LocalDateTime.now());
		customer.setCreatedBy(UtilConstants.CURRENT_USER);
		return customerService.saveCustomer(customer);
	}
}
