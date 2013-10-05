package com.carrotass.schemas;

import java.util.Locale;

public class RigentIssue extends BaseIssue
{
	private int timeOriginalEstimate;
	
	public int getTimeOriginalEstimate() 
	{
		return timeOriginalEstimate;
	}

	public void setTimeOriginalEstimate(int timeOriginalEstimate) 
	{
		this.timeOriginalEstimate = timeOriginalEstimate;
	}
	
	public static String RESOLVED_PROPERTY_NAME = "resolved";
	
	public static String TIME_ORIGINAL_ESTIMATE_PROPERTY_NAME = "timeoriginalestimate";
	
	public static Locale DateLocale = new Locale("ru");
			
	public void PrintElement() throws Exception
	{
		super.PrintElement();
		System.out.println("Originale estiamte time: " + this.timeOriginalEstimate);
	}			
	
}
