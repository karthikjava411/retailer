package com.retailer.ordereditems.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderedItems", schema = "SYSTEM")
public class OrderedItems {

	@Id
	@Column(name = "Ordered_Items_Id")
	private Integer orderedItemsId;
	
	@Column(name = "Order_Id")
	private Integer orderId;
	
	@Column(name = "Item_Id")
	private Integer itemId;
	
	@Column(name = "Quantity")
	private Integer quantity;
	
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
