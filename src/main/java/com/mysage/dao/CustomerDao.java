package com.mysage.dao;

import java.util.List;

import org.hibernate.Query;

import com.mysage.entities.Customer;

public class CustomerDao extends EntityDao<Customer> {

	public void save(Customer entity) {
		session.saveOrUpdate(entity);
	}

	public void update(Customer entity) {
		session.update(entity);
	}

	public Customer findById(Integer id) {
		return (Customer) session.get(Customer.class, id);
	}

	public Customer findByCode(String code) {
		Query query = session.createQuery("from Customer where customerCode = :code ");
		query.setParameter("code", code);
		return (Customer) query.uniqueResult();
	}

	public void delete(Customer entity) {
		session.delete(entity);
	}

	public List<Customer> findAll() {
		@SuppressWarnings("unchecked")
		List<Customer> l = (List<Customer>) session.createQuery("from Customer").list();
		return l;
	}

}