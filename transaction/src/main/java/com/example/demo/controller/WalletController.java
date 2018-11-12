package com.example.demo.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.TransactionRepository;

public class WalletController {
	
	private static final Logger logger = LoggerFactory.getLogger(WalletController.class)
	private TransactionRepository transactionRepository;
	
	public void deposit(Wallet wallet, Account account, double money, Timestamp timestamp) {
		Transaction transaction = wallet.deposit(account, money, timestamp);
		if (transaction == null) {
			logger.info("deposit transaction invalid");
		}
		else {
			transactionRepository.save(transaction);
		}
	}
	
	public void withdraw(Wallet wallet, Account account, double money, Timestamp timestamp) {
		Transaction transaction = wallet.withdraw(account, money, timestamp);
		if (transaction == null) {
			logger.info("withdraw transaction invalid");
		}
		else {
			transactionRepository.save(transaction);
		}
	}
	
	public void transfer(Wallet wallet, Account from, Account to, double money, Timestamp timestamp) {
		Transaction transaction = wallet.transfer(from, to, money, timestamp);
		if (transaction == null) {
			logger.info("deposit transaction invalid");
		}
		else {
			transactionRepository.save(transaction);
		}
	}

}
