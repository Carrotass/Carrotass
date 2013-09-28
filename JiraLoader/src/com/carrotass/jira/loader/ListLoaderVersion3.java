package com.carrotass.jira.loader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.carrotass.jira.query.JiraFilter;

public class ListLoaderVersion3 extends ListLoader {

	@Override
	ArrayList<String[]> getQueryParams(JiraFilter filter) throws Exception {
		ArrayList<String[]> params = new ArrayList<String[]>();
		if (filter.startDate != null) {
			params.add(new String[] {"updated:after", formatDateForQuery(filter.startDate)});
		}
		if (filter.finishDate != null) {
			params.add(new String[] {"updated:before", formatDateForQuery(filter.finishDate)});
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
	
	@Override
	protected String formatDateForQuery(Calendar date) {
		Locale ruLocal = new Locale("ru"); 
		SimpleDateFormat formatter = new SimpleDateFormat("d MMM yy", ruLocal);
		String formattedDate = formatter.format(date.getTime());
		return formattedDate;
	}

}
