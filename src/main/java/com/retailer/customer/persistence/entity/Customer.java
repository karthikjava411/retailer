package com.retailer.customer.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER", schema = "SYSTEM")
public class Customer {

	@Id
	@Column(name = "Customer_Id")
	private Integer customerId;
	
	@Column(name = "Customer_Name")
	private String customerName;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Phone")
	private Long phone;
	
	@Column(name = "Address1")
	private String address1;
	
	@Column(name = "Address2")
	private String address2;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "ZipCode")
	private String zipCode;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;
	
	@Column(name = "UpdatedBy")
	private String updatedBy;
	
	@Column(name = "UpdatedDate")
	private LocalDateTime updatedDate;
	
}
