package com.carrotass.jira;

import java.util.HashMap;

import com.carrotass.jira.Jira.JiraType;

public class JiraWorker {
	
	private HashMap<JiraType, Boolean> loggedIn = new HashMap<>();
	
	private JiraPreferences jiraPreferences = new JiraPreferences();
	
	public void Login(JiraType jiraType) throws Exception {
		if (! (loggedIn.containsKey(jiraType) && loggedIn.get(jiraType)) ) {
			loggedIn.put(jiraType, new JiraLogin().Login(
					jiraPreferences.getUsername(), 
					jiraPreferences.getPassword(), 
					jiraType) );
			if (loggedIn.get(jiraType)) {
				System.out.println("Successfully login to " + jiraType.name());
			}
			else
			{
				System.out.println("Failed to login to " + jiraType.name());
			}
		}
		else
		{
			System.out.println("Already login to " + jiraType.name());
		}
	}
}
