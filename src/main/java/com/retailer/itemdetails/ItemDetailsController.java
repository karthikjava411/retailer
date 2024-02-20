package com.retailer.itemdetails;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.itemdetails.service.ItemDetailsService;

@RestController
@RequestMapping("itemdetails")
public class ItemDetailsController {
	
	private ItemDetailsService itemDetailsService;
	
	public ItemDetailsController(ItemDetailsService itemDetailsService) {
		this.itemDetailsService = itemDetailsService;
	}
}
