package com.carrotass.jira.loader;

import java.util.ArrayList;

import com.carrotass.jira.query.JiraFilter;

public class ListLoaderVersion5 extends ListLoader {

	@Override
	ArrayList<String[]> getQueryParams(JiraFilter filter) {
		ArrayList<String[]> params = new ArrayList<String[]>();
		if (filter.startDate != null && filter.finishDate != null) {
			params.add(new String[] {"jqlQuery", String.format("updated+>=+%s+AND+updated+<=+%s", formatDateForQuery(filter.startDate), formatDateForQuery(filter.finishDate))});
		}
		else if (filter.startDate != null) {
			params.add(new String[] {"jqlQuery", String.format("updated+>=+%s", formatDateForQuery(filter.startDate))});
		}
		else if (filter.finishDate != null) {
			params.add(new String[] {"jqlQuery", String.format("updated+<=%+s", formatDateForQuery(filter.finishDate))});
		}
		if (filter.user != null) {
			params.add(new String[] {"assigneeSelect", "specificuser"});
			params.add(new String[] {"assignee", filter.user});
		}
		
		return params;
	}

	@Override
	String getRequestURLString() {
		return "sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml";
	}

}
