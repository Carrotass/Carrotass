package com.carrotass.storage;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionHelper {
	
	public static SessionFactory sessionFactory;
	public static ServiceRegistry serviceRegistry;
	static
	{
		try
		{
			Configuration configuration = new Configuration().configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
		}
		catch (HibernateException exception)
		{
			System.out.println("Problem with creating hibernate session factory "+exception.getMessage());
		}
		catch (Exception exception)
		{
			System.out.println("Problem with creating hibernate session factory "+exception.getMessage());
		}
	
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
}
