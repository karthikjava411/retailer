package com.retailer.ordereditems.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.retailer.ordereditems.persistence.entity.OrderedItems;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer>{

	@Query(value = "select * from OrderedItems where Order_Id = ?1", nativeQuery = true)
	List<OrderedItems> findByOrderId(int orderId);
}
