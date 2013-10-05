package com.carrotass.parsers;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.Jira.JiraVersion;
import com.carrotass.schemas.ExternalIssue;

public class ExternalJiraIssueParser extends BaseJiraIssueParser
{	
	@Override
	public void FillSystemVariables() throws Exception
	{
		this.IssueJiraVersion = JiraVersion.VERSION_4;
		this.IssueJiraType = JiraType.ExternalJira;
		this.ParsedIssue = new ExternalIssue();
		this.DateLocale = ExternalIssue.DateLocale;
	}
	
	@Override
	public void FillOwnIssueProperties() throws Exception
	{		
		this.ParsedIssue.setResolved(ParseHelper.GetDateFromElement(this.Item, ExternalIssue.RESOLVED_PROPERTY_NAME, this.DateLocale));
		
		String originalEstimateTimeStr = ParseHelper.GetAttributeFromElement(this.Item, ExternalIssue.TIME_ORIGINAL_ESTIMATE_PROPERTY_NAME, ExternalIssue.TIME_ATTRIBUTE_NAME); 
		((ExternalIssue)this.ParsedIssue).setTimeOriginalEstimate(Integer.parseInt(originalEstimateTimeStr));
	}
}
