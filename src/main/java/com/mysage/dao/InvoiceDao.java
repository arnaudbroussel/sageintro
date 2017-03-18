package com.mysage.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import com.mysage.entities.Invoice;

public class InvoiceDao extends EntityDao<Invoice> {

	public void save(Invoice entity) {
		session.saveOrUpdate(entity);
	}

	public void update(Invoice entity) {
		session.update(entity);
	}

	public Invoice findById(Integer id) {
		return (Invoice) session.get(Invoice.class, id);
	}

	public Invoice findByNumber(Integer number) {
		Query query = session.createQuery("from Invoice where invoiceNumber = :invoiceNumber ");
		query.setParameter("invoiceNumber", number);
		return (Invoice) query.uniqueResult();
	}

	public void delete(Invoice entity) {
		session.delete(entity);
	}

	public List<Invoice> findAll() {
		@SuppressWarnings("unchecked")
		List<Invoice> l = (List<Invoice>) session.createQuery("from Invoice").list();
		return l;
	}

	public int getLastInvoiceNumber() {
		Criteria criteria = session.createCriteria(Invoice.class).setProjection(Projections.max("invoiceNumber"));
		return (Integer) criteria.uniqueResult();
	}

}
