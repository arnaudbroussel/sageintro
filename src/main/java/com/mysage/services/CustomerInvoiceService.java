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
		
		lastInvoiceNumber=new AtomicInteger();
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
		invoiceDao.openCurrentSessionwithTransaction();
		
		if (entity.getInvoiceId()==null){
			entity.setInvoiceNumber(lastInvoiceNumber.incrementAndGet());
		}
		
		invoiceDao.save(entity);
		invoiceDao.closeCurrentSessionwithTransaction();
	}

	public Invoice findByNumber(Integer number) {
		return invoiceDao.findByNumber(number);
	}

	public Invoice statusToPaid(Invoice entity) {
		entity.setStatus(InvoiceStatus.PAID.text());
		return entity;
	}

	public Float getInvoicesAmount(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Float getInvoicesAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	private int getLastInvoiceNumber(){
		return invoiceDao.getLastInvoiceNumber(); 
	}
}
