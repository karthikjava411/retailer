package com.retailer.orderdetails;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.exception.UserException;
import com.retailer.orderdetails.model.RewardPoints;
import com.retailer.orderdetails.persistence.entity.OrderDetails;
import com.retailer.orderdetails.service.OrderDetailsService;

@RestController
@RequestMapping("orderdetails")
public class OrderDetailsController {

	private OrderDetailsService orderDetailsService;
	
	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}
	
	@GetMapping("getAllOrderDetails")
	public  List<OrderDetails> getAllOrderDetails() {
		return orderDetailsService.getAllOrderDetails();
	}
	
	@GetMapping("getCustomerRewardsByMonths")
	public  Map<String, Integer> getCustomerRewardsByMonths(@RequestParam int customerId) {
		return orderDetailsService.getCustomerRewardsByMonths(customerId);
	}
	
	@GetMapping("getCustomerRewards")
	public  List<RewardPoints> getCustomerRewards(@RequestParam int customerId) {
		return orderDetailsService.getCustomerRewards(customerId);
	}
	
	@GetMapping("getCustomerTransactionByPeriod")
	public  List<OrderDetails> getCustomerTransactionByPeriod(@RequestParam int customerId, @RequestParam int noOfMonths) {
		return orderDetailsService.getCustomerTransactionByPeriod(customerId, noOfMonths);
	}
	
	@PostMapping("save")
	public OrderDetails saveOrderDetails(@RequestBody OrderDetails orderDetails) throws UserException {
		return orderDetailsService.saveOrderDetails(orderDetails);
	}
}
