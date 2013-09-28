package com.carrotass.jira.loader;

import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.carrotass.jira.Jira;
import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.Jira.JiraVersion;
import com.carrotass.jira.JiraPreferences;
import com.carrotass.jira.login.LoginResponse;
import com.carrotass.jira.query.JiraFilter;
import com.carrotass.jira.query.QueryHelper;

abstract public class ListLoader {
	
	protected JiraType jiraType;
	
	protected JiraPreferences jiraPreferences = new JiraPreferences();
	
	protected LoginResponse loginResponse;
	
	public static ListLoader CreateInstance(JiraType jiraType, LoginResponse loginResponse) {
		ListLoader instance = null;
		JiraVersion jiraVersion = Jira.getJiraVersionByType(jiraType);
		if (jiraVersion == JiraVersion.version4) {
			instance = new ListLoaderVersion4();
		}
		else if (jiraVersion == JiraVersion.version5) {
			instance = new ListLoaderVersion5();
		}
		else if (jiraVersion == JiraVersion.version6) {
			instance = new ListLoaderVersion6();
		}
		instance.jiraType = jiraType;
		instance.loginResponse = loginResponse;
		return instance;
	}
	
	public InputStream LoadList(JiraFilter filter) throws Exception {
		String requestURLString = getRequestURLString();
		String queryString = buildQueryFromParams(getQueryParams(filter));
		
		String request = jiraPreferences.getJiraPath(jiraType) + requestURLString + "?" + queryString;
		System.out.println(request);
		
		URLConnection connection = QueryHelper.PrepareConnection(request, loginResponse.Cookies);
		
		InputStream in = connection.getInputStream();
		return in;
	}
	
	abstract ArrayList<String> getQueryParams(JiraFilter filter) throws Exception;
	
	abstract String getRequestURLString();
	
	protected String buildQueryFromParams(ArrayList<String> params) throws Exception {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<params.size();i++) {
			if (i>0) {
				builder.append("&");
			}
			builder.append(QueryHelper.formatStringToURL(params.get(i)));
		}
		return builder.toString();
	}
	
	
	protected String formatDateForQuery(Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		String formattedDate = formatter.format(date.getTime());
		return formattedDate;
	}
	
}