package com.retailer.itemdetails.service;

import java.util.List;

import com.retailer.exception.ItemDetailsNotFoundException;
import com.retailer.exception.UserException;
import com.retailer.itemdetails.persistence.entity.ItemDetails;

public interface ItemDetailsService {
	List<ItemDetails> getAllItemDetails();
	ItemDetails getItemDetailsById(Integer itemId) throws ItemDetailsNotFoundException;
	
	ItemDetails saveItemDetails(ItemDetails itemDetails) throws UserException;
}
