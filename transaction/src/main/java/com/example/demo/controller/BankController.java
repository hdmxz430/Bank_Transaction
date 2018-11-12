package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bank;
import com.example.demo.repository.BankRepository;

@RestController
@RequestMapping(value="/banks")
public class BankController {
	
	private BankRepository bankRepository;
	
	@RequestMapping(value="/createBank", method = RequestMethod.POST)
	public List<Bank> createBank(@RequestBody Bank bank) {
		bankRepository.save(bank);
		return bankRepository.findAll();
	}
	
	@RequestMapping(value = "/getBanks", method = RequestMethod.GET)
	public List<Bank> getBanks(){
		return bankRepository.findAll();
	}
}
