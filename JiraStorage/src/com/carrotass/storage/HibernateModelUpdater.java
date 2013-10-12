package com.carrotass.storage;


import java.util.Date;

import org.hibernate.Session;

import com.carrotass.models.Issue;
import com.carrotass.models.ModelUpdater;
import com.carrotass.models.ProjectVersion;
import com.carrotass.models.User;

public class HibernateModelUpdater implements ModelUpdater {

	public static void test1()
	{

		Session session = HibernateSessionHelper.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = new User();
		user.setName("carrotass");
		user.setEmail("carrotass@gmail.com");
		
		User user2 = new User();
		user2.setName("au");
		user2.setEmail("au@gmail.com");
		
		ProjectVersion version = new ProjectVersion();
		version.setNumber("2.10.0.41");
		
		Issue asv1 = new Issue("asv-1", new Date(), new Date(), user2, user, 1000, 23, 42, null);
		session.save(asv1);
		
		Issue asv2 = new Issue("asv-2", new Date(), new Date(), user, user2, 5, 3, 4, null);
		session.save(asv2);
		
		session.getTransaction().commit();
		
		session.close();
		HibernateSessionHelper.getSessionFactory().close();
		
	}
	public void CreateIssue(Issue issue) {
		ModelUpdateHelper.SaveObject(issue);

	}

	public void UpdateIssue(Issue issue) {
		// TODO Auto-generated method stub

	}

	public void DeleteIssue(Issue issue) {
		// TODO Auto-generated method stub

	}

	public void CreateUser(User user) {
		ModelUpdateHelper.SaveObject(user);
	}

	public void UpdateUser(User user) {
		ModelUpdateHelper.SaveObject(user);	
	}

	public void DeleteUser(User user) {
		ModelUpdateHelper.DeleteObject(user);
	}

	public void CreateProjectVersion(ProjectVersion version) {
		// TODO Auto-generated method stub

	}

	public void UpdateProjectVersion(ProjectVersion version) {
		// TODO Auto-generated method stub

	}

	public void DeleteProjectVersion(ProjectVersion version) {
		// TODO Auto-generated method stub

	}

}
