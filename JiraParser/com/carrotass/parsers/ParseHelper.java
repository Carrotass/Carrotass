package com.carrotass.parsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Attribute;

import com.carrotass.jira.Jira;
import com.carrotass.jira.Jira.JiraVersion;

public class ParseHelper
{
	public static BaseJiraIssueParser CreateParserByJiraVersion(String version) throws Exception 
	{
		Integer jiraVersion = GetJiraTypeByJiraVersion(version);
		String jiraType = Jira.GetJiraTypeByJiraVersion(jiraVersion).toString();
		Class<?> schemaClass = Class.forName("com.carrotass.parsers." + jiraType + "IssueParser");
		Object obj = schemaClass.newInstance();
		return (BaseJiraIssueParser)obj;
	}
	
	public static Integer GetJiraTypeByJiraVersion(String version) throws Exception 
	{
		Integer jiraVersion = Integer.parseInt(version.split("\\.")[0]);
		if (!JiraVersion.IsValidJiraVersion(jiraVersion))
		{
			throw new RuntimeException("Unknown jira version " + jiraVersion + "!");	
		}
		return jiraVersion;
	}
	
	public static String GetAttributeFromElement(Element main, String name, String attribute)
	{
		String result = "";
		Element child = main.getChild(name);
		if (child != null)
		{
			Attribute attr = child.getAttribute(attribute);
			if (attr != null)
			{
				result = attr.getValue();
			}
		}
		return result;	
	}
	
	public static List<String> GetStringListFromElement(Element main, String name)
	{
		List<Element> resultList = main.getChildren(name);
		return ConvertElementListToStringValueList(resultList);
	}
     
    public static List<String> ConvertElementListToStringValueList(List <Element> ElementList) 
 	{
    	List<String> result = new ArrayList<String>();
    	
        for (Integer j=0; j < ElementList.size(); j++)
        {
        	Element el = ElementList.get(j);
        	result.add(el.getValue());
        }
        
        return result;
	}
    
    public static Date GetDateFromElement(Element main, String name, Locale locale) throws Exception 
 	{
    	String dateString = main.getChildText(name);
    	return ConvertStringToDate(dateString, locale);
	} 
    
    public static Date ConvertStringToDate(String dateString,Locale locale) throws Exception 
 	{
	    DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss zzzz", locale);
	    Date result = df.parse(dateString);
	    return result;
	}
    
    public static List<Element> GetCustomFieldValuesByName(List<Element> customFields,
    													   String name,
    													   String elementName,
    													   String valueName) throws Exception 
 	{
    	List<Element> customFieldValues = null;
    	if (customFields != null)
    	{
			for (Integer j=0; j < customFields.size(); j++)
			{
				Element custom = customFields.get(j);
				String customName = custom.getChildText(elementName);
				if (customName.equals(name))
				{
					customFieldValues = custom.getChildren(valueName);
					break;
				}
			}
    	}
		return customFieldValues;
	}
}
