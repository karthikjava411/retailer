package com.retailer.exception;

public class ItemDetailsNotFoundException  extends Exception{

	private static final long serialVersionUID = 1L;

	public ItemDetailsNotFoundException(String msg){
		super(msg);
	}
}
