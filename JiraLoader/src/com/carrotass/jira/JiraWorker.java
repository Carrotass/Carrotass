package com.carrotass.jira;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.loader.issue.IssueLoader;
import com.carrotass.jira.loader.list.ListLoader;
import com.carrotass.jira.login.JiraLogin;
import com.carrotass.jira.login.LoginResponse;
import com.carrotass.jira.query.JiraFilter;

public class JiraWorker {

	private HashMap<JiraType, LoginResponse> loggedIn = new HashMap<>();

	private JiraPreferences jiraPreferences = new JiraPreferences();

	public void Login(JiraType jiraType) throws Exception {
		if (!isLoggedIn(jiraType)) {
			LoginResponse response = new JiraLogin().Login(
					jiraPreferences.getUsername(), 
					jiraPreferences.getPassword(), 
					jiraType);
			if (response.Succeed) {
				loggedIn.put(jiraType,  response);
				System.out.println("Successfully login to " + jiraType.name());
			}
			else {
				System.out.println("Failed to login to " + jiraType.name());
			}
		}
		else {
			System.out.println("Already login to " + jiraType.name());
		}
	}

	public InputStream LoadIssuesListThisMonth(JiraType jiraType) throws Exception {
		if (!isLoggedIn(jiraType)) {
			Login(jiraType);
		}
		ListLoader loader = ListLoader.CreateInstance(jiraType, loggedIn.get(jiraType));
		JiraFilter filter = JiraFilter.FromMonthStartToNow();
		return loader.LoadList(filter);
	}
	
	public InputStream LoadIssueByNumber(String issueNumber) throws Exception {
		JiraType jiraType = Jira.GetJiraTypeByIssueNumber(issueNumber);
		if (!isLoggedIn(jiraType)) {
			Login(jiraType);
		}
		IssueLoader loader = new IssueLoader(loggedIn.get(jiraType));
		return loader.GetIssue(issueNumber);
	}

	public ArrayList<String> ConvertIssuesListFromXMLToArrayOfNames(InputStream stream) throws Exception {
		return ListLoader.GetIssuesListByXML(stream);
	}

	private boolean isLoggedIn(JiraType jiraType) {
		return (loggedIn.containsKey(jiraType) && loggedIn.get(jiraType).Succeed);
	}
}
