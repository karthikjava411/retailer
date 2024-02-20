package com.retailer.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.retailer.customer.persistence.CustomerRepository;
import com.retailer.customer.persistence.entity.Customer;
import com.retailer.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	
	CustomerServiceImpl(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Integer customerId) throws CustomerNotFoundException{
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return customer.get();
	}
	
	public Customer saveCustomer(Customer customer){
		return customerRepository.save(customer);
	}
	
}
