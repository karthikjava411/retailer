package com.retailer.ordereditems.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedItemsTest {

	OrderedItems orderedItems;
	
	LocalDateTime currentDateTime = LocalDateTime.now();
			
	@BeforeEach
	public void setup() {
		orderedItems = new OrderedItems();
		orderedItems.setOrderedItemsId(1);
		orderedItems.setItemId(1);
		orderedItems.setOrderId(1);
		orderedItems.setQuantity(1);
		orderedItems.setCreatedBy("Test");
		orderedItems.setCreatedDate(currentDateTime);
		orderedItems.setUpdatedBy("Test");
		orderedItems.setUpdatedDate(currentDateTime);
	}
	
	@Test
	@DisplayName("Test ItemDetails entity")
	public void testItemDetails() {
		assertEquals(orderedItems.getOrderedItemsId(), 1);
		assertEquals(orderedItems.getOrderId(), 1);
		assertEquals(orderedItems.getItemId(), 1);
		assertEquals(orderedItems.getQuantity(), 1);
		assertEquals(orderedItems.getCreatedBy(), "Test");
		assertEquals(orderedItems.getCreatedDate(), currentDateTime);
		assertEquals(orderedItems.getUpdatedBy(), "Test");
		assertEquals(orderedItems.getUpdatedDate(), currentDateTime);
	}
}
