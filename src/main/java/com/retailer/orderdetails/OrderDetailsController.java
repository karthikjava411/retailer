package com.retailer.orderdetails;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.orderdetails.service.OrderDetailsService;

@RestController
@RequestMapping("orderdetails")
public class OrderDetailsController {

	private OrderDetailsService orderDetailsService;
	
	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}
}
