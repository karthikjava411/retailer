package com.retailer.itemdetails.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ItemDetails", schema = "SYSTEM")
public class ItemDetails {

	@Id
	@Column(name = "Item_Id")
	private Integer itemId;
	
	@Column(name = "Item_Name")
	private String itemName;
	
	@Column(name = "Item_Price")
	private BigDecimal itemPrice;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;
	
	@Column(name = "UpdatedBy")
	private String updatedBy;
	
	@Column(name = "UpdatedDate")
	private LocalDateTime updatedDate;
}
