package com.carrotass.jira.query;

import java.util.Calendar;

public class JiraFilter {
	
	public Calendar startDate = null;
	
	public Calendar finishDate = null;
	
	public String user = null;
	
	public static JiraFilter FromMonthStartToNow() {
		JiraFilter instance = new JiraFilter();
		Calendar startOfMonth = Calendar.getInstance();
		startOfMonth.set(Calendar.DATE, 1);
		instance.startDate = startOfMonth;
		instance.finishDate = Calendar.getInstance();
		return instance;
	}

}
