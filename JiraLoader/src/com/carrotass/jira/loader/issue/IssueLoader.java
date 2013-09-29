package com.carrotass.jira.loader.issue;

import java.io.InputStream;
import java.net.URLConnection;

import com.carrotass.jira.Jira;
import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.login.LoginResponse;
import com.carrotass.jira.query.QueryHelper;

public class IssueLoader {
	
	private LoginResponse loginResponse;

	public IssueLoader(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
	
	public InputStream GetIssue(String issueNumber) throws Exception {
		JiraType jiraType = Jira.GetJiraTypeByIssueNumber(issueNumber);
		
		String jiraPath = Jira.GetJiraPathByType(jiraType);
		
		String requestURLString = getRequestURLString(issueNumber);
		String request = jiraPath + requestURLString;
		System.out.println(request);

		URLConnection connection = QueryHelper.PrepareConnection(request, loginResponse.Cookies);

		InputStream in = connection.getInputStream();
		return in;
	}

	private String getRequestURLString(String issueNumber) {
		return String.format("si/jira.issueviews:issue-xml/%s/%s.xml", issueNumber, issueNumber);
	}
}
