package com.retailer.ordereditems.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retailer.ordereditems.persistence.OrderedItemsRepository;
import com.retailer.ordereditems.persistence.entity.OrderedItems;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedItemsServiceImplTest {

	@InjectMocks
	private OrderedItemsServiceImpl orderedItemsService;
	
	@Mock 
	private OrderedItemsRepository orderedItemsRepository;
	
	@Test
	@DisplayName("Get OrderedItems by orderId")
	public void testGetOrderedItemsByOrderId() {
		when(orderedItemsRepository.findByOrderId(1)).thenReturn(genearateOrderedItemsList());
		List<OrderedItems> result = orderedItemsService.getOrderedItemsByOrderId(1);
		assertEquals(result.size(), 3);
		OrderedItems orderedItems = result.get(0);
		assertEquals(orderedItems.getOrderedItemsId(), 1);
		assertEquals(orderedItems.getOrderId(), 1);
		assertEquals(orderedItems.getItemId(), 1);
	}
	
	@Test
	@DisplayName("Save all orderedItems")
	public void testSaveAllOrderedItems() {
		List<OrderedItems> request = genearateOrderedItemsList();
		when(orderedItemsRepository.saveAll(request)).thenReturn(request);
		List<OrderedItems> result = orderedItemsService.saveAllOrderedItems(request);
		assertEquals(result.size(), 3);
		OrderedItems orderedItems = result.get(0);
		assertEquals(orderedItems.getOrderedItemsId(), 1);
		assertEquals(orderedItems.getOrderId(), 1);
		assertEquals(orderedItems.getItemId(), 1);
	}
	
	private List<OrderedItems> genearateOrderedItemsList() {
		List<OrderedItems> orderedItemsList = new ArrayList<>();
		orderedItemsList.add(genearateOrderedItems(1, 1, 1, 1));
		orderedItemsList.add(genearateOrderedItems(2, 1, 1, 1));
		orderedItemsList.add(genearateOrderedItems(3, 1, 1, 1));
		return orderedItemsList;
	}
	
	private OrderedItems genearateOrderedItems(int orderedItemsId, int itemId, int orderId, int quantity){
		OrderedItems orderedItems = new OrderedItems();
		orderedItems.setOrderedItemsId(orderedItemsId);
		orderedItems.setItemId(itemId);
		orderedItems.setOrderId(orderId);
		orderedItems.setQuantity(quantity);
		orderedItems.setCreatedBy("Test");
		orderedItems.setCreatedDate(LocalDateTime.now());
		orderedItems.setUpdatedBy("Test");
		orderedItems.setUpdatedDate(LocalDateTime.now());
		return orderedItems;
	}
	
}
