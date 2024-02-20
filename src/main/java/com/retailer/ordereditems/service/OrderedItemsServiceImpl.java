package com.retailer.ordereditems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retailer.ordereditems.persistence.OrderedItemsRepository;
import com.retailer.ordereditems.persistence.entity.OrderedItems;

@Service
public class OrderedItemsServiceImpl implements OrderedItemsService{

	private OrderedItemsRepository orderedItemsRepository;
	
	OrderedItemsServiceImpl(OrderedItemsRepository orderedItemsRepository){
		this.orderedItemsRepository = orderedItemsRepository;
	}

	@Override
	public List<OrderedItems> saveAllOrderedItems(List<OrderedItems> orderedItemsList) {
		return orderedItemsRepository.saveAll(orderedItemsList);
	}
	
	public List<OrderedItems> getOrderedItemsByOrderId(int orderId) {
		return orderedItemsRepository.findByOrderId(orderId);
	}
	
}
