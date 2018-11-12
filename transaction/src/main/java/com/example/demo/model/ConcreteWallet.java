package com.example.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ConcreteWallet implements Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private List<Transaction> transactions;
	
	private User user;
	
	public ConcreteWallet(User user) {
		// TODO Auto-generated constructor stub
		transactions = new ArrayList<>();
		this.user = user;
	}
	
	public long getId() {
		return id;
	}
	
	@OneToMany(mappedBy = "concreteWallet")
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public List<Transaction> getLatestKTransactions(int k){
		return transactions.subList(transactions.size() - k, transactions.size());
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@OneToOne(mappedBy = "ConcreteWallet")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Transaction deposit(Account account, double money, Timestamp timestamp) {
		// TODO Auto-generated method stub
		Transaction transaction = new Deposit(timestamp, 1, account, null, money);
		try {
			transaction.action();
			transactions.add(transaction);
			return transaction;
		} catch (MoneyNegativeException | MoneyMoreThanBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Transaction withdraw(Account account, double money, Timestamp timestamp) {
		// TODO Auto-generated method stub
		Transaction transaction = new Withdraw(timestamp, 2, account, null, money);
		try {
			transaction.action();
			transactions.add(transaction);
			return transaction;
		} catch (MoneyNegativeException | MoneyMoreThanBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Transaction transfer(Account from, Account to, double money, Timestamp timestamp) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transfer(timestamp, 3, from, to, money);
		try {
			transaction.action();
			transactions.add(transaction);
			return transaction;
		} catch (MoneyNegativeException | MoneyMoreThanBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
