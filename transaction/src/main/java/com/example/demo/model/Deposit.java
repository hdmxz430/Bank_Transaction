package com.example.demo.model;

import java.sql.Timestamp;

public class Deposit extends Transaction {

	public Deposit(Timestamp timestamp, int type, Account from, Account to, double money) {
		super(timestamp, type, from, to, money);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() throws MoneyNegativeException {
		// TODO Auto-generated method stub
		if(this.getMoney() <= 0) {
			throw new MoneyNegativeException("money is negative");
		}
		this.getFrom().setBalance(this.getFrom().getBalance() + this.getMoney());
	}

}
