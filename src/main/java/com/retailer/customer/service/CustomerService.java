package com.retailer.customer.service;

import java.util.List;

import com.retailer.customer.persistence.entity.Customer;
import com.retailer.exception.CustomerNotFoundException;

public interface CustomerService {
	List<Customer> getAllCustomers();
	Customer getCustomerById(Integer customerId) throws CustomerNotFoundException;
	Customer saveCustomer(Customer customer);
}
