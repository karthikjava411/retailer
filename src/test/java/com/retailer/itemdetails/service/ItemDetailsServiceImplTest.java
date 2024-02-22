package com.retailer.itemdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retailer.exception.UserException;
import com.retailer.itemdetails.persistence.ItemDetailsRepository;
import com.retailer.itemdetails.persistence.entity.ItemDetails;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemDetailsServiceImplTest {

	@Mock
	private ItemDetailsRepository itemDetailsRepository;
	
	@InjectMocks 
	private ItemDetailsServiceImpl itemDetailsService;
	
	@Test
	@DisplayName("Get All ItemDetails Data")
	public void testGetAllItemDetails() {
		when(itemDetailsRepository.findAll()).thenReturn(genearateItemDetailsList());
		List<ItemDetails> result = itemDetailsService.getAllItemDetails();
		assertEquals(result.size(), 3);
		ItemDetails itemDetails = result.get(0);
		assertEquals(itemDetails.getItemId(), 1);
		assertEquals(itemDetails.getItemName(), "Bag");
		assertEquals(itemDetails.getItemPrice(), new BigDecimal(50));
	}
	
	@Test
	@DisplayName("Test save")
	public void testSave() throws UserException{
		ItemDetails itemDetails = genearateItemDetails(1, "Bag", new BigDecimal(50));
		when(itemDetailsRepository.getItemDetailsByItemName(itemDetails.getItemName())).thenReturn(Optional.empty());
		when(itemDetailsRepository.save(itemDetails)).thenReturn(itemDetails);
		ItemDetails result = itemDetailsService.saveItemDetails(itemDetails);
		assertEquals(result.getItemId(), 1);
		assertEquals(result.getItemName(), "Bag");
		assertEquals(result.getItemPrice(), new BigDecimal(50));
	}
	
	@Test
	@DisplayName("Test save - when user trying to save with existing item name throw exception")
	public void testSaveWithException() throws UserException{
		ItemDetails itemDetails = genearateItemDetails(1, "Bag", new BigDecimal(50));
		when(itemDetailsRepository.getItemDetailsByItemName(itemDetails.getItemName())).thenReturn(Optional.of(itemDetails));
		try {
			itemDetailsService.saveItemDetails(itemDetails);
		}catch(UserException ex) {
			assertEquals(ex.getMessage(), "Item Name already exists");
		}
	}
	
	private List<ItemDetails> genearateItemDetailsList() {
		List<ItemDetails> itemDetailsList = new ArrayList<>();
		itemDetailsList.add(genearateItemDetails(1, "Bag", new BigDecimal(50)));
		itemDetailsList.add(genearateItemDetails(2, "Pen", new BigDecimal(5)));
		itemDetailsList.add(genearateItemDetails(3, "Bottle", new BigDecimal(15)));
		return itemDetailsList;
	}
	
	private ItemDetails genearateItemDetails(int itemId, String itemName, BigDecimal price){
		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setItemId(itemId);
		itemDetails.setItemName(itemName);
		itemDetails.setItemPrice(price);
		itemDetails.setCreatedBy("Test");
		itemDetails.setCreatedDate(LocalDateTime.now());
		itemDetails.setUpdatedBy("Test");
		itemDetails.setUpdatedDate(LocalDateTime.now());
		return itemDetails;
	}
}
