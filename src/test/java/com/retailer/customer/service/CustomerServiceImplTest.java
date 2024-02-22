package com.retailer.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retailer.customer.persistence.CustomerRepository;
import com.retailer.customer.persistence.entity.Customer;
import com.retailer.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceImplTest {

	@Mock 
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Test
	@DisplayName("Get All Customers Data")
	public void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(genearateCustomerList());
		List<Customer> result = customerService.getAllCustomers();
		assertEquals(result.size(), 3);
		Customer customer = result.get(0);
		assertEquals(customer.getCustomerId(), 1);
		assertEquals(customer.getCustomerName(), "John Wick");
	}
	
	@Test
	@DisplayName("Get customer by id - when customer exists by given id")
	public void testGetCustomerById() throws CustomerNotFoundException {
		when(customerRepository.findById(1)).thenReturn(Optional.of(genearateCustomer(1, "John Wick")));
		Customer customer = customerService.getCustomerById(1);
		assertEquals(customer.getCustomerId(), 1);
		assertEquals(customer.getCustomerName(), "John Wick");
	}
	
	@Test
	@DisplayName("Get customer by id - when customer doesn't exists by given id")
	public void testGetCustomerByIdExceptionCase() throws CustomerNotFoundException {
		when(customerRepository.findById(1)).thenReturn(Optional.of(genearateCustomer(1, "John Wick")));
		try {
			customerService.getCustomerById(1);
		}catch(CustomerNotFoundException ex) {
			assertEquals(ex.getMessage(), "Customer Not Found");
		}
		
	}
	
	@Test
	@DisplayName("Test save")
	public void testSave(){
		Customer customer = genearateCustomer(1, "John Wick");
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.saveCustomer(customer);
		assertEquals(result.getCustomerId(), 1);
		assertEquals(result.getCustomerName(), "John Wick");
	}
	
	private List<Customer> genearateCustomerList(){
		List<Customer> customerList = new ArrayList<>();
		customerList.add(genearateCustomer(1, "John Wick"));
		customerList.add(genearateCustomer(2, "Emma Waston"));
		customerList.add(genearateCustomer(3, "Micheal Hall"));
		return customerList;
	}
	
	private Customer genearateCustomer(int customerId, String customerName){
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		customer.setPhone(981234567l);
		customer.setEmail("test@email.com");
		customer.setAddress1("Test Address 1");
		customer.setAddress2("Test Address 2");
		customer.setCity("St Louis");
		customer.setState("MO");
		customer.setZipCode("61116");
		customer.setCreatedBy("Test");
		customer.setCreatedDate(LocalDateTime.now());
		customer.setUpdatedBy("Test");
		customer.setUpdatedDate(LocalDateTime.now());
		return customer;
	}
}
