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
		if (jiraVersion == JiraVersion.VERSION_3) {
			instance = new ListLoaderVersion3();
		}
		else if (jiraVersion == JiraVersion.VERSION_4) {
			instance = new ListLoaderVersion4();
		}
		else if (jiraVersion == JiraVersion.VERSION_5) {
			instance = new ListLoaderVersion5();
		}
		instance.jiraType = jiraType;
		instance.loginResponse = loginResponse;
		return instance;
	}
	
	public InputStream LoadList(JiraFilter filter) throws Exception {
		String requestURLString = getRequestURLString();
		ArrayList<String[]> paramsList = getQueryParams(filter);
		paramsList.add(new String[] {"field", "key"});
		paramsList.add(new String[] {"field", "summary"});
		String queryString = buildQueryFromParams(paramsList);
		
		String request = jiraPreferences.getJiraPath(jiraType) + requestURLString + "?" + queryString;
		System.out.println(request);
		
		URLConnection connection = QueryHelper.PrepareConnection(request, loginResponse.Cookies);
		
		InputStream in = connection.getInputStream();
		return in;
	}
	
	abstract ArrayList<String[]> getQueryParams(JiraFilter filter) throws Exception;
	
	abstract String getRequestURLString();
	
	protected String buildQueryFromParams(ArrayList<String[]> params) throws Exception {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<params.size();i++) {
			if (i>0) {
				builder.append("&");
			}
			String[] param = params.get(i);
			String paramName = QueryHelper.formatStringToURL(param[0]);
			
			builder.append(paramName.replaceAll("%2B", "+"));
			if (param.length > 1)
			{
				builder.append("=");
				String paramValue = QueryHelper.formatStringToURL(param[1]);
				System.out.println("paramvalue before: " + paramValue);
				System.out.println("paramvalue after: " + paramValue.replaceAll("%2B", "+"));
				builder.append(paramValue.replaceAll("%2B", "+"));
			}
		}
		return builder.toString();
	}
	
	
	protected String formatDateForQuery(Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		String formattedDate = formatter.format(date.getTime());
		return formattedDate;
	}
	
}