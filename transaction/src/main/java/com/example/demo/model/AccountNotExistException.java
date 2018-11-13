package com.example.demo.model;

public class AccountNotExistException extends Exception {
	public AccountNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
