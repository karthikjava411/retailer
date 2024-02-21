package com.retailer.itemdetails;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.exception.UserException;
import com.retailer.itemdetails.persistence.entity.ItemDetails;
import com.retailer.itemdetails.service.ItemDetailsService;
import com.retailer.util.UtilConstants;



@RestController
@RequestMapping("itemdetails")
public class ItemDetailsController {
	
	private ItemDetailsService itemDetailsService;
	
	public ItemDetailsController(ItemDetailsService itemDetailsService) {
		this.itemDetailsService = itemDetailsService;
	}
	
	@GetMapping("getAllItemDetails")
	public  List<ItemDetails> getAllItemDetails() {
		return itemDetailsService.getAllItemDetails();
	}
	
	@PostMapping("save")
	public ItemDetails saveItemDetails(@RequestBody ItemDetails itemDetails) throws UserException {
		itemDetails.setCreatedDate(LocalDateTime.now());
		itemDetails.setCreatedBy(UtilConstants.CURRENT_USER);
		return itemDetailsService.saveItemDetails(itemDetails);
	}
	
	
}
