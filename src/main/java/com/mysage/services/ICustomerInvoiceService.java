package com.mysage.services;

import java.util.List;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;


public interface ICustomerInvoiceService {

	public void save(Customer entity);	
	public void save(Invoice entity);	

	public Customer findByCode(String code);
	public Invoice findByNumber(Integer number);
	
	public List<Customer> findAll();

	public Invoice statusToPaid(Invoice entity);
	
	public Float getInvoicesAmount(Customer entity);
	public Float getInvoicesAmount();
}
