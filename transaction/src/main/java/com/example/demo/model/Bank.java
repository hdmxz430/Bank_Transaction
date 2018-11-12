package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	
	private List<User> users;
	
	public Bank() {
		// TODO Auto-generated constructor stub
	}
	
	public Bank(String name, List<User> users) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.users = users;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy="bank", cascade = CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}

}
