package com.retailer.itemdetails.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemDetailsTest {

	ItemDetails itemDetails;
	
	LocalDateTime currentDateTime = LocalDateTime.now();
			
	@BeforeEach
	public void setup() {
		itemDetails = new ItemDetails();
		itemDetails.setItemId(1);
		itemDetails.setItemName("Bag");
		itemDetails.setItemPrice(new BigDecimal(50));
		itemDetails.setCreatedBy("Test");
		itemDetails.setCreatedDate(currentDateTime);
		itemDetails.setUpdatedBy("Test");
		itemDetails.setUpdatedDate(currentDateTime);
	}
	
	@Test
	@DisplayName("Test ItemDetails entity")
	public void testItemDetails() {
		assertEquals(itemDetails.getItemId(), 1);
		assertEquals(itemDetails.getItemName(), "Bag");
		assertEquals(itemDetails.getItemPrice(), new BigDecimal(50));
		assertEquals(itemDetails.getCreatedBy(), "Test");
		assertEquals(itemDetails.getCreatedDate(), currentDateTime);
		assertEquals(itemDetails.getUpdatedBy(), "Test");
		assertEquals(itemDetails.getUpdatedDate(), currentDateTime);
	}
}
