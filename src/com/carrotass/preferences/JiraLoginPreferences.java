package com.carrotass.preferences;

public class JiraLoginPreferences {
	java.util.prefs.Preferences preferences = 
			java.util.prefs.Preferences.userNodeForPackage(getClass());
	
	public void setCredentials(String username, String password) {
		preferences.put("jira_username", username);
		preferences.put("jira_password", password);
	}
	
	public String getUsername() {
		return preferences.get("jira_username", null);
	}
	
	public String getPassword() {
		return preferences.get("jira_password", null);
	}
}
