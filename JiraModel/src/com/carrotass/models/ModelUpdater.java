package com.carrotass.models;

public interface ModelUpdater {
	
	public void CreateIssue(Issue issue);
	public void UpdateIssue(Issue issue);
	public void DeleteIssue(Issue issue);
	
	public void CreateUser(User user);
	public void UpdateUser(User user);
	public void DeleteUser(User user);

	public void CreateProjectVersion(ProjectVersion version);
	public void UpdateProjectVersion(ProjectVersion version);
	public void DeleteProjectVersion(ProjectVersion version);

}
