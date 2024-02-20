package com.retailer.util;

import java.math.BigDecimal;

public class RewardPointsCalculator {

	private static final Integer MINIMUM_AMOUNT_ELIGIBLE_FOR_REWARDS = 50;
	
	private static final Integer MINIMUM_AMOUNT_ELIGIBLE_FOR_DOUBLE_REWARDS = 100;
	
	private static final Integer DOUBLE_REWARDS = 2;
	
	private static final Integer SINGLE_REWARDS = 1;
	
	public static Integer calculateRewardPoints(BigDecimal amount) {
		Integer amountInInt = amount.intValue();
		int rewardsEarned = 0;
		if( isAmountEligibleForRewards(amountInInt) ) {
			
			if(isAmountEligibleForDoubleRewards(amountInInt)) {
				rewardsEarned = getSingleRewardsPoint( MINIMUM_AMOUNT_ELIGIBLE_FOR_REWARDS ) + getDoubleRewardsPoint( amountInInt - MINIMUM_AMOUNT_ELIGIBLE_FOR_DOUBLE_REWARDS );
			}else {
				rewardsEarned = getSingleRewardsPoint( amountInInt - MINIMUM_AMOUNT_ELIGIBLE_FOR_REWARDS );
			}
			 
			
		}
		
		return rewardsEarned;
	}
	
	private static boolean isAmountEligibleForRewards(Integer amount) {
		return amount > MINIMUM_AMOUNT_ELIGIBLE_FOR_REWARDS;
	}
	
	private static boolean isAmountEligibleForDoubleRewards(Integer amount) {
		return amount > MINIMUM_AMOUNT_ELIGIBLE_FOR_DOUBLE_REWARDS;
	}
	
	private static Integer getSingleRewardsPoint(Integer amount) {
		return amount * SINGLE_REWARDS;
	}
	
	private static Integer getDoubleRewardsPoint(Integer amount) {
		return amount * DOUBLE_REWARDS;
	}
}
