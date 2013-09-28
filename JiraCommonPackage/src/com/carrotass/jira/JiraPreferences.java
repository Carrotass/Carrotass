package com.carrotass.jira;

import com.carrotass.jira.Jira.JiraType;

public class JiraPreferences {
	
	java.util.prefs.Preferences preferences = 
			java.util.prefs.Preferences.userNodeForPackage(getClass());
	
	public void setCredentials(String username, String password) {
		preferences.put("jira_username", username);
		preferences.put("jira_password", password);
	}
	
	public void setJiraPath(JiraType type, String path) {
		preferences.put(type.toString(), path);
	}
	
	public String getJiraPath(JiraType type) {
		return preferences.get(type.toString(), null);
	}
	
	public String getUsername() {
		return preferences.get("jira_username", null);
	}
	
	public String getPassword() {
		return preferences.get("jira_password", null);
	}
}
