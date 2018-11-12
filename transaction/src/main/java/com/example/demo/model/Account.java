package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private double balance;
	
	private User user;
	
	public Account() {}
	
	public Account(double balance) {
		this.balance = balance;
	}
	
	public long getId() {
		return id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object account) {
		if (account == null) return false;
		if (account == this) return true;
		if (!(account instanceof Account)) return false;
		Account a = (Account) account;
		return a.getId() == this.getId();
	}
}
