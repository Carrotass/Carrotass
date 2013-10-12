package com.carrotass.UnitTests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class UnitTestRunner 
{
	public static void main(String[] args) 
	{
	    TestRunner runner = new TestRunner();
	    TestSuite suite = new TestSuite();
	    suite.addTest(new IssueParserTest("testParseInternalIssue"));
	    suite.addTest(new IssueParserTest("testParseExternalIssue"));
	    suite.addTest(new IssueParserTest("testParseRidjentIssue"));
	    runner.doRun(suite);
	}
}
