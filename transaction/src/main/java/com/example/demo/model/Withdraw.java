package com.example.demo.model;

import java.sql.Timestamp;

public class Withdraw extends Transaction {
	public Withdraw(Timestamp timestamp, int type, Account from, Account to, double money) {
		super(timestamp, type, from, to, money);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() throws MoneyNegativeException, MoneyMoreThanBalanceException {
		// TODO Auto-generated method stub
		if(this.getMoney() < 0) {
			throw new MoneyNegativeException("money is negative");
		}
		else if(this.getMoney() > this.getFrom().getBalance()) {
			throw new MoneyMoreThanBalanceException("money more than balance");
		}
		this.getFrom().setBalance(this.getFrom().getBalance() - this.getMoney());
	}
}
