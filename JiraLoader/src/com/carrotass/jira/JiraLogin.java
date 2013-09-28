package com.carrotass.jira;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.carrotass.jira.Jira.JiraType;

public class JiraLogin {
	
	public final static String LoginPath = "login.jsp";
	
	public boolean Login(String username, String password, JiraType jiraType) throws Exception {
		boolean result = false;
		
		String jiraPath = new JiraPreferences().getJiraPath(jiraType);
		char[] urlParameters = String.format("os_username=%s&os_password=%s", username, password).toCharArray();
		
		URL loginUrl = new URL(jiraPath + LoginPath);
		HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();
		connection.setDoOutput(true);
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(urlParameters );
		writer.flush();
		writer.close();
		
		int response = connection.getResponseCode();
		if (response == 200) {
			result = true;
		}
		
		connection.disconnect();
		return result;
	}

}
