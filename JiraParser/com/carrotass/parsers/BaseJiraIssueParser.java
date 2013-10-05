package com.carrotass.parsers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import com.carrotass.jira.Jira.JiraVersion;
import com.carrotass.jira.Jira.JiraType;
import com.carrotass.schemas.*;


public abstract class BaseJiraIssueParser 
{
	
	protected Element Item; 
	
	protected BaseIssue ParsedIssue;
	
	protected JiraType IssueJiraType;
	
	protected JiraVersion IssueJiraVersion;
	
	private List<Element> customFields;
	
	protected Locale DateLocale;
	
	protected List<Element> GetCustomFields() throws Exception
	{
		List<Element> resultList = new ArrayList<Element>();
		
		if (customFields != null)
		{
			resultList = customFields;
		}
		else if (this.Item != null)
		{
			Element customFields = Item.getChild(BaseIssue.CUSTOM_FIELDS_ELEMENT_NAME);
			resultList = customFields.getChildren(BaseIssue.CUSTOM_FIELD_ELEMENT_NAME);
		}
		else
		{
			throw new Exception("Unable to get custom fields from input stream!");
		}
		return resultList;
	}	
	
	
	public void Parse(Element item) throws Exception
	{
		this.Item = item;
		FillSystemVariables();
		FillCommonIssueProperties();
		FillOwnIssueProperties();
	}
	
	abstract public void FillSystemVariables() throws Exception;

	protected void FillCommonIssueProperties() throws Exception
	{
		this.ParsedIssue.setKey(this.Item.getChildText(BaseIssue.KEY_PROPERTY_NAME));
		this.ParsedIssue.setStatus(this.Item.getChildText(BaseIssue.STATUS_PROPERTY_NAME));
		this.ParsedIssue.setResolution(this.Item.getChildText(BaseIssue.RESOLUTION_PROPERTY_NAME));
		
		String assigneeStr = ParseHelper.GetAttributeFromElement(this.Item, BaseIssue.ASSIGNEE_PROPERTY_NAME, BaseIssue.USERNAME_ATTRIBUTE_NAME);
		this.ParsedIssue.setAssignee(assigneeStr);
		
		String reporterStr = ParseHelper.GetAttributeFromElement(this.Item, BaseIssue.REPORTER_PROPERTY_NAME, BaseIssue.USERNAME_ATTRIBUTE_NAME);
		this.ParsedIssue.setReporter(reporterStr);				
		
		String estimateTimeStr = ParseHelper.GetAttributeFromElement(this.Item, BaseIssue.TIME_ESTIMATE_PROPERTY_NAME, BaseIssue.TIME_ATTRIBUTE_NAME); 
		this.ParsedIssue.setTimeEstimate(Integer.parseInt(estimateTimeStr));
		
		String spentTimeStr = ParseHelper.GetAttributeFromElement(this.Item, BaseIssue.TIME_SPENT_PROPERTY_NAME, BaseIssue.TIME_ATTRIBUTE_NAME); 
		this.ParsedIssue.setTimeSpent(Integer.parseInt(spentTimeStr));
		
		List<String> versions = ParseHelper.GetStringListFromElement(this.Item, BaseIssue.FIX_VERSION_PROPERTY_NAME);
		this.ParsedIssue.setFixVersion(versions);
		
		this.ParsedIssue.setCreated(ParseHelper.GetDateFromElement(this.Item, BaseIssue.CREATED_PROPERTY_NAME, this.DateLocale));
		this.ParsedIssue.setUpdated(ParseHelper.GetDateFromElement(this.Item, BaseIssue.UPDATED_PROPERTY_NAME, this.DateLocale));
		
		List<Element> customFields = this.GetCustomFields(); 
		List<Element> customValues = ParseHelper.GetCustomFieldValuesByName(customFields, BaseIssue.ISSUE_REASON_CUSTOM_FIELD_VALUE, BaseIssue.CUSTOM_FIELD_NAME_ELEMENT_NAME, BaseIssue.CUSTOM_FIELDS_VALUE_NAME);
		if (customValues != null)
		{
			String reason = ParseHelper.GetEncodedStringValueFromElement(customValues.get(0), BaseIssue.CUSTOM_FIELD_VALUE_NAME, "windows-1251", "UTF-8");
			this.ParsedIssue.setIssueReason(reason);
		}
	}
	
	abstract public void FillOwnIssueProperties() throws Exception;
	
}
