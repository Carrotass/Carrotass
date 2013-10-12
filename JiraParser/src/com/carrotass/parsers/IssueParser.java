package com.carrotass.parsers;

import java.io.FileReader;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.carrotass.schemas.BaseIssue;

public class IssueParser
{
	public static String CHANNEL_TAG_NAME = "channel";
	public static String BUILD_INFO_TAG_NAME = "build-info";
	public static String VERSION_TAG_NAME = "version";
	public static String ITEM_TAG_NAME = "item";
	
	private Element item;
	
	private String issueVersion;
	
	public IssueParser(InputStream inputStream) throws Exception 
	{
		SAXBuilder parser = new SAXBuilder();
		Document doc = parser.build(inputStream);
		PrepareDataForParse(doc);
	}
	
	public IssueParser(InputStream inputStream, String version) throws Exception 
	{
		issueVersion = version;
		SAXBuilder parser = new SAXBuilder();
		Document doc = parser.build(inputStream);
		PrepareDataForParse(doc);
	}
	
	public IssueParser(String fileName) throws Exception 
	{
		SAXBuilder parser = new SAXBuilder();
		FileReader fr = new FileReader(fileName);		
		Document doc = parser.build(fr);
		PrepareDataForParse(doc);
	}
	
	public BaseIssue Parse() throws Exception
	{
		BaseJiraIssueParser issueParser = ParseHelper.CreateParserByJiraVersion(issueVersion);
		issueParser.Parse(item);
		return issueParser.ParsedIssue;
	}
	
	private void PrepareDataForParse(Document doc)
	{		
		Element root = doc.getRootElement();
		Element channel = root.getChild(CHANNEL_TAG_NAME);
		item = channel.getChild(ITEM_TAG_NAME);
		
		if ((issueVersion == "") || (issueVersion == null))
		{
	        Element buildInfo = channel.getChild(BUILD_INFO_TAG_NAME);
	        issueVersion = buildInfo.getChildText(VERSION_TAG_NAME);
		}
	}
	
	
}
