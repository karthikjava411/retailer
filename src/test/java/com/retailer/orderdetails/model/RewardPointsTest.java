package com.retailer.orderdetails.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RewardPointsTest {

	RewardPoints rewardPoints;
	
	@BeforeEach
	public void setup() {
		rewardPoints = new RewardPoints();
		rewardPoints.setMonth("Feb");
		rewardPoints.setYear("2024");;
		rewardPoints.setRewards(25);
		rewardPoints.setOrder(1);
		rewardPoints.setMonthAndYear("Feb-2024");
	}
	
	@Test
	@DisplayName("Test RewardPoints model")
	public void testRewardPoints() {
		assertEquals(rewardPoints.getMonth(), "Feb");
		assertEquals(rewardPoints.getYear(), "2024");
		assertEquals(rewardPoints.getRewards(), 25);
		assertEquals(rewardPoints.getOrder(), 1);
		assertEquals(rewardPoints.getMonthAndYear(), "Feb-2024");
	}
}
