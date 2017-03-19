package com.mysage.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.mysage.dao.CustomerDao;
import com.mysage.dao.InvoiceDao;
import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
import com.mysage.enums.InvoiceStatus;

/***
 * 
 * Set of business operations expected for Customer and Invoices.
 * Some of these operations are transactional (save and update).
 * 
 * @author Arnaud Broussel
 *
 */
public class CustomerInvoiceService implements ICustomerInvoiceService {

	private static CustomerDao customerDao;
	private static InvoiceDao invoiceDao;

	private AtomicInteger lastInvoiceNumber;

	public CustomerInvoiceService() {
		customerDao = new CustomerDao();
		invoiceDao = new InvoiceDao();

		lastInvoiceNumber = new AtomicInteger();
		lastInvoiceNumber.set(this.getLastInvoiceNumber());
	}

	public void save(Customer entity) {
		customerDao.openCurrentSessionwithTransaction();
		customerDao.save(entity);
		customerDao.closeCurrentSessionwithTransaction();
	}

	public Customer findByCode(String code) {
		return customerDao.findByCode(code);
	}

	public List<Customer> findAllCustomers() {
		return customerDao.findAll();
	}

	public void save(Invoice entity) {
		entity.setInvoiceNumber(lastInvoiceNumber.incrementAndGet());

		try {
			invoiceDao.openCurrentSessionwithTransaction();
			invoiceDao.save(entity);
			invoiceDao.closeCurrentSessionwithTransaction();
		} catch (Exception e) {
			lastInvoiceNumber.decrementAndGet();
		}
	}

	/***
	 * Change the status of an invoice to set it to Paid.
	 */
	public boolean updateToPaid(Integer number) {
		boolean result=false;
		
		invoiceDao.openCurrentSessionwithTransaction();
		Invoice entity = invoiceDao.findByNumber(number);
		if (entity!=null)
		{
			entity.setStatus(InvoiceStatus.PAID.text());
			invoiceDao.update(entity);
			
			result=true;
		}
		invoiceDao.closeCurrentSessionwithTransaction();
		
		return result;
	}

	public Invoice findByNumber(Integer number) {
		return invoiceDao.findByNumber(number);
	}

	/***
	 * Get the total amount of all the invoices in the DB for the customer indicated.
	 */
	public Float getInvoicesAmount(Customer entity) {
		float amount = 0;

		for (Invoice i : entity.getInvoices()) {
			amount = amount + i.getAmount();
		}
		return new Float(amount);
	}

	/***
	 * Get the total amount of all the invoices in the DB.
	 */
	public Float getInvoicesAmount() {
		float amount = 0;

		List<Invoice> l = invoiceDao.findAll();
		for (Invoice i : l) {
			amount = amount + i.getAmount();
		}
		return new Float(amount);
	}

	/***
	 * This method must be called only once (in constructor).
	 * 
	 * @return The last invoice number registered in the DB
	 */
	private int getLastInvoiceNumber() {
		return invoiceDao.getLastInvoiceNumber();
	}

	public List<Invoice> findAllInvoices() {
		return invoiceDao.findAll();
	}

}
