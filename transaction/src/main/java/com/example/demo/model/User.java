package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String username;
	private String password;
	
	private Bank bank;
	
	private List<Account> accounts;
	
	private Wallet wallet;
	
	public User() {
		
	}
	
	public User(String username, String password, Wallet wallet, Bank bank) {
		this.username = username;
		this.password = password;
		accounts = new ArrayList<>();
		this.wallet = wallet;
		this.bank = bank;
	}
	
	public long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Account> getAccounts(){
		return accounts;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public List<Account> addAccount(Account account){
		accounts.add(account);
		return accounts;
	}
	
	public List<Account> removeAccount(Account account){
		accounts.remove(account);
		return accounts;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_id")
	public Wallet getWallet() {
		return wallet;
	}

}
