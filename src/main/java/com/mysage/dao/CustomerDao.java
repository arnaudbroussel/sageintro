package com.mysage.dao;

import java.util.List;

import org.hibernate.Query;

import com.mysage.entities.Customer;

/***
 * 
 * @author Arnaud Broussel
 *
 * CRUD operations to perform on a Customer.
 */
public class CustomerDao extends EntityDao<Customer> {

	public void save(Customer entity) {
		session.save(entity);
	}

	public void update(Customer entity) {
		session.update(entity);
	}

	public Customer findById(Integer id) {
		return (Customer) session.get(Customer.class, id);
	}

	/***
	 * 
	 * @param code Code of the customer
	 * @return An instance of customer when exists
	 */
	public Customer findByCode(String code) {
		this.openSession();
		Query query = session.createQuery("from Customer where customerCode = :code ");
		query.setParameter("code", code);
		return (Customer) query.uniqueResult();
	}

	public void delete(Customer entity) {
		session.delete(entity);
	}

	/***
	 * Gives the list of all the customers in the DB.
	 */
	public List<Customer> findAll() {
		this.openSession();
		@SuppressWarnings("unchecked")
		List<Customer> l = (List<Customer>) session.createQuery("from Customer").list();
		return l;
	}

}
