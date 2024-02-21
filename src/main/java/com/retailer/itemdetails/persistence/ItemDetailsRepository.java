package com.retailer.itemdetails.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.retailer.itemdetails.persistence.entity.ItemDetails;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Integer>{

	@Query( value = "Select * from ItemDetails where Item_Name = ?1", nativeQuery = true)
	Optional<ItemDetails> getItemDetailsByItemName(String itemName);
}
