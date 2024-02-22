package com.retailer.ordereditems;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.ordereditems.service.OrderedItemsService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedItemsControllerTest {

	@Mock 
	private OrderedItemsService orderedItemsService;
	
	@InjectMocks
	private OrderedItemsController orderedItemsController;
	
	@Test
	@DisplayName("Get OrderedItems by orderId")
	public void testGetOrderedItemsByOrderId() {
		when(orderedItemsService.getOrderedItemsByOrderId(1)).thenReturn(genearateOrderedItemsList());
		ResponseEntity<List<OrderedItems>> response = orderedItemsController.getOrderedItemsByOrderId(1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<OrderedItems> result = response.getBody();
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
