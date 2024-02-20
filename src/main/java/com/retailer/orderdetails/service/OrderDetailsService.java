package com.retailer.orderdetails.service;

import java.util.List;
import java.util.Map;

import com.retailer.exception.ItemDetailsNotFoundException;
import com.retailer.orderdetails.persistence.entity.OrderDetails;

public interface OrderDetailsService {

	List<OrderDetails> getAllOrderDetails();
	OrderDetails getOrderDetailsById(Integer orderDetailsId) throws ItemDetailsNotFoundException;
	OrderDetails saveOrderDetails(OrderDetails orderDetails);
	List<OrderDetails> getCustomerTransactionByPeriod(int customerId, int noOfMonths);
	Map<String, Integer> getCustomerRewardsByMonths(int customerId);
}
