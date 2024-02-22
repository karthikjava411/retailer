package com.retailer.itemdetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.retailer.exception.UserException;
import com.retailer.itemdetails.persistence.entity.ItemDetails;
import com.retailer.itemdetails.service.ItemDetailsService;
import com.retailer.util.UtilConstants;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemDetailsControllerTest {

	@Mock 
	private ItemDetailsService itemDetailsService;
	
	@InjectMocks
	private ItemDetailsController itemDetailsController;
	
	@Test
	@DisplayName("Get All ItemDetails Data")
	public void testGetAllItemDetails() {
		when(itemDetailsService.getAllItemDetails()).thenReturn(genearateItemDetailsList());
		ResponseEntity<List<ItemDetails>> response = itemDetailsController.getAllItemDetails();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<ItemDetails> result = response.getBody();
		assertEquals(result.size(), 3);
		ItemDetails itemDetails = result.get(0);
		assertEquals(itemDetails.getItemId(), 1);
		assertEquals(itemDetails.getItemName(), "Bag");
		assertEquals(itemDetails.getItemPrice(), new BigDecimal(50));
	}
	
	@Test
	@DisplayName("Test save - success")
	public void testSave() throws UserException{
		ItemDetails itemDetails = genearateItemDetails(1, "Bag", new BigDecimal(50));
		when(itemDetailsService.saveItemDetails(itemDetails)).thenReturn(itemDetails);
		ResponseEntity<String> response = itemDetailsController.saveItemDetails(itemDetails);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), UtilConstants.SUCCESS_MESSAGE);
	}
	
	@Test
	@DisplayName("Test save - when user trying to save with existing item name throw exception")
	public void testSaveWithException() throws UserException{
		ItemDetails itemDetails = genearateItemDetails(1, "Bag", new BigDecimal(50));
		when(itemDetailsService.saveItemDetails(itemDetails)).thenThrow(new UserException("Please enter unique item name"));
		ResponseEntity<String> response = itemDetailsController.saveItemDetails(itemDetails);
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(response.getBody(), "Please enter unique item name");
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
