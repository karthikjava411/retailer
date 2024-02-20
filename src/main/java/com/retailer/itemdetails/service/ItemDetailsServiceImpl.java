package com.retailer.itemdetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.retailer.exception.ItemDetailsNotFoundException;
import com.retailer.itemdetails.persistence.ItemDetailsRepository;
import com.retailer.itemdetails.persistence.entity.ItemDetails;


@Service
public class ItemDetailsServiceImpl implements ItemDetailsService{

	private ItemDetailsRepository itemDetailsRepository;
	
	ItemDetailsServiceImpl(ItemDetailsRepository itemDetailsRepository){
		this.itemDetailsRepository = itemDetailsRepository;
	}

	@Override
	public List<ItemDetails> getAllItemDetails() {
		return itemDetailsRepository.findAll();
	}

	@Override
	public ItemDetails getItemDetailsById(Integer itemId) throws ItemDetailsNotFoundException {
		Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(itemId);
		if(itemDetails.isEmpty()) {
			throw new ItemDetailsNotFoundException("ItemDetails Not Found");
		}
		return itemDetails.get();
	}

	@Override
	public ItemDetails saveItemDetails(ItemDetails itemDetails) {
		return itemDetailsRepository.save(itemDetails);
	}
	
}
