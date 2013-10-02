package com.carrotass.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private String number;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	private int planAmount;
	
	public int getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(int planAmount) {
		this.planAmount = planAmount;
	}

	private int factAmount;
	
	public int getFactAmount() {
		return factAmount;
	}

	public void setFactAmount(int factAmount) {
		this.factAmount = factAmount;
	}

	private int realAmount;
	
	public int getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(int realAmount) {
		this.realAmount = realAmount;
	}


	private List<ProjectVersion> resolveVersions = new ArrayList<ProjectVersion>();

	public List<ProjectVersion> getResolveVersions() {
		return resolveVersions;
	}

	public void setResolveVersions(List<ProjectVersion> resolveVersions) {
		this.resolveVersions = resolveVersions;
	}
	
	private Date createdOn;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	private Date resolvedOn;
	
	public Date getResolvedOn() {
		return resolvedOn;
	}

	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	
	private Date updateOn;
	
	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	private User createdBy;
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	private User assignedTo;
	
	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Issue()
	{
		super();
	}
	public Issue(String number, Date createdOn, Date resolvedOn,
			User createdBy, User assignedTo, int planAmount, int factAmount,
			int realAmount, List<ProjectVersion> resolveVersions) {
		super();
		this.number = number;
		this.createdOn = createdOn;
		this.resolvedOn = resolvedOn;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.planAmount = planAmount;
		this.factAmount = factAmount;
		this.realAmount = realAmount;
		this.resolveVersions = resolveVersions;
	}


}
