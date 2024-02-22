package com.retailer.exception;

public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String msg){
		super(msg);
	}
}
