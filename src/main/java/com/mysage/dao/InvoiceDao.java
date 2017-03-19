package com.mysage.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.mysage.entities.Invoice;

/***
 * 
 * @author Arnaud Broussel
 *
 * CRUD operations to perform on an Invoice.
 */
public class InvoiceDao extends EntityDao<Invoice> {

	public void save(Invoice entity) {
		session.save(entity);
	}

	public void update(Invoice entity) {
		session.update(entity);
	}

	public Invoice findById(Integer id) {
		return (Invoice) session.get(Invoice.class, id);
	}

	/***
	 * 
	 * @param number Number of the invoice
	 * @return An instance of invoice when exists
	 */	
	public Invoice findByNumber(Integer number) {
		this.openSession();
		Query query = session.createQuery("from Invoice where invoiceNumber = :invoiceNumber ");
		query.setParameter("invoiceNumber", number);
		return (Invoice) query.uniqueResult();
	}

	public void delete(Invoice entity) {
		session.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> findAll() {
		this.openSession();

		Criteria c = session.createCriteria(Invoice.class);
		c.addOrder(Order.asc("invoiceNumber"));
		return c.list();
	}

	/***
	 * This method should be called only once in the cycle of the execution of the application.
	 * Only to be called once at application start.
	 * 
	 * @return The last invoice number registered in the DB.
	 */
	public int getLastInvoiceNumber() {
		try {
			Criteria criteria = session.createCriteria(Invoice.class).setProjection(Projections.max("invoiceNumber"));
			return (Integer) criteria.uniqueResult();
		} catch (Exception e) {
			return 0;
		}
	}

}
