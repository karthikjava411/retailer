package com.retailer.orderdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retailer.exception.UserException;
import com.retailer.orderdetails.model.RewardPoints;
import com.retailer.orderdetails.persistence.OrderDetailsRepository;
import com.retailer.orderdetails.persistence.entity.OrderDetails;
import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.ordereditems.service.OrderedItemsService;
import com.retailer.util.DateFormatterUtil;
import com.retailer.util.RewardPointsCalculator;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderDetailsServiceImplTest {
	
	@InjectMocks
	private OrderDetailsServiceImpl orderDetailsService;
	
	@Mock
	private OrderDetailsRepository orderDetailsRepository;
	
	@Mock
	private OrderedItemsService orderedItemsService;
	
	@Test
	@DisplayName("Get all orderDetails")
	public void testGetAllOrderDetails() {
		when(orderDetailsRepository.findAll()).thenReturn(generateOrderDetailsList());
		List<OrderDetails> result = orderDetailsService.getAllOrderDetails();
		assertEquals(result.size(), 3);
		OrderDetails orderDetails = result.get(0);
		assertEquals(orderDetails.getOrderId(), 1);
		assertEquals(orderDetails.getCustomerId(), 1);
		assertEquals(orderDetails.getRewardPoints(), 54);
	}
	
	@Test
	@DisplayName("Get customer rewards by months")
	public void testGetCustomerRewardsByMonths() {
		
		when(orderDetailsRepository.findByCustomerId(1)).thenReturn(generateOrderDetailsList());
		Map<String, Integer> result = orderDetailsService.getCustomerRewardsByMonths(1);
		
		assertEquals(result.size(), 2);
		assertEquals(result.get(DateFormatterUtil.getMonthAndYearOfLocalDate(LocalDate.now())), 244);
		assertEquals(result.get("Total"), 244);
	}
	
	@Test
	@DisplayName("Get customer rewards in list view of rewardpoints object")
	public void testGetCustomerRewards() {
		when(orderDetailsRepository.findByCustomerId(1)).thenReturn(generateOrderDetailsList());
		List<RewardPoints> result = orderDetailsService.getCustomerRewards(1);
		assertEquals(result.size(), 2);
	}
	
	@Test
	@DisplayName("Get customer transactions")
	public void testGetCustomerTransactionByPeriod() {
		when(orderDetailsRepository.getCustomerTransactionByPeriod(1,-3,DateFormatterUtil.getCurrentDateAsString())).thenReturn(generateOrderDetailsList());
		List<OrderDetails> result = orderDetailsService.getCustomerTransactionByPeriod(1,3);
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
		when(orderDetailsRepository.save(orderDetails)).thenReturn(orderDetails);
		OrderDetails response = orderDetailsService.saveOrderDetails(orderDetails);
		assertNotNull(response);
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
	
}
