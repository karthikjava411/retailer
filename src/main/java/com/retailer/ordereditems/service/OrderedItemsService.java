package com.retailer.ordereditems.service;

import java.util.List;

import com.retailer.ordereditems.persistence.entity.OrderedItems;

public interface OrderedItemsService {

	List<OrderedItems> saveAllOrderedItems(List<OrderedItems> orderedItemsList);
	
	List<OrderedItems> getOrderedItemsByOrderId(int orderId);
}
