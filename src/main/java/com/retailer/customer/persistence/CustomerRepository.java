package com.retailer.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.customer.persistence.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
