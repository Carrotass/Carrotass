package com.carrotass.parsers;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.Jira.JiraVersion;
import com.carrotass.schemas.RigentIssue;;

public class RigentJiraIssueParser extends BaseJiraIssueParser
{	
	@Override
	public void FillSystemVariables() throws Exception
	{
		this.IssueJiraVersion = JiraVersion.VERSION_5;
		this.IssueJiraType = JiraType.RigentJira;
		this.ParsedIssue = new RigentIssue();
		this.DateLocale = RigentIssue.DateLocale;
	}
	
	@Override
	public void FillOwnIssueProperties() throws Exception
	{
		this.ParsedIssue.setResolved(ParseHelper.GetDateFromElement(this.Item, RigentIssue.RESOLVED_PROPERTY_NAME, this.DateLocale));

		String originalEstimateTimeStr = ParseHelper.GetAttributeFromElement(this.Item, RigentIssue.TIME_ORIGINAL_ESTIMATE_PROPERTY_NAME, RigentIssue.TIME_ATTRIBUTE_NAME); 
		((RigentIssue)this.ParsedIssue).setTimeOriginalEstimate(Integer.parseInt(originalEstimateTimeStr));	
	}

}
