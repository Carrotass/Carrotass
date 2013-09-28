package com.carrotass.jira;

public class Jira {
	public enum JiraType {
		InternalJira,
		ExternalJira,
		RigentJira
	}
	
	public enum JiraVersion {
		version4(4), version5(5), version6(6);
		
		private final int version;
		private JiraVersion(int version) { this.version = version; }
		public int getVersion() { return version; }
	}
	
	public static JiraVersion getJiraVersionByType(JiraType type) {
		if (type == JiraType.InternalJira) {
			return JiraVersion.version4;
		}
		if (type == JiraType.ExternalJira) {
			return JiraVersion.version5;
		}
		if (type == JiraType.RigentJira) {
			return JiraVersion.version6;
		}
		return null;
	}
}