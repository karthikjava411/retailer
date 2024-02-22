package com.retailer.customer.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerTest {
	
	Customer customer;
	
	LocalDateTime currentDateTime = LocalDateTime.now();
			
	@BeforeEach
	public void setup() {
		customer = new Customer();
		customer.setCustomerId(1);
		customer.setCustomerName("Customer Name");
		customer.setPhone(981234567l);
		customer.setEmail("test@email.com");
		customer.setAddress1("Test Address 1");
		customer.setAddress2("Test Address 2");
		customer.setCity("St Louis");
		customer.setState("MO");
		customer.setZipCode("61116");
		customer.setCreatedBy("Test");
		customer.setCreatedDate(currentDateTime);
		customer.setUpdatedBy("Test");
		customer.setUpdatedDate(currentDateTime);
	}
	
	@Test
	@DisplayName("Test Customer entity")
	public void testCustomer() {
		assertEquals(customer.getCustomerId(), 1);
		assertEquals(customer.getCustomerName(), "Customer Name");
		assertEquals(customer.getPhone(), 981234567l);
		assertEquals(customer.getEmail(), "test@email.com");
		assertEquals(customer.getAddress1(), "Test Address 1");
		assertEquals(customer.getAddress2(), "Test Address 2");
		assertEquals(customer.getCity(), "St Louis");
		assertEquals(customer.getState(), "MO");
		assertEquals(customer.getZipCode(), "61116");
		assertEquals(customer.getCreatedBy(), "Test");
		assertEquals(customer.getCreatedDate(), currentDateTime);
		assertEquals(customer.getUpdatedBy(), "Test");
		assertEquals(customer.getUpdatedDate(), currentDateTime);
	}

}
