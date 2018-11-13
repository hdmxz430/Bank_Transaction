package com.example.demo.model;

public class UserNotExistException extends Exception {
	public UserNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
