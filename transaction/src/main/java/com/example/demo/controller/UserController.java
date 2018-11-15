package com.example.demo.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.AccountNotExistException;
import com.example.demo.model.MoneyMoreThanBalanceException;
import com.example.demo.model.MoneyNegativeException;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionSizeLessThanKException;
import com.example.demo.model.User;
import com.example.demo.model.UserNotExistException;
import com.example.demo.model.Wallet;
import com.example.demo.model.WalletExistsException;
import com.example.demo.model.WalletNotExistException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WalletRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountResitory;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void register(@RequestBody String body) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(body);
			String username = jsonNode.get("username").toString();
			username = username.substring(1, username.length()-1);
			String password = jsonNode.get("password").toString();
			password = password.substring(1, password.length()-1);
			User user = new User(username, password);
			userRepository.save(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		User user = new User(username, password);
//		userRepository.save(user);
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/getAllWallets", method = RequestMethod.GET)
	public List<Wallet> getWallets(){
		return walletRepository.findAll();
	}
	
	@RequestMapping(value = "/createWallet", method = RequestMethod.POST)
	public String createWallet(@RequestBody String body) throws UserNotExistException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(body);
			long userId = jsonNode.get("userId").asLong();
			User user = userRepository.findById(userId);
			if(user == null) {
				throw new UserNotExistException("user not exist");
			}
			Wallet wallet = new Wallet(user);
			walletRepository.save(wallet);
			return "Create a new wallet successfully";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "fail to create a new wallet";
		}
	}
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public void createAccount(@RequestBody String body) throws UserNotExistException, WalletNotExistException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(body);
			long userId = jsonNode.get("userId").asLong();
			User user = userRepository.findById(userId);
			if(user == null) {
				throw new UserNotExistException("user not exist");
			}
			Wallet wallet = user.getWallet();
			if(wallet == null) {
				throw new WalletNotExistException("wallet not exist");
			}
			Account account = new Account(0);
			account.setUser(user);
			wallet.addAccounts(account);
			accountResitory.save(account);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
	public List<Account> getAccounts(){
		return accountResitory.findAll();
	}
	
	@RequestMapping(value = "/getLatestKTransactions/{accountId}/{k}", method = RequestMethod.GET)
	public List<Transaction> getLatestKTransactions(@PathVariable long accountId, @PathVariable int k) throws AccountNotExistException, TransactionSizeLessThanKException {
		Account account = accountResitory.findById(accountId);
		if (account == null) {
			throw new AccountNotExistException("account not exist");
		}
		int size = account.getTransactions().size();
		if(size < k) {
			throw new TransactionSizeLessThanKException("transaction size less than k");
		}
		
		return account.getTransactions().subList(size-k, size);
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public void deposit(@RequestBody String body) throws AccountNotExistException, MoneyNegativeException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(body);
			long accountId = jsonNode.get("accountId").asLong();
			double money = jsonNode.get("money").asDouble();
			if(money < 0) {
				throw new MoneyNegativeException("money is negative");
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Account account = accountResitory.findById(accountId);
			if(account == null) {
				throw new AccountNotExistException("account not exist");
			}
			Transaction transaction = new Transaction(timestamp, 1, account, accountId, -1, money);
			account.setBalance(account.getBalance() + money);
			account.addTransaction(transaction);
			transactionRepository.save(transaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public void withdraw(@RequestBody String body) throws AccountNotExistException, MoneyNegativeException, MoneyMoreThanBalanceException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(body);
			long accountId = jsonNode.get("accountId").asLong();
			double money = jsonNode.get("money").asDouble();
			
			if(money < 0) {
				throw new MoneyNegativeException("money is negative");
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Account account = accountResitory.findById(accountId);
			if(account == null) {
				throw new AccountNotExistException("account not exist");
			}
			if(account.getBalance() < money) {
				throw new MoneyMoreThanBalanceException("money more than balance");
			}
			Transaction transaction = new Transaction(timestamp, 2, account, accountId, -1, money);
			account.setBalance(account.getBalance() - money);
			account.addTransaction(transaction);
			transactionRepository.save(transaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public void transfer(@RequestBody String body) throws AccountNotExistException, MoneyMoreThanBalanceException, MoneyNegativeException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(body);
			long from = jsonNode.get("from").asLong();
			long to = jsonNode.get("to").asLong();
			double money = jsonNode.get("money").asDouble();
			if(money < 0) {
				throw new MoneyNegativeException("money is negative");
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			Account fromAccount = accountResitory.findById(from);
			Account toAccount = accountResitory.findById(to);
			if(fromAccount == null || toAccount == null) {
				throw new AccountNotExistException("account not exist");
			}
			if(fromAccount.getBalance() < money) {
				throw new MoneyMoreThanBalanceException("money more than balance");
			}
			fromAccount.setBalance(fromAccount.getBalance() - money);
			toAccount.setBalance(toAccount.getBalance() + money);
			Transaction transaction = new Transaction(timestamp, 3, fromAccount, from, to, money);
			fromAccount.addTransaction(transaction);
			toAccount.addTransaction(transaction);
			transactionRepository.save(transaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
