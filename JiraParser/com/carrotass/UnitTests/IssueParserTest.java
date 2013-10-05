package com.carrotass.UnitTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;

import com.carrotass.parsers.IssueParser;
import com.carrotass.schemas.ExternalIssue;
import com.carrotass.schemas.InternalIssue;
import com.carrotass.schemas.RigentIssue;

public class IssueParserTest extends TestCase
{
    public IssueParserTest(String testName) 
    {
        super(testName);
    }
	
	@Test
	public void testParseInternalIssue() throws Exception
	{
		IssueParser ip = new IssueParser(".\\TestData\\InternalIssue.xml");
		System.out.println(ip);
		InternalIssue parsed = (InternalIssue)ip.Parse();
		
		assertEquals("ASV-14230", parsed.getKey());
		assertEquals("Разрешен", parsed.getStatus());
		assertEquals("Исправлено", parsed.getResolution());
		assertEquals("p.brichev", parsed.getAssignee());
		assertEquals("p.brichev", parsed.getReporter());

		DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss zzzz", Locale.US);
		
		Date createdDate = df.parse("Thu, 26 Sep 2013 17:34:39 +0800 (KRAST)");
		assertTrue(createdDate.equals(parsed.getCreated()));
		
		Date updatedDate = df.parse("Thu, 26 Sep 2013 18:27:24 +0800 (KRAST)");
		assertTrue(updatedDate.equals(parsed.getUpdated()));
		
		Date resolvedDate = df.parse("Thu, 26 Sep 2013 18:27:18 +0800 (KRAST)");
		assertTrue(resolvedDate.equals(parsed.getResolved()));
		
		assertEquals(1, parsed.getFixVersion().size());
		assertEquals("2.10.0.39", parsed.getFixVersion().get(0));
		assertEquals(0, parsed.getTimeEstimate());
		assertEquals(1800, parsed.getTimeSpent());
		assertEquals("Ошибка кодирования", parsed.getIssueReason());
	}
	
	@Test
	public void testParseExternalIssue() throws Exception
	{
		IssueParser ip = new IssueParser(".\\TestData\\ExternalIssue.xml");
		System.out.println(ip);
		ExternalIssue parsed = (ExternalIssue)ip.Parse();
		
		assertEquals("ASVP-6533", parsed.getKey());
		assertEquals("Разрешен", parsed.getStatus());
		assertEquals("Исправлено", parsed.getResolution());
		assertEquals("p.brichev", parsed.getAssignee());
		assertEquals("n.grebennikova", parsed.getReporter());

		Locale locale = new Locale("ru");
		DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss zzzz", locale);
		
		Date createdDate = df.parse("Вт, 9 апр 2013 09:06:05 +0800");
		assertTrue(createdDate.equals(parsed.getCreated()));
		
		Date updatedDate = df.parse("Чт, 26 сен 2013 14:24:22 +0800");
		assertTrue(updatedDate.equals(parsed.getUpdated()));
		
		Date resolvedDate = df.parse("Чт, 26 сен 2013 14:14:21 +0800");
		assertTrue(resolvedDate.equals(parsed.getResolved()));
		
		assertEquals(1, parsed.getFixVersion().size());
		assertEquals("2.10.0.39", parsed.getFixVersion().get(0));
		assertEquals(0, parsed.getTimeEstimate());
		assertEquals(10800, parsed.getTimeSpent());
		assertEquals(10800, parsed.getTimeOriginalEstimate());
		assertEquals(null, parsed.getIssueReason());
	}
	
	@Test
	public void testParseRidjentIssue() throws Exception
	{
		IssueParser ip = new IssueParser(".\\TestData\\RidjentIssue.xml");
		System.out.println(ip);
		RigentIssue parsed = (RigentIssue)ip.Parse();
		
		assertEquals("ASVR-161", parsed.getKey());
		assertEquals("Решенные", parsed.getStatus());
		assertEquals("Исправленный", parsed.getResolution());
		assertEquals("p.brichev", parsed.getAssignee());
		assertEquals("v.urbaeva", parsed.getReporter());

		Locale locale = new Locale("ru");
		DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss zzzz", locale);
		
		Date createdDate = df.parse("Ср, 4 сен 2013 15:23:49 +0800");
		assertTrue(createdDate.equals(parsed.getCreated()));
		
		Date updatedDate = df.parse("Чт, 19 сен 2013 10:48:15 +0800");
		assertTrue(updatedDate.equals(parsed.getUpdated()));
		
		Date resolvedDate = df.parse("Чт, 12 сен 2013 19:32:23 +0800");
		assertTrue(resolvedDate.equals(parsed.getResolved()));
		
		assertEquals(2, parsed.getFixVersion().size());
		assertEquals("2.10.0.37", parsed.getFixVersion().get(1));
		assertEquals("2.9.0", parsed.getFixVersion().get(0));
		assertEquals(0, parsed.getTimeEstimate());
		assertEquals(14400, parsed.getTimeSpent());
		assertEquals(0, parsed.getTimeOriginalEstimate());
		assertEquals("Уточнение требования", parsed.getIssueReason());
	}		
	
	
	

}
