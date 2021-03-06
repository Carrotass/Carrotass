package com.carrotass.jira.login;

import java.net.HttpURLConnection;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.JiraPreferences;
import com.carrotass.jira.query.QueryHelper;

public class JiraLogin {

	public static final String LOGIN_PATH = "login.jsp";
	
	public LoginResponse Login(String username, String password, JiraType jiraType) throws Exception {
		LoginResponse result = null;
		
		String jiraPath = new JiraPreferences().getJiraPath(jiraType);
		
		String request = String.format("%s%s?os_username=%s&os_password=%s", 
				jiraPath, LOGIN_PATH, QueryHelper.formatStringToURL(username),
				QueryHelper.formatStringToURL(password));
		System.out.println("login to: " + request);
		HttpURLConnection connection = (HttpURLConnection) QueryHelper.PrepareConnection(request, null);
		
		int response = connection.getResponseCode();
		if (response == 200) { 
			result = LoginResponse.SucceedResponse(connection.getHeaderFields().get("Set-Cookie"));
		}
		else {
			result = LoginResponse.FailedResponse(response);
		}
		
		connection.disconnect();
		return result;
	}

}
