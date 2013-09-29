package com.carrotass.jira;

public class Jira {
	public enum JiraType {
		InternalJira,
		ExternalJira,
		RigentJira
	}
	
	public enum JiraVersion {
		VERSION_3(3), VERSION_4(4), VERSION_5(5);
		
		private final int version;
		private JiraVersion(int version) { this.version = version; }
		public int getVersion() { return version; }
	}
	
	public static JiraVersion getJiraVersionByType(JiraType type) {
		if (type == JiraType.InternalJira) {
			return JiraVersion.VERSION_3;
		}
		if (type == JiraType.ExternalJira) {
			return JiraVersion.VERSION_4;
		}
		if (type == JiraType.RigentJira) {
			return JiraVersion.VERSION_5;
		}
		return null;
	}
	
	public static JiraType GetJiraTypeByIssueNumber(String issueNumber) {
		if (issueNumber.contains("ASVP-")) {
			return JiraType.ExternalJira;
		}
		else if (issueNumber.contains("ASVR-")) {
			return JiraType.RigentJira;
		}
		else if (issueNumber.contains("ASV-")) {
			return JiraType.InternalJira;
		}
		throw new RuntimeException("unknown issue number " + issueNumber);
	}
	
	public static String GetJiraPathByType(JiraType jiraType) {
		return new JiraPreferences().getJiraPath(jiraType);
	}
}