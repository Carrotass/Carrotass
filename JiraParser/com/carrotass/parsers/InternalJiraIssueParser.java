package com.carrotass.parsers;

import java.util.Date;
import java.util.List;

import org.jdom2.Element;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.Jira.JiraVersion;
import com.carrotass.schemas.InternalIssue;

public class InternalJiraIssueParser extends BaseJiraIssueParser
{	
	@Override
	public void FillSystemVariables() throws Exception
	{
		this.IssueJiraVersion = JiraVersion.VERSION_3;
		this.IssueJiraType = JiraType.InternalJira;
		this.ParsedIssue = new InternalIssue();
		this.DateLocale = InternalIssue.DateLocale;
	}
	
	@Override
	public void FillOwnIssueProperties() throws Exception
	{
		List<Element> customFields = this.GetCustomFields(); 
		List<Element> customValues = ParseHelper.GetCustomFieldValuesByName(
													customFields, 
													InternalIssue.RESOLVED_DATE_CUSTOM_FIELD_NAME, 
													InternalIssue.CUSTOM_FIELD_NAME_ELEMENT_NAME, 
													InternalIssue.CUSTOM_FIELDS_VALUE_NAME);
		
		String resolvedDateString = ParseHelper.GetEncodedStringValueFromElement(customValues.get(0), InternalIssue.CUSTOM_FIELD_VALUE_NAME, "windows-1251", "UTF-8");
		Date resolvedDate = ParseHelper.ConvertStringToDate(resolvedDateString, this.DateLocale);
		this.ParsedIssue.setResolved(resolvedDate);
	}
	

}
