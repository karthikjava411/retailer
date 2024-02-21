package com.retailer.orderdetails.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retailer.exception.ItemDetailsNotFoundException;
import com.retailer.orderdetails.model.RewardPoints;
import com.retailer.orderdetails.persistence.OrderDetailsRepository;
import com.retailer.orderdetails.persistence.entity.OrderDetails;
import com.retailer.ordereditems.persistence.entity.OrderedItems;
import com.retailer.ordereditems.service.OrderedItemsService;
import com.retailer.util.DateFormatterUtil;
import com.retailer.util.RewardPointsCalculator;
import com.retailer.util.UtilConstants;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	private OrderDetailsRepository orderDetailsRepository;
	
	private OrderedItemsService orderedItemsService;
	
	OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, OrderedItemsService orderedItemsService){
		this.orderDetailsRepository = orderDetailsRepository;
		this.orderedItemsService = orderedItemsService;
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public OrderDetails getOrderDetailsById(Integer orderDetailsId) throws ItemDetailsNotFoundException {
		Optional<OrderDetails> itemDetails = orderDetailsRepository.findById(orderDetailsId);
		if(itemDetails.isEmpty()) {
			throw new ItemDetailsNotFoundException("ItemDetails Not Found");
		}
		return itemDetails.get();
	}

	@Override
	public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
		orderDetails.setOrderDate(LocalDate.now());
		orderDetails.setRewardPoints(RewardPointsCalculator.calculateRewardPoints(orderDetails.getAmount()));
		orderDetails.setCreatedDate(LocalDateTime.now());
		orderDetails.setCreatedBy(UtilConstants.CURRENT_USER);
		OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
		for(OrderedItems  orderedItems : orderDetails.getOrderedItemsList()) {
			orderedItems.setOrderId(savedOrderDetails.getOrderId());
			orderedItems.setCreatedDate(LocalDateTime.now());
			orderedItems.setCreatedBy(UtilConstants.CURRENT_USER);
		}
		List<OrderedItems>  orderedItemsList = orderedItemsService.saveAllOrderedItems(orderDetails.getOrderedItemsList());
		savedOrderDetails.setOrderedItemsList(orderedItemsList);
		return savedOrderDetails;
	}

	@Override
	public List<OrderDetails> getCustomerTransactionByPeriod(int customerId, int noOfMonths) {
		return orderDetailsRepository.getCustomerTransactionByPeriod(customerId, -noOfMonths, DateFormatterUtil.getCurrentDateAsString());
	}

	@Override
	public Map<String, Integer> getCustomerRewardsByMonths(int customerId) {
		//Todo
//		Map<String, Integer> map = orderDetailsRepository.findByCustomerId(customerId).collect(Collectors.toMap(OrderDetails::getOrderDate, Function.identity(), 
//                (orderDetails1, orderDetails2) -> {
//                	orderDetails1.setRewardPoints(orderDetails1.getRewardPoints() + orderDetails2.getRewardPoints());
//                    return orderDetails1;
//                  }));
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByCustomerId(customerId);
		Map<String, Integer> mapRewards = new HashMap<>();
		mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, 0);
		for(OrderDetails orderDetails : orderDetailsList) {
			Integer previousRewards = mapRewards.get(DateFormatterUtil.getMonthAndYearOfLocalDate(orderDetails.getOrderDate()));
			Integer currentRewards = orderDetails.getRewardPoints();
			if(previousRewards == null) {
				mapRewards.put(DateFormatterUtil.getMonthAndYearOfLocalDate(orderDetails.getOrderDate()), currentRewards);
				mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, mapRewards.get(UtilConstants.TOTAL_MONTH_STRING)+currentRewards);
			}else {
				mapRewards.put(DateFormatterUtil.getMonthAndYearOfLocalDate(orderDetails.getOrderDate()), previousRewards + currentRewards);
				mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, mapRewards.get(UtilConstants.TOTAL_MONTH_STRING) + previousRewards + currentRewards);
			}
		}
		return mapRewards;
	}
	
	@Override
	public List<RewardPoints> getCustomerRewards(int customerId) {
		//Todo
//		Map<String, Integer> map = orderDetailsRepository.findByCustomerId(customerId).collect(Collectors.toMap(OrderDetails::getOrderDate, Function.identity(), 
//                (orderDetails1, orderDetails2) -> {
//                	orderDetails1.setRewardPoints(orderDetails1.getRewardPoints() + orderDetails2.getRewardPoints());
//                    return orderDetails1;
//                  }));
		//List<RewardPoints> rewardPointsList = new ArrayList<>();
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByCustomerId(customerId);
		Map<String, RewardPoints> mapRewards = new HashMap<>();
		//mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, 0);
		int order = 1;
		int totalRewards = 0;
		for(OrderDetails orderDetails : orderDetailsList) {
			String currentMonthAndYear = DateFormatterUtil.getMonthAndYearOfLocalDate(orderDetails.getOrderDate());
			RewardPoints previousRewards = mapRewards.get(currentMonthAndYear);
			Integer currentRewards = orderDetails.getRewardPoints();
			if(previousRewards == null) {
				
				RewardPoints rewardPoints = new RewardPoints();
				rewardPoints.setRewards(currentRewards);
				rewardPoints.setOrder(order);
				rewardPoints.setMonthAndYear(currentMonthAndYear);
				rewardPoints.setMonth(currentMonthAndYear.split("-")[0]);
				rewardPoints.setYear(currentMonthAndYear.split("-")[1]);
				mapRewards.put(currentMonthAndYear, rewardPoints);
				order++;
				
				//mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, mapRewards.get(UtilConstants.TOTAL_MONTH_STRING)+currentRewards);
			}else {
				previousRewards.setRewards(previousRewards.getRewards()+currentRewards);
				mapRewards.put(currentMonthAndYear, previousRewards);
				//mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, mapRewards.get(UtilConstants.TOTAL_MONTH_STRING) + previousRewards + currentRewards);
			}
			totalRewards = totalRewards + currentRewards;
			
		}
		mapRewards.put(UtilConstants.TOTAL_MONTH_STRING, createTotalRewards(totalRewards, order ));
		return mapRewards.values().stream().sorted((mr1,mr2)-> mr1.getOrder().compareTo(mr2.getOrder())).collect(Collectors.toList());
	}

	private RewardPoints createTotalRewards(int totalRewards, int order) {
		RewardPoints rewardPoints = new RewardPoints();
		rewardPoints.setRewards(totalRewards);
		rewardPoints.setOrder(order);
		rewardPoints.setMonthAndYear(UtilConstants.TOTAL_MONTH_STRING);
		rewardPoints.setMonth(UtilConstants.TOTAL_MONTH_STRING);
		rewardPoints.setYear(UtilConstants.TOTAL_MONTH_STRING);
		return rewardPoints;
	}
}

