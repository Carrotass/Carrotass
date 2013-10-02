package com.carrotass.models;

public class ProjectVersion {
	private int id;
	private String number;
	public ProjectVersion() {super();};
	
	public ProjectVersion(String number) {
		super();
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
