package com.carrotass.storage;

import org.hibernate.Session;

public class ModelUpdateHelper {
	
	public static void DeleteObject(Object object)
	{
		Session session = HibernateSessionHelper.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(object);
		
		session.getTransaction().commit();
		
		session.close();
		HibernateSessionHelper.getSessionFactory().close();
		
	}

	public static void SaveObject(Object object)
	{
		Session session = HibernateSessionHelper.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(object);
		
		session.getTransaction().commit();
		
		session.close();
		HibernateSessionHelper.getSessionFactory().close();
		
	}


}
