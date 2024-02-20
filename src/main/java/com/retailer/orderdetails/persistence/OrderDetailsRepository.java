package com.retailer.orderdetails.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.orderdetails.persistence.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

}
