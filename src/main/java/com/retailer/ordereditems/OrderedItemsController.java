package com.retailer.ordereditems;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.ordereditems.service.OrderedItemsService;

@RestController
@RequestMapping("ordereditems")
public class OrderedItemsController {
	
	private OrderedItemsService orderedItemsService;
	
	public OrderedItemsController(OrderedItemsService orderedItemsService) {
		this.orderedItemsService = orderedItemsService;
	}

}
