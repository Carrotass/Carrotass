package com.carrotass.jira.loader;

import java.util.ArrayList;

import com.carrotass.jira.query.JiraFilter;

public class ListLoaderVersion5 extends ListLoader {

	@Override
	ArrayList<String> getQueryParams(JiraFilter filter) {
		
		ArrayList<String> params = new ArrayList<>();
		if (filter.startDate != null) {
			params.add(String.format("updated>=%s", formatDateForQuery(filter.startDate)));
		}
		if (filter.finishDate != null) {
			params.add(String.format("updated<==%s", formatDateForQuery(filter.finishDate)));
		}
		if (filter.user != null) {
			params.add(String.format("assigneeSelect=specificuser"));
			params.add(String.format("assignee=%s", filter.user));
		}
		
		return params;
	}

	@Override
	String getRequestURLString() {
		return "sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml";
	}

}
