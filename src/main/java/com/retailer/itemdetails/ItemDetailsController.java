package com.retailer.itemdetails;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.exception.UserException;
import com.retailer.itemdetails.persistence.entity.ItemDetails;
import com.retailer.itemdetails.service.ItemDetailsService;
import com.retailer.util.UtilConstants;

@RestController
@RequestMapping("itemdetails")
@CrossOrigin(origins = "*")
public class ItemDetailsController {
	
	private ItemDetailsService itemDetailsService;
	
	public ItemDetailsController(ItemDetailsService itemDetailsService) {
		this.itemDetailsService = itemDetailsService;
	}
	
	@GetMapping("getAllItemDetails")
	public  ResponseEntity<List<ItemDetails>> getAllItemDetails() {
		List<ItemDetails>  itemDetailsList = itemDetailsService.getAllItemDetails();
		return new ResponseEntity<List<ItemDetails>>(itemDetailsList,HttpStatus.OK);
	}
	
	@PostMapping("save")
	public ResponseEntity<String> saveItemDetails(@RequestBody ItemDetails itemDetails) throws UserException {
		try {
			itemDetails.setCreatedDate(LocalDateTime.now());
			itemDetails.setCreatedBy(UtilConstants.CURRENT_USER);
			itemDetailsService.saveItemDetails(itemDetails);
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}catch(UserException ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
