package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public abstract class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private Timestamp timestamp;
	private int type;
	
	private Account from;
	private Account to;
	private double money;
	
	private Wallet concreteWallet;
	
	public Transaction(Timestamp timestamp, int type, Account from, Account to, double money) {
		// TODO Auto-generated constructor stub
		this.timestamp = timestamp;
		this.type = type;
		this.from = from;
		this.to = to;
		this.money = money;
	}
	
	public long getId() {
		return id;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public int getType() {
		return type;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public Account getFrom() {
		return from;
	}
	
	public void setFrom(Account from) {
		this.from = from;
	}
	
	public Account getTo() {
		return to;
	}
	
	public void setTo(Account to) {
		this.to = to;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	@ManyToOne
	@JoinColumn(name = "concrete_wallet_id")
	public Wallet getConcreteWallet() {
		return concreteWallet;
	}
	
	public void setConcreteWallet(Wallet concreteWallet) {
		this.concreteWallet = concreteWallet;
	}
	
	public abstract void action() throws MoneyNegativeException, MoneyMoreThanBalanceException;
	
	
	
}
