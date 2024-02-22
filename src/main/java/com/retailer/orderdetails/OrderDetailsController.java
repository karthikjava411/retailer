package com.retailer.orderdetails;

import java.util.List;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
public class OrderDetailsController {

	private OrderDetailsService orderDetailsService;
	
	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}
	
	@GetMapping("getAllOrderDetails")
	public  ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
		List<OrderDetails> orderDetailsList= orderDetailsService.getAllOrderDetails();
		return new ResponseEntity<List<OrderDetails>>(orderDetailsList,HttpStatus.OK);
	}
	
	@GetMapping("getCustomerRewardsByMonths")
	public  ResponseEntity<Map<String, Integer>> getCustomerRewardsByMonths(@RequestParam int customerId) {
		Map<String, Integer> rewardsMap = orderDetailsService.getCustomerRewardsByMonths(customerId);
		return new ResponseEntity<Map<String, Integer>>(rewardsMap,HttpStatus.OK);
	}
	
	@GetMapping("getCustomerRewards")
	public  ResponseEntity<List<RewardPoints>> getCustomerRewards(@RequestParam int customerId) {
		List<RewardPoints> rewardList= orderDetailsService.getCustomerRewards(customerId);
		return new ResponseEntity<List<RewardPoints>>(rewardList,HttpStatus.OK);
	}
	
	@GetMapping("getCustomerTransactionByPeriod")
	public  ResponseEntity<List<OrderDetails>> getCustomerTransactionByPeriod(@RequestParam int customerId, @RequestParam int noOfMonths) {
		List<OrderDetails> orderDetailsList= orderDetailsService.getCustomerTransactionByPeriod(customerId, noOfMonths);
		return new ResponseEntity<List<OrderDetails>>(orderDetailsList,HttpStatus.OK);
	}
	
	@PostMapping("save")
	public ResponseEntity<String> saveOrderDetails(@RequestBody OrderDetails orderDetails) throws UserException {
		try {
			orderDetailsService.saveOrderDetails(orderDetails);
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
