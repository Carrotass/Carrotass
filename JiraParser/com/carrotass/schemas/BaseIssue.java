package com.carrotass.schemas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaseIssue 
{

	public BaseIssue()
	{
		super();
	}	
	
	private String key;
	
	public String getKey() 
	{
		return key;
	}

	public void setKey(String key) 
	{
		this.key = key;
	}	

	private int timeEstimate;
	
	public int getTimeEstimate() 
	{
		return timeEstimate;
	}

	public void setTimeEstimate(int timeEstimate) 
	{
		this.timeEstimate = timeEstimate;
	}

	private int timeSpent;
	
	public int getTimeSpent() 
	{
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) 
	{
		this.timeSpent = timeSpent;
	}

	private List<String> fixVersion = new ArrayList<String>();

	public List<String> getFixVersion()
	{
		return fixVersion;
	}

	public void setFixVersion(List<String> fixVersion) 
	{
		this.fixVersion = fixVersion;
	}
	
	private Date created;

	public Date getCreated() 
	{
		return created;
	}

	public void setCreated(Date created) 
	{
		this.created = created;
	}
	
	private Date updated;
	
	public Date getUpdated() 
	{
		return updated;
	}
	
	public void setUpdated(Date updated) 
	{
		this.updated = updated;
	}	
	
	private Date resolved;
	
	public Date getResolved() 
	{
		return resolved;
	}
	
	public void setResolved(Date resolved) 
	{
		this.resolved = resolved;
	}	
	
	private String status;

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	private String resolution;

	public String getResolution() 
	{
		return resolution;
	}

	public void setResolution(String resolution)
	{
		this.resolution = resolution;
	}

	private String reporter;
	
	public String getReporter() 
	{
		return reporter;
	}

	public void setReporter(String reporter) 
	{
		this.reporter = reporter;
	}

	private String assignee;
	
	public String getAssignee() 
	{
		return assignee;
	}

	public void setAssignee(String assignee) 
	{
		this.assignee = assignee;
	}

	private String issueReason;
	
	public String getIssueReason() 
	{
		return issueReason;
	}

	public void setIssueReason(String issueReason) 
	{
		this.issueReason = issueReason;
	}
	
	public static String KEY_PROPERTY_NAME = "key";
	
	public static String STATUS_PROPERTY_NAME = "status";
	
	public static String RESOLUTION_PROPERTY_NAME = "resolution";
	
	public static String ASSIGNEE_PROPERTY_NAME = "assignee";
	
	public static String REPORTER_PROPERTY_NAME = "reporter";
	
	public static String USERNAME_ATTRIBUTE_NAME = "username";
	
	public static String CREATED_PROPERTY_NAME = "created";
	
	public static String UPDATED_PROPERTY_NAME = "updated";
	
	public static String FIX_VERSION_PROPERTY_NAME = "fixVersion";
	
	public static String TIME_ESTIMATE_PROPERTY_NAME = "timeestimate";
	
	public static String TIME_SPENT_PROPERTY_NAME = "timespent";
	
	public static String TIME_ATTRIBUTE_NAME = "seconds";
	
	public static String ISSUE_REASON_CUSTOM_FIELD_VALUE = "Причина запроса";
	
	public static String CUSTOM_FIELDS_ELEMENT_NAME = "customfields";
	
	public static String CUSTOM_FIELD_ELEMENT_NAME = "customfield";
	
	public static String CUSTOM_FIELD_NAME_ELEMENT_NAME = "customfieldname";
	
	public static String CUSTOM_FIELDS_VALUE_NAME = "customfieldvalues";
	
	public static String CUSTOM_FIELD_VALUE_NAME = "customfieldvalue";
	
	public static Locale DateLocale = Locale.US;
	
	public void PrintElement() throws Exception
	{
		System.out.println("");
		System.out.println("Номер запроса: " + this.key);
		System.out.println("Статус запроса: " + this.status);
		System.out.println("Резолюция запроса: " + this.resolution);
		System.out.println("Причина запроса: " + this.issueReason);
		
		String FixVersions = "";
		for (Integer j=0; j < this.fixVersion.size(); j++)
		{
			if (!FixVersions.equals(""))
				FixVersions = FixVersions + "; ";
			
			FixVersions = FixVersions + this.fixVersion.get(j).toString();
		}
		System.out.println("Исправить в версиях: " + FixVersions);
		
		System.out.println("Автор : " + this.reporter);
		System.out.println("Исполнитель: " + this.assignee);
		System.out.println("Дата создания: " + this.created);
		System.out.println("Дата обновления: " + this.updated);
		System.out.println("Дата разрешения: " + this.resolved);
		System.out.println("Запланированное время: " + this.timeEstimate);
		System.out.println("Потраченное время: " + this.timeSpent);
	}
	
}
