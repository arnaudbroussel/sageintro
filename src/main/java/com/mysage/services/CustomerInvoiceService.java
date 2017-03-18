package com.mysage.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.mysage.dao.CustomerDao;
import com.mysage.dao.InvoiceDao;
import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
import com.mysage.enums.InvoiceStatus;

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

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public void save(Invoice entity) {
		entity.setInvoiceNumber(lastInvoiceNumber.incrementAndGet());

		invoiceDao.openCurrentSessionwithTransaction();
		invoiceDao.save(entity);
		invoiceDao.closeCurrentSessionwithTransaction();
	}

	public void updateToPaid(Integer number) {
		invoiceDao.openCurrentSessionwithTransaction();
		
		Invoice entity = invoiceDao.findByNumber(number);
		entity.setStatus(InvoiceStatus.PAID.text());
		
		invoiceDao.update(entity);
		invoiceDao.closeCurrentSessionwithTransaction();
	}

	public Invoice findByNumber(Integer number) {
		return invoiceDao.findByNumber(number);
	}

	public void statusToPaid(Invoice entity) {
		entity.setStatus(InvoiceStatus.PAID.text());
	}

	public Float getInvoicesAmount(Customer entity) {
		float amount = 0;

		for (Invoice i : entity.getInvoices()) {
			amount = amount + i.getAmount();
		}
		return new Float(amount);
	}

	public Float getInvoicesAmount() {
		float amount = 0;

		List<Invoice> l = invoiceDao.findAll();
		for (Invoice i : l) {
			amount = amount + i.getAmount();
		}
		return new Float(amount);
	}

	private int getLastInvoiceNumber() {
		return invoiceDao.getLastInvoiceNumber();
	}

}
