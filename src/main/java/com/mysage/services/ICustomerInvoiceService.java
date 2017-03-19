package com.mysage.services;

import java.util.List;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;

/***
 * Set of business operations expected for Customer and Invoices 
 * 
 * @author Arnaud Broussel
 *
 */
public interface ICustomerInvoiceService {

	public void save(Customer entity);	
	public void save(Invoice entity);	

	public boolean updateToPaid(Integer number);
	
	public Customer findByCode(String code);
	public Invoice findByNumber(Integer number);
	
	public List<Customer> findAllCustomers();
	public List<Invoice> findAllInvoices();
	
	public Float getInvoicesAmount(Customer entity);
	public Float getInvoicesAmount();
}
