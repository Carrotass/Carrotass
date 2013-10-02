package com.carrotass.models;

public class User {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public User()
	{
		super();
	}
	

	
}
