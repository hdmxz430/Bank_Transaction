package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private Timestamp timestamp;
	private int type;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	private long from;
	private long to;
	private double money;
	
	public Transaction(Timestamp timestamp, int type, Account account, long from, long to, double money) {
		// TODO Auto-generated constructor stub
		this.timestamp = timestamp;
		this.type = type;
		this.account = account;
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
	
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public long getFrom() {
		return from;
	}
	
	public void setFrom(long from) {
		this.from = from;
	}
	
	public long getTo() {
		return to;
	}
	
	public void setTo(long to) {
		this.to = to;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
}
