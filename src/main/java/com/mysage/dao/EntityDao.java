package com.mysage.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class EntityDao<T> implements Dao<T, Integer> {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	protected Session session = getSessionFactory().openSession();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public Session openCurrentSessionwithTransaction() {
		session = getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}

	public void closeCurrentSessionwithTransaction() {
		session.getTransaction().commit();
		session.close();
	}

}
