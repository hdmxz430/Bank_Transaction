package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;

@Entity
public interface Wallet {
	public Transaction deposit(Account account, double money, Timestamp timestamp);
	public Transaction withdraw(Account account, double money, Timestamp timestamp);
	public Transaction transfer(Account from, Account to, double money, Timestamp timestamp);
}
