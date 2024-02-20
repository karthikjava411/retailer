package com.retailer.itemdetails.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.itemdetails.persistence.entity.ItemDetails;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Integer>{

}
