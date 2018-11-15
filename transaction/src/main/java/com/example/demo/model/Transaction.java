package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long fromAccount;
	private long toAccount;
	private double money;
	
	private Timestamp timestamp;
	private int type;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonManagedReference
	private Account account;
	
	public Transaction() {}
	
	public Transaction(Timestamp timestamp, int type, Account account, long from, long to, double money) {
		// TODO Auto-generated constructor stub
		this.timestamp = timestamp;
		this.type = type;
		this.account = account;
		this.fromAccount = from;
		this.toAccount = to;
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
	
	public long getFromAccount() {
		return fromAccount;
	}
	
	public void setFromAccount(long from) {
		this.fromAccount = from;
	}
	
	public long getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(long to) {
		this.toAccount = to;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
}
