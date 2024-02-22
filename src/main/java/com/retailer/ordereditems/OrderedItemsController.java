package com.retailer.ordereditems;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.ordereditems.service.OrderedItemsService;

@RestController
@RequestMapping("ordereditems")
@CrossOrigin(origins = "*")
public class OrderedItemsController {
	
	private OrderedItemsService orderedItemsService;
	
	public OrderedItemsController(OrderedItemsService orderedItemsService) {
		this.orderedItemsService = orderedItemsService;
	}
	
	@GetMapping("getOrderedItemsByOrderId/{orderId}")
	public  ResponseEntity<List<OrderedItems>> getOrderedItemsByOrderId(@PathVariable int orderId) {
		List<OrderedItems> orderedItemsList =  orderedItemsService.getOrderedItemsByOrderId(orderId);
		return new ResponseEntity<List<OrderedItems>>(orderedItemsList,HttpStatus.OK);
	}

}
