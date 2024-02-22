package com.retailer.orderdetails.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.util.RewardPointsCalculator;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderDetailsTest {

	OrderDetails orderDetails;
	
	LocalDateTime currentDateTime = LocalDateTime.now();
			
	@BeforeEach
	public void setup() {
		orderDetails = new OrderDetails();
		orderDetails.setOrderId(1);
		orderDetails.setAmount(new BigDecimal(100));
		orderDetails.setRewardPoints(RewardPointsCalculator.calculateRewardPoints(orderDetails.getAmount()));
		orderDetails.setCustomerId(1);
		orderDetails.setOrderDate(LocalDate.now());
		orderDetails.setOrderedItemsList(genearateOrderedItemsList(1));
		orderDetails.setCreatedBy("Test");
		orderDetails.setCreatedDate(currentDateTime);
		orderDetails.setUpdatedBy("Test");
		orderDetails.setUpdatedDate(currentDateTime);
	}
	
	@Test
	@DisplayName("Test ItemDetails entity")
	public void testItemDetails() {
		assertEquals(orderDetails.getOrderId(), 1);
		assertEquals(orderDetails.getAmount(), new BigDecimal(100));
		assertEquals(orderDetails.getRewardPoints(), 50);
		assertEquals(orderDetails.getCustomerId(), 1);
		assertEquals(orderDetails.getOrderedItemsList().size(), 3);
		assertEquals(orderDetails.getOrderDate(), LocalDate.now());
		assertEquals(orderDetails.getCreatedBy(), "Test");
		assertEquals(orderDetails.getCreatedDate(), currentDateTime);
		assertEquals(orderDetails.getUpdatedBy(), "Test");
		assertEquals(orderDetails.getUpdatedDate(), currentDateTime);
	}
	
	private List<OrderedItems> genearateOrderedItemsList(int orderId) {
		List<OrderedItems> orderedItemsList = new ArrayList<>();
		orderedItemsList.add(genearateOrderedItems(1, 1, orderId, 1));
		orderedItemsList.add(genearateOrderedItems(2, 1, orderId, 1));
		orderedItemsList.add(genearateOrderedItems(3, 1, orderId, 1));
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
