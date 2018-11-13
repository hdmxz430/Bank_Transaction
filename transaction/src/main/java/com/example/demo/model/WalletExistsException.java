package com.example.demo.model;

public class WalletExistsException extends Exception{
	public WalletExistsException(String errorMessage) {
		super(errorMessage);
	}

}
