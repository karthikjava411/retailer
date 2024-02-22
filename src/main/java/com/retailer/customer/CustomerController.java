package com.retailer.customer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.customer.persistence.entity.Customer;
import com.retailer.customer.service.CustomerService;
import com.retailer.exception.CustomerNotFoundException;
import com.retailer.util.UtilConstants;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("getAllCustomers")
	public  ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customerList = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
	}
	
	@GetMapping("customerById/{customerId}")
	public  ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
		try {
			Customer customer = customerService.getCustomerById(customerId);
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}catch(CustomerNotFoundException ex) {
			return new ResponseEntity<Customer>(new Customer(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("save")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
		customer.setCreatedDate(LocalDateTime.now());
		customer.setCreatedBy(UtilConstants.CURRENT_USER);
		customerService.saveCustomer(customer);
		return new ResponseEntity<String>(UtilConstants.SUCCESS_MESSAGE,HttpStatus.OK);
	}
}
