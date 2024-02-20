package com.retailer.orderdetails.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.retailer.exception.CustomerNotFoundException;
import com.retailer.exception.ItemDetailsNotFoundException;
import com.retailer.itemdetails.persistence.entity.ItemDetails;
import com.retailer.orderdetails.persistence.OrderDetailsRepository;
import com.retailer.orderdetails.persistence.entity.OrderDetails;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	private OrderDetailsRepository orderDetailsRepository;
	
	OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository){
		this.orderDetailsRepository = orderDetailsRepository;
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public OrderDetails getOrderDetailsById(Integer orderDetailsId) throws ItemDetailsNotFoundException {
		Optional<OrderDetails> itemDetails = orderDetailsRepository.findById(orderDetailsId);
		if(itemDetails.isEmpty()) {
			throw new ItemDetailsNotFoundException("ItemDetails Not Found");
		}
		return itemDetails.get();
	}

	@Override
	public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
		return null;
	}

	@Override
	public List<OrderDetails> getCustomerTransactionByPeriod(int customerId, int noOfMonths) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getCustomerRewardsByMonths(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
}
