package com.retailer.orderdetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.retailer.orderdetails.model.RewardPoints;
import com.retailer.orderdetails.persistence.entity.OrderDetails;
import com.retailer.orderdetails.service.OrderDetailsService;
import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.util.RewardPointsCalculator;
import com.retailer.util.UtilConstants;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderDetailsControllerTest {

	@Mock 
	private OrderDetailsService orderDetailsService;
	
	@InjectMocks
	private OrderDetailsController orderDetailsController;
	
	@Test
	@DisplayName("Get all orderDetails")
	public void testGetAllOrderDetails() {
		when(orderDetailsService.getAllOrderDetails()).thenReturn(generateOrderDetailsList());
		ResponseEntity<List<OrderDetails>> response = orderDetailsController.getAllOrderDetails();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<OrderDetails> result = response.getBody();
		assertEquals(result.size(), 3);
		OrderDetails orderDetails = result.get(0);
		assertEquals(orderDetails.getOrderId(), 1);
		assertEquals(orderDetails.getCustomerId(), 1);
		assertEquals(orderDetails.getRewardPoints(), 54);
	}
	
	@Test
	@DisplayName("Get customer rewards by months")
	public void testGetCustomerRewardsByMonths() {
		Map<String, Integer> rewardsMap = new HashMap<>();
		rewardsMap.put("Feb-2024", 20);
		rewardsMap.put("Jan-2024", 25);
		rewardsMap.put("Dec-2023", 10);
		when(orderDetailsService.getCustomerRewardsByMonths(1)).thenReturn(rewardsMap);
		ResponseEntity<Map<String, Integer>> response = orderDetailsController.getCustomerRewardsByMonths(1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		Map<String, Integer> result = response.getBody();
		assertEquals(result.size(), 3);
		assertEquals(result.get("Feb-2024"), 20);
		assertEquals(result.get("Jan-2024"), 25);
		assertEquals(result.get("Dec-2023"), 10);
	}
	
	@Test
	@DisplayName("Get customer rewards in list view of rewardpoints object")
	public void testGetCustomerRewards() {
		when(orderDetailsService.getCustomerRewards(1)).thenReturn(generateRewardPointsList());
		ResponseEntity<List<RewardPoints>> response = orderDetailsController.getCustomerRewards(1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<RewardPoints> result = response.getBody();
		assertEquals(result.size(), 3);
	}
	
	@Test
	@DisplayName("Get customer transactions")
	public void testGetCustomerTransactionByPeriod() {
		when(orderDetailsService.getCustomerTransactionByPeriod(1,3)).thenReturn(generateOrderDetailsList());
		ResponseEntity<List<OrderDetails>> response = orderDetailsController.getCustomerTransactionByPeriod(1,3);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		List<OrderDetails> result = response.getBody();
		assertEquals(result.size(), 3);
		OrderDetails orderDetails = result.get(0);
		assertEquals(orderDetails.getOrderId(), 1);
		assertEquals(orderDetails.getCustomerId(), 1);
		assertEquals(orderDetails.getRewardPoints(), 54);
	}
	
	@Test
	@DisplayName("Test save - success")
	public void testSave() throws UserException{
		OrderDetails orderDetails = generateOrderDetails(1, 1, 50);
		when(orderDetailsService.saveOrderDetails(orderDetails)).thenReturn(orderDetails);
		ResponseEntity<String> response = orderDetailsController.saveOrderDetails(orderDetails);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), UtilConstants.SUCCESS_MESSAGE);
	}
	
	@Test
	@DisplayName("Test save - throw exception")
	public void testSaveWithException() throws UserException{
		OrderDetails orderDetails = generateOrderDetails(1, 1, 50);
		when(orderDetailsService.saveOrderDetails(orderDetails)).thenThrow(new IndexOutOfBoundsException("Error"));
		ResponseEntity<String> response = orderDetailsController.saveOrderDetails(orderDetails);
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(response.getBody(), "Error");
	}

	
	private List<OrderDetails> generateOrderDetailsList(){
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		orderDetailsList.add(generateOrderDetails(1, 1, 102));
		orderDetailsList.add(generateOrderDetails(2, 1, 10));
		orderDetailsList.add(generateOrderDetails(3, 1, 170));
		return orderDetailsList;
	}
	
	private OrderDetails generateOrderDetails( int orderId, int customerId, int amount){
		OrderDetails orderDetails = new OrderDetails();
		
		orderDetails.setOrderId(orderId);
		orderDetails.setAmount(new BigDecimal(amount));
		orderDetails.setRewardPoints(RewardPointsCalculator.calculateRewardPoints(orderDetails.getAmount()));
		orderDetails.setCustomerId(customerId);
		orderDetails.setOrderDate(LocalDate.now());
		orderDetails.setOrderedItemsList(genearateOrderedItemsList(orderId));
		orderDetails.setCreatedBy("Test");
		orderDetails.setCreatedDate(LocalDateTime.now());
		orderDetails.setUpdatedBy("Test");
		orderDetails.setUpdatedDate(LocalDateTime.now());
		return orderDetails;
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
	
	private List<RewardPoints> generateRewardPointsList(){
		List<RewardPoints> rewardPointsList = new ArrayList<>();
		rewardPointsList.add(generateRewardPoints("Feb","2024", 20));
		rewardPointsList.add(generateRewardPoints("Jan","2024", 25));
		rewardPointsList.add(generateRewardPoints("Dec","2023", 15));
		return rewardPointsList;
	}
	
	private RewardPoints generateRewardPoints( String month, String year, int rewards){
		RewardPoints rewardPoints = new RewardPoints();
		
		rewardPoints.setMonth(month);
		rewardPoints.setYear(year);;
		rewardPoints.setRewards(rewards);
		rewardPoints.setOrder(1);
		rewardPoints.setMonthAndYear(month+"-"+year);
		return rewardPoints;
	}
}
