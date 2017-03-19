package com.mysage.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/***
 * 
 * @author Arnaud Broussel
 *
 * This abstract is the base class of any entity with data access.
 * A singleton mechanism is used to initialize the Hibernate framework.
 *
 * @param <T> Any type
 * 
 */
public abstract class EntityDao<T> implements Dao<T, Integer> {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	protected Session session = getSessionFactory().openSession();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected void shutdown() {
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

	protected void openSession() {
		if (!session.isOpen())
			session = getSessionFactory().openSession();
	}
}
