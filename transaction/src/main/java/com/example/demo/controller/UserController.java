package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController {
private UserRepository userRepository;
	
	@RequestMapping(value="/createUser", method = RequestMethod.POST)
	public List<User> createUser(@RequestBody User user) {
		userRepository.save(user);
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public double deposit(@RequestBody User user, @RequestBody Account account, @RequestBody double money) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return user.getWallet().deposit(account, money, timestamp);
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public double withdraw(@RequestBody User user, @RequestBody Account account, @RequestBody double money) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return user.getWallet().withdraw(account, money, timestamp);
	}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public double withdraw(@RequestBody User user, @RequestBody Account from, @RequestBody Account to, @RequestBody double money) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return user.getWallet().transfer(from, to, money, timestamp);
	}
}
