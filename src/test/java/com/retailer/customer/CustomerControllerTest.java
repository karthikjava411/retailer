package com.retailer.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.retailer.customer.persistence.entity.Customer;
import com.retailer.customer.service.CustomerService;
import com.retailer.exception.CustomerNotFoundException;
import com.retailer.util.UtilConstants;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {

	@Mock 
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	@Test
	@DisplayName("Get All Customers Data")
	public void testGetAllCustomers() {
		when(customerService.getAllCustomers()).thenReturn(genearateCustomerList());
		ResponseEntity<List<Customer>> response = customerController.getAllCustomers();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<Customer> result = response.getBody();
		assertEquals(result.size(), 3);
		Customer customer = result.get(0);
		assertEquals(customer.getCustomerId(), 1);
		assertEquals(customer.getCustomerName(), "John Wick");
	}
	
	@Test
	@DisplayName("Get customer by id - when customer exists by given id")
	public void testGetCustomerById() throws CustomerNotFoundException {
		when(customerService.getCustomerById(1)).thenReturn(genearateCustomer(1, "John Wick"));
		ResponseEntity<Customer> response = customerController.getCustomerById(1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		Customer customer = response.getBody();
		assertEquals(customer.getCustomerId(), 1);
		assertEquals(customer.getCustomerName(), "John Wick");
	}
	
	@Test
	@DisplayName("Get customer by id - when customer doesn't exists by given id")
	public void testGetCustomerByIdExceptionCase() throws CustomerNotFoundException {
		when(customerService.getCustomerById(1)).thenThrow(new CustomerNotFoundException("Customer not found"));
		ResponseEntity<Customer> response = customerController.getCustomerById(1);
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		Customer customer = response.getBody();
		assertNull(customer.getCustomerName());
	}
	
	@Test
	@DisplayName("Test save")
	public void testSave(){
		Customer customer = genearateCustomer(1, "John Wick");
		when(customerService.saveCustomer(customer)).thenReturn(customer);
		ResponseEntity<String> response = customerController.saveCustomer(customer);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), UtilConstants.SUCCESS_MESSAGE);
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
