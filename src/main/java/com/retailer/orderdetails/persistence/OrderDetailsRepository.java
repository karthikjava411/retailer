package com.retailer.orderdetails.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retailer.orderdetails.persistence.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	@Query(value="Select * from OrderDetails where Customer_Id = ?1 and Order_Date between last_day(add_months(TO_DATE(?3,'YYYY-MM-DD'),?2)) and TO_DATE(?3,'YYYY-MM-DD') order by Order_Date desc", nativeQuery = true)
	public List<OrderDetails> getCustomerTransactionByPeriod(int customerId, int noOfMonths, String currentDate);
	
	@Query(value="Select * from OrderDetails where Customer_Id = ?1 order by Order_Date desc", nativeQuery = true)
	public List<OrderDetails> getCustomerTransactionByPeriod(int customerId);
	
	@Query(value="Select * from OrderDetails where Customer_Id = ?1 order by Order_Date asc", nativeQuery = true)
	public List<OrderDetails> findByCustomerId(int customerId);
}
