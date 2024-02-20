package com.retailer.orderdetails.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderDetails", schema = "SYSTEM")
public class OrderDetails {

	@Id
	@Column(name = "Order_Id")
	private Integer orderId;
	
	@Column(name = "Customer_Id")
	private String customerId;
	
	@Column(name = "Order_Date")
	private LocalDateTime orderDate;
	
	@Column(name = "Amount")
	private BigDecimal amount;
	
	@Column(name = "Reward_Points")
	private Integer rewardPoints;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;
	
	@Column(name = "UpdatedBy")
	private String updatedBy;
	
	@Column(name = "UpdatedDate")
	private LocalDateTime updatedDate;
	
}
